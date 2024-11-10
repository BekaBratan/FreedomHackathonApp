package com.example.freedomhackathonapp.presentaion.search

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.freedomhackathonapp.R
import androidx.navigation.fragment.findNavController
import com.example.freedomhackathonapp.data.SharedProvider
import com.example.freedomhackathonapp.databinding.FragmentSearchBinding
import com.example.freedomhackathonapp.domain.RcViewItemClickLinkCallback

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterRequirements = RequirementsAdapter()
        binding.rcRequirements.adapter = adapterRequirements
        adapterRequirements.submitList(emptyList())

        binding.btnAdd.setOnClickListener {
            adapterRequirements.addItem(binding.etRequirement.text.toString())
            binding.etRequirement.text.clear()
        }

        binding.btnSearch.setOnClickListener {
            var vacancy = binding.etSpecialization.text.toString()
            var requirements = ""
            if (adapterRequirements.getItems().isNotEmpty())
                requirements += adapterRequirements.getItems().joinToString("; ")
            Log.d("AAA", vacancy)
            SharedProvider(requireContext()).saveSearch(vacancy, requirements, "search")
            val bundle = Bundle().apply {
                putInt("search", 1)
            }
            findNavController().navigate(R.id.action_searchFragment_to_resultFragment, bundle)
        }
    }

}