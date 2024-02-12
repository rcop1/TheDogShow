package com.rodrigocopetti.thedogshow.data.network

import com.rodrigocopetti.thedogshow.data.network.models.BreedImagesResponse
import com.rodrigocopetti.thedogshow.data.network.models.BreedListResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Network API logic
 */
interface ApiService {
    @GET("api/breeds/list/all")
    suspend fun getBreedList(): BreedListResponse

    @GET("api/breed/{breed}/images/random/{numberOfImages}")
    suspend fun getRandomBreedImages(@Path("breed") breed: String,
                                @Path("numberOfImages") numberOfImages: Int): BreedImagesResponse
}

object RetrofitInstance {
    private const val BASE_URL = "https://dog.ceo/"
    val api: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}