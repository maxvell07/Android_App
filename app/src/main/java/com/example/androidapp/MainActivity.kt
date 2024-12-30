package com.example.androidapp

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var viewmodel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        viewmodel = (application as JokeApp).viewModel
        setContentView(binding.root)

        binding.button.setOnClickListener{
            binding.button.isEnabled = false
            binding.progressbar.visibility = View.VISIBLE
            viewmodel.getJoke()
        }
        viewmodel.init(object : TextCallback {
            override fun provideText(text:String) =runOnUiThread{
                binding.button.isEnabled = true
                binding.progressbar.visibility = View.INVISIBLE
                binding.text.text = text
            }
        })

    }


    override fun onDestroy() {
        super.onDestroy()
        viewmodel.clear()
    }
}