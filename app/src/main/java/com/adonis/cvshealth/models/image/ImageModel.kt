package com.adonis.cvshealth.models.image

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageModel(
    val title: String,
    val link: String,
    val media: Media,
    val description: String,
    val published: String,
    val author: String,
) : Parcelable

@Parcelize
data class Media(
    val m: String
) : Parcelable

@Parcelize
data class BaseModel(
    val title: String,
    val link: String,
    val description: String?,
    val modifier: String,
    val generator: String,
    val items: List<ImageModel>
) : Parcelable