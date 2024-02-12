package com.rodrigocopetti.thedogshow.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigocopetti.thedogshow.data.network.NetworkOperation
import com.rodrigocopetti.thedogshow.data.network.OperationStatus
import com.rodrigocopetti.thedogshow.data.network.RetrofitInstance
import com.rodrigocopetti.thedogshow.data.network.models.BreedImages
import com.rodrigocopetti.thedogshow.data.network.models.toList
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * View Model that connects the network API with the app's UI
 */
class BreedViewModel: ViewModel() {
    private val apiService = RetrofitInstance.api

    // Get list of breeds
    private val _breedList = MutableStateFlow(emptyList<String>())
    val breedList: StateFlow<List<String>> = _breedList.asStateFlow()

    private val _breedListStatus = MutableStateFlow(NetworkOperation())
    val breedListStatus: StateFlow<NetworkOperation> = _breedListStatus.asStateFlow()
    fun getListOfBreeds() {
        val errorHandler = CoroutineExceptionHandler { _, exception ->
            _breedListStatus.value = NetworkOperation(exception)
        }

        _breedListStatus.value = NetworkOperation(status = OperationStatus.IN_PROGRESS)

        viewModelScope.launch(errorHandler) {
            _breedList.value = apiService.getBreedList().message.toList()
            _breedListStatus.value = NetworkOperation(status = OperationStatus.STOPPED)
        }
    }

    // Get images of a breed
    private val _breedImages = MutableStateFlow<BreedImages?>(null)
    val breedImages: StateFlow<BreedImages?> = _breedImages.asStateFlow()

    private val _breedImagesStatus = MutableStateFlow(NetworkOperation())
    val breedImagesStatus: StateFlow<NetworkOperation> = _breedImagesStatus.asStateFlow()
    fun getListOfBreedImages(breed: String, numberOfImages: Int = 10) {
        val errorHandler = CoroutineExceptionHandler { _, exception ->
            _breedImagesStatus.value = NetworkOperation(exception)
        }

        clearListOfBreedImages()
        _breedImagesStatus.value = NetworkOperation(status = OperationStatus.IN_PROGRESS)

        viewModelScope.launch(errorHandler) {
            val images = apiService.getRandomBreedImages(
                breed = breed,
                numberOfImages = numberOfImages
            ).message
            _breedImages.value = BreedImages(images, breed)
            _breedImagesStatus.value = NetworkOperation(status = OperationStatus.STOPPED)
        }
    }

    fun clearListOfBreedImages(){
        _breedImagesStatus.value = NetworkOperation(status = OperationStatus.STOPPED)
        _breedImages.value = null
    }
}