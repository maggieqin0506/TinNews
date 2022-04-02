package com.laioffer.tinnews.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.laioffer.tinnews.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {
    private var binding: FragmentDetailsBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = DetailsFragmentArgs.fromBundle(arguments!!).article
        binding!!.detailsTitleTextView.text = article.title
        binding!!.detailsAuthorTextView.text = article.author
        binding!!.detailsDateTextView.text = article.publishedAt
        binding!!.detailsDescriptionTextView.text = article.description
        binding!!.detailsContentTextView.text = article.content
        Picasso.get().load(article.urlToImage).into(binding!!.detailsImageView)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding!!.root
    }
}