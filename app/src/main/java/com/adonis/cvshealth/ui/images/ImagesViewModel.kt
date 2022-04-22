package com.adonis.cvshealth.ui.images

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adonis.cvshealth.data.retrofit.datasource.DataSource
import com.adonis.cvshealth.models.image.ImageModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImagesViewModel(private val dataSource: DataSource): ViewModel() {

    val working = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val images = MutableLiveData<List<ImageModel>>()


    fun searchImages(params: String ) {
        working.postValue(true)

        CoroutineScope(Dispatchers.IO).launch {
            val response = dataSource.searchImages(params)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val list = response.body()?.items

                    if(list.isNullOrEmpty()){
                        errorMessage.postValue("Empty")
                    } else {
                        list.let {
                            images.postValue(it)
                        }
                    }
                } else {
                    errorMessage.postValue(response.errorBody().toString())
                }
                working.postValue(false)
            }
        }
    }

    fun reset() {
        images.postValue(mutableListOf())
    }

}