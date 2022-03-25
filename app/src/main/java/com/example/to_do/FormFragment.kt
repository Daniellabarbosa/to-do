package com.example.to_do

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.to_do.Fragment.DatePickerFragment
import com.example.to_do.Fragment.TimePickerListener
import com.example.to_do.databinding.FragmentFormBinding
import com.example.to_do.model.Categoria
import java.time.LocalDate

class FormFragment : Fragment(), TimePickerListener {


    private lateinit var binding: FragmentFormBinding


    private val mainViewModel: MainViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFormBinding.inflate(layoutInflater, container, false)

        mainViewModel.listCategoria()

        mainViewModel.reponseListCategoria.observe(viewLifecycleOwner, {
                response -> Log.d("Requisicao", response.body().toString())
                spinnerCategoria(response.body())
        })

        mainViewModel.dataSelecionada.observe(viewLifecycleOwner,{
            selectedDate -> binding.editData.setText(selectedDate.toString())
        })

        binding.buttonSalvar.setOnClickListener {
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        }

        binding.editData.setOnClickListener{
            DatePickerFragment(this)
                .show(parentFragmentManager, "DatePicker")
        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onTimeSelected(date: LocalDate) {
       mainViewModel.dataSelecionada.value = date
    }

}

fun spinnerCategoria(categorias: List <Categoria>?){



if (categorias !=null){

    binding.spinnerCategoria.adapter = ArrayAdapter(
    requireContext(),
    R.layout.support_simple_spinner_dropdown_item,
    categorias

       )
   }
}


