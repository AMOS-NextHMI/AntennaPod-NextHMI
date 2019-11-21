package de.danoeh.antennapod

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import de.amos.antennapodHMI.R

class SearchPodcastFragment : Fragment() {


    private val ARG_FEED_URL = "feedurl"

    companion object {
        fun newInstance() = SearchPodcastFragment()
    }

    private lateinit var viewModel: SearchPodcastViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.search_podcast_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchPodcastViewModel::class.java)
        // TODO: Use the ViewModel
    }


    private fun setupSeachBox(root: View) {
        val etxtFeedurl = root.findViewById<EditText>(R.id.etxtFeedurl)

        val args = arguments
        if (args != null && args.getString(ARG_FEED_URL) != null) {
            etxtFeedurl.setText(args.getString(ARG_FEED_URL))
        }

        val butConfirmAddUrl = root.findViewById<Button>(R.id.butConfirm)
        butConfirmAddUrl.setOnClickListener { v -> addUrl(etxtFeedurl.text.toString()) }

        combinedFeedSearchBox = root.findViewById<EditText>(R.id.combinedFeedSearchBox)
        combinedFeedSearchBox.setOnEditorActionListener({ v, actionId, event ->
            performSearch()
            true
        })
    }


    private fun performSearch() {
        val query = combinedFeedSearchBox.getText().toString()

        if (query.startsWith("http")) {
            addUrl(query)
            return
        }

        val bundle = Bundle()
        bundle.putString(CombinedSearchFragment.ARGUMENT_QUERY, query)
        val fragment = CombinedSearchFragment()
        fragment.setArguments(bundle)
        activity.loadChildFragment(fragment)
    }
}
