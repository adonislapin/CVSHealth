package com.adonis.cvshealth.interfaces

import com.adonis.cvshealth.models.image.ImageModel

interface IDetailImage {

    fun selectImage(info: ImageModel)
}