package com.example.freedomhackathonapp.presentaion.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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
                viewModel.fetch("python developer")
//                viewModel.getEmpty()
//                findNavController().navigate(R.id.action_searchFragment_to_resultFragment)
            }

            viewModel.response.observe(viewLifecycleOwner) {
                // print in log
                Log.d("AAA", "it.toString()")

                Log.d("AAA", it.toString())
            }

//            viewModel.empty.observe(viewLifecycleOwner) {
//                // print in log
//                Log.d("AAA", "it.toString()")
//
//                Log.d("AAA", it.toString())
//            }
        }


    }

}