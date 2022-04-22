package com.adonis.cvshealth.data.retrofit.datasource

import com.adonis.cvshealth.models.image.BaseModel
import retrofit2.Response

interface DataSource {

    suspend fun searchImages(
        searchParams: String
    ): Response<BaseModel>
}