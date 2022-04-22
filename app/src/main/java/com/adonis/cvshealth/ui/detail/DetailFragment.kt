package com.adonis.cvshealth.ui.detail

import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adonis.cvshealth.R
import com.adonis.cvshealth.databinding.FragmentDetailBinding
import com.adonis.cvshealth.models.image.ImageModel
import com.adonis.cvshealth.utils.Utils
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private var detail: ImageModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        detail = arguments?.getParcelable("dataImageModel")
        detail?.let { fillInfo(it) }

        return binding?.root
    }

    private fun fillInfo(info: ImageModel){
        binding?.titleTxt?.text = info.title
        binding?.authorTxt?.text = getString(R.string.author,  info.author)

        binding?.image?.let {
            Glide.with(this)
                .load(info.media.m)
                .placeholder(R.drawable.ic_placeholder)
                .into(it)
        }

        binding?.sizeTxt?.text = Utils.getSize(info.description)
        binding?.descTxt?.text = Utils.getDescription(info.description)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}