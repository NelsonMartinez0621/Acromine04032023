package com.example.acromine04032023

import android.app.Activity
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.acromine04032023.adapter.AcronymAdapter
import com.example.acromine04032023.data.models.AcronymResponseItem
import com.example.acromine04032023.databinding.ActivityMainBinding
import com.example.acromine04032023.utils.UIState
import com.example.acromine04032023.viewmodel.AcronymViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val acronymViewModel by lazy {
        ViewModelProvider(this)[AcronymViewModel::class.java]
    }

    private val acronymAdapter by lazy {
        AcronymAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.acronymViewModel = acronymViewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        binding.acronymRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = acronymAdapter
        }
        binding.apply {
            edInput.addTextChangedListener(object :TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {}

            })
        }
        acronymViewModel.getMeaning("FBI")
    }
}