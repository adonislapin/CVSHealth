package com.adonis.cvshealth.data.retrofit.datasource

import android.content.Context
import com.adonis.cvshealth.data.retrofit.service.FlickrService
import com.adonis.cvshealth.models.image.BaseModel
import com.adonis.cvshealth.models.image.ImageModel
import com.adonis.cvshealth.patterns.SingletonHolder
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitDataSource
private constructor(val context: Context) : DataSource {

    lateinit var flickrService: FlickrService

    init {
        initialize()
    }

    private fun initialize() {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
        val builder = OkHttpClient.Builder()
        val client = builder.build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com")
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        flickrService = retrofit.create(FlickrService::class.java)
    }

    override suspend fun searchImages(searchParams: String): Response<BaseModel> {
        return flickrService.searchImages(searchParams)
    }

    companion object : SingletonHolder<RetrofitDataSource, Context>(::RetrofitDataSource)
}