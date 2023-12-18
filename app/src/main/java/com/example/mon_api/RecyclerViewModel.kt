package com.example.mon_api
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecyclerViewModel(private val repository: WidgetRepository) : ViewModel() {

    private val _widgetData = MutableLiveData<List<Widget>>()
    val widgetData: LiveData<List<Widget>> get() = _widgetData

    fun fetchDataFromApi() {
        viewModelScope.launch {
            try {
                val apiResponse = repository.fetchDataFromApi()
                _widgetData.value = apiResponse.widgets
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error appropriately
            }
        }
    }
}
