package com.example.to_do

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do.model.Categoria
import com.example.to_do.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDate
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private val _responseListCategoria =
        MutableLiveData<Response<List<Categoria>>>()

    val reponseListCategoria: LiveData<Response<List<Categoria>>> =
        _responseListCategoria

    val dataSelecionada = MutableLiveData<LocalDate>()

    init {
        dataSelecionada.value = LocalDate.now()
        listCategoria()
    }

    init {
        listCategoria()
    }


    fun listCategoria(){

        viewModelScope.launch {
            try {
                val response = repository.listCategoria()
                _responseListCategoria.value = response
            }catch (e: Exception){
                Log.d("ErroRequisicao", e.message.toString())
            }
        }
    }



}