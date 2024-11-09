package com.example.freedomhackathonapp.presentaion.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.freedomhackathonapp.R
import com.example.freedomhackathonapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {

            val adapterRequirements = RequirementsAdapter()
            rcRequirements.adapter = adapterRequirements
            adapterRequirements.submitList(emptyList())

            btnAdd.setOnClickListener {
                adapterRequirements.addItem(etRequirement.text.toString())
                etRequirement.text.clear()
            }

            btnSearch.setOnClickListener{
                var prompt = etSpecialization.text.toString()
                val requirements = adapterRequirements.getItems().joinToString("; ")
                if (requirements.isNotEmpty()) {
                    prompt += ". Навыки: $requirements"
                }
                Log.d("AAA", prompt)
                viewModel.fetch(prompt)
                llSearch.visibility = View.GONE
                llProgress.visibility = View.VISIBLE
            }

            viewModel.response.observe(viewLifecycleOwner) {
                llProgress.visibility = View.GONE
                llResults.visibility = View.VISIBLE
                val adapterResults = ResultAdapter()
                binding.rcResults.adapter = adapterResults
                adapterResults.submitList(it.result)
            }

        }


    }

}