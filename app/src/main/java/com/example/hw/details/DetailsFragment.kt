package com.example.hw.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.hw.R
import com.example.hw.movie.MovieItem

class DetailsFragment : Fragment() {
    companion object {
        const val TAG = "DetailsFragment"
        const val EXTRA_DETAILS = "EXTRA_DETAILS"
        const val EXTRA_IMAGE = "EXTRA_IMAGE"

        fun newInstance(item: MovieItem): DetailsFragment {
            return DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_DETAILS, item.details)
                    putInt(EXTRA_IMAGE, item.image)
                }
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.details).text = arguments?.getString(EXTRA_DETAILS) ?: "default"
        view.findViewById<ImageView>(R.id.imageDetails).setImageResource(arguments?.getInt(EXTRA_IMAGE) ?: 0)
    }

}