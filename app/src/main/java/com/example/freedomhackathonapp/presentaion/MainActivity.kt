package com.example.freedomhackathonapp.presentaion

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.freedomhackathonapp.R
import com.example.freedomhackathonapp.databinding.ActivityMainBinding
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding!!.bottomNavbar.setupWithNavController(navController)

        lifecycleScope.launch {
            generate()
        }

    }

    private suspend fun generate() {
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = "AIzaSyCauwLFrxTgYXHwUy2R9YyZxh-khfhnPTU"
        )
        val prompt = "Write a story about a magic backpack."
        val response = generativeModel.generateContent(prompt)
        print(response.text)
    }
}