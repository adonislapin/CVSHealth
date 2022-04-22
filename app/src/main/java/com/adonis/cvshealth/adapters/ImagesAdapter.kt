package com.adonis.cvshealth.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adonis.cvshealth.R
import com.adonis.cvshealth.databinding.ItemImageBinding
import com.adonis.cvshealth.interfaces.IDetailImage
import com.adonis.cvshealth.models.image.ImageModel
import com.bumptech.glide.Glide

class ImagesAdapter (
    private var imageList: MutableList<ImageModel>,
    private val listener: IDetailImage
)
    : RecyclerView.Adapter<ImagesAdapter.ImageHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val itemBinding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        val item = imageList[position]
        holder.bind(item, listener)
    }

    override fun getItemCount() = imageList.size

    fun setData(tagList: MutableList<ImageModel>) {
        this.imageList.clear()
        this.imageList.addAll(tagList)
    }

    class ImageHolder(private val itemBinding: ItemImageBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(model: ImageModel, listener: IDetailImage) {
            Glide.with(itemBinding.image)
                .load(model.media.m)
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder)
                .into(itemBinding.image)

            itemBinding.image.setOnClickListener { listener.selectImage(model) }
        }
    }
}