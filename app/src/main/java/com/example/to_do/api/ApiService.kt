package com.example.to_do.api

import com.example.to_do.model.Categoria
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("category")
    suspend fun listCategoria(): Response<List<Categoria>>

}