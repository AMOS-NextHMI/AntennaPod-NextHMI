package de.danoeh.antennapod.discovery;

import java.util.ArrayList;
import java.util.List;

import de.danoeh.antennapod.core.service.download.AntennapodHttpClient;
import de.mfietz.fyydlin.FyydClient;
import de.mfietz.fyydlin.FyydResponse;
import de.mfietz.fyydlin.SearchHit;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FyydPodcastSearcher implements PodcastSearcher {
    private final FyydClient client = new FyydClient(AntennapodHttpClient.getHttpClient());

    public Single<List<PodcastSearchResult>> search(String query) {
        return Single.create((SingleOnSubscribe<List<PodcastSearchResult>>) subscriber -> {
            FyydResponse response = client.searchPodcasts(query, 10)
                    .subscribeOn(Schedulers.io())
                    .blockingGet();

            ArrayList<PodcastSearchResult> searchResults = new ArrayList<>();

            if (!response.getData().isEmpty()) {
                for (SearchHit searchHit : response.getData()) {
                    PodcastSearchResult podcast = PodcastSearchResult.fromFyyd(searchHit);
                    searchResults.add(podcast);
                }
            }

            subscriber.onSuccess(searchResults);
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}