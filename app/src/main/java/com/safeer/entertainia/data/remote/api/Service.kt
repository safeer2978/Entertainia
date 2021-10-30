package com.safeer.entertainia.data.remote.api

import com.safeer.entertainia.model.Post
import retrofit2.Response
import retrofit2.http.GET


interface Service {

    @GET("api.json")
    suspend fun getPosts(): Response<List<Post>>

    companion object {
        const val FOODIUM_API_URL = "https://raw.githubusercontent.com/safeer2978/Entertainia/main/"
    }
}
