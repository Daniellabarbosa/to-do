package com.example.to_do

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.resources.Compatibility.Api21Impl.inflate
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.to_do.databinding.FragmentFormBinding

import com.example.to_do.repository.Repository

class FormFragment : Fragment() {

    private lateinit var binding: Fragment


    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = Fragment.inflate(layoutInflater, container, false)

        mainViewModel.listCategoria()

        mainViewModel.reponseListCategoria.observe(viewLifecycleOwner){
                response -> Log.d("Requisicao", response.body().toString())
        }

        binding.buttonSalvar.setOnClickListener {
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        }

        return binding.root
    }

}