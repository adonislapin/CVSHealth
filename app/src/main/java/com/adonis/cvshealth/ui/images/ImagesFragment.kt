package com.adonis.cvshealth.ui.images

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.adonis.cvshealth.R
import com.adonis.cvshealth.adapters.ImagesAdapter
import com.adonis.cvshealth.data.preferences.SharedPreferencesManager
import com.adonis.cvshealth.data.retrofit.datasource.RetrofitDataSource
import com.adonis.cvshealth.databinding.FragmentImagesBinding
import com.adonis.cvshealth.interfaces.IDetailImage
import com.adonis.cvshealth.models.image.ImageModel
import com.adonis.cvshealth.utils.Utils.Companion.LAST_SEARCH_KEY

class ImagesFragment : Fragment(), IDetailImage {

    private var binding : FragmentImagesBinding? = null
    private lateinit var retrofitDataSource: RetrofitDataSource
    private lateinit var sharedPreferencesManager: SharedPreferencesManager
    private lateinit var model: ImagesViewModel
    private var adapter : ImagesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        context?.let {
            retrofitDataSource = RetrofitDataSource.getInstance(it)
            sharedPreferencesManager = SharedPreferencesManager.getInstance(it)
        }

        setHasOptionsMenu(true)

        model = ViewModelProvider(
            this,
            ImagesViewModelFactory(retrofitDataSource)
        )[ImagesViewModel::class.java]
        model.working.observe(this.viewLifecycleOwner, progressObserver)
        model.images.observe(this.viewLifecycleOwner, imagesObserver)

        binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()

        val lastSearch = sharedPreferencesManager.getValue(LAST_SEARCH_KEY)
        if (lastSearch != "0") {
            binding?.labelEmpty?.visibility = View.GONE
            model.searchImages(lastSearch)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.menu_search)
        if(searchItem != null) {
            val searchView = searchItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d("COSA", "Ready to sent from fragment")
                    query?.let {
                        model.searchImages(it)
                        binding?.labelEmpty?.visibility = View.GONE
                        sharedPreferencesManager.setValue(LAST_SEARCH_KEY, it)
                    }
                    hideKeyboard(searchView)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.d("COSA", "User typing fragment...")
                    return true
                }
            })
        }
    }

    private val progressObserver =
        Observer<Boolean> { isWorking ->
            binding?.progressBar?.isIndeterminate = isWorking
            binding?.progressBar?.visibility = if (isWorking) View.VISIBLE else View.GONE
        }

    private val imagesObserver =
        Observer<List<ImageModel>> { images ->
            if (images.isEmpty()) {
                binding?.labelEmpty?.visibility = View.VISIBLE
            } else {
                val list = images.toMutableList()
                val layoutManager = GridLayoutManager(activity, 2)
                adapter = ImagesAdapter(mutableListOf(), this)
                binding?.recyclerViewImages?.layoutManager = layoutManager
                binding?.recyclerViewImages?.adapter = adapter
                adapter?.setData(list)
                binding?.labelEmpty?.visibility = View.GONE
            }
        }

    override fun onPause() {
        model.reset()
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun selectImage(info: ImageModel) {
        val bundle = Bundle()
        bundle.putParcelable("dataImageModel", info)
        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment, bundle)
    }

    fun hideKeyboard(view: View) {
        view.let {
            val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}