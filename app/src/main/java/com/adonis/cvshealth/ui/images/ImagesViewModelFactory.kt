package com.adonis.cvshealth.ui.images

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adonis.cvshealth.data.retrofit.datasource.DataSource

class ImagesViewModelFactory constructor(private val repository: DataSource): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ImagesViewModel::class.java)) {
            ImagesViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}