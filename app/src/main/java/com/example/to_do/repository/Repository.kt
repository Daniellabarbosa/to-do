package com.example.to_do.repository

import com.example.to_do.api.RetrofitInstance
import com.example.to_do.model.Categoria
import retrofit2.Response

class Repository {

    suspend fun listCategoria(): Response<List<Categoria>>{
        return RetrofitInstance.api.listCategoria()
    }

}