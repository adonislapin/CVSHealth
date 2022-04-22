package com.adonis.cvshealth.data.retrofit.service

import com.adonis.cvshealth.models.image.BaseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrService {

    @GET("/services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    suspend fun searchImages(
        @Query("tags") searchParams: String
    ): Response<BaseModel>
}