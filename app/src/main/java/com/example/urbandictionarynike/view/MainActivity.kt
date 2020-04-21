package com.example.urbandictionarynike.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.urbandictionarynike.databinding.ActivityMainBinding
import com.example.urbandictionarynike.viewmodel.UrbanViewModel

class MainActivity : AppCompatActivity() {

    val urbanViewModel by viewModels<UrbanViewModel>()

    private lateinit var binding: ActivityMainBinding
    val tabletController: NavController by lazy {
        binding.fragDetailContainer.findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
    }
}