package com.example.freedomhackathonapp.presentaion.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.freedomhackathonapp.R
import com.example.freedomhackathonapp.databinding.FragmentSearchBinding

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
        binding.run {

            val adapterRequirements = RequirementsAdapter()
            rcRequirements.adapter = adapterRequirements
            adapterRequirements.submitList(emptyList())

            btnAdd.setOnClickListener {
                adapterRequirements.addItem(etRequirement.text.toString())
                etRequirement.text.clear()
            }

        }
    }

}