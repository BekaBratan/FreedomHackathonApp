package com.example.freedomhackathonapp.presentaion.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.freedomhackathonapp.R
import com.example.freedomhackathonapp.data.SharedProvider
import com.example.freedomhackathonapp.databinding.FragmentResultBinding
import com.example.freedomhackathonapp.domain.RcViewItemClickLinkCallback
import kotlin.getValue

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navOptions = NavOptions.Builder().setLaunchSingleTop(true).setRestoreState(true).build()

        val vacancy = SharedProvider(requireContext()).getSearch() + ", Навыки: " + SharedProvider(requireContext()).getSpecialization()
        if (SharedProvider(requireContext()).getFrom() == "search") {
            binding.llProgress.visibility = View.VISIBLE
            binding.llResult.visibility = View.GONE
            viewModel.fetch(vacancy)
        }

        viewModel.response.observe(viewLifecycleOwner) {

            val adapterResults = ResultAdapter()
            binding.tvResult.text = SharedProvider(requireContext()).getSearch()
            binding.rcResults.adapter = adapterResults
            adapterResults.submitList(it.data)

            binding.llProgress.visibility = View.GONE
            binding.llResult.visibility = View.VISIBLE

            adapterResults.setOnItemClickListener(object : RcViewItemClickLinkCallback {
                override fun onClick(link: String) {
                    SharedProvider(requireContext()).saveUser(it.data.first { it.resumeLink == link })
                    SharedProvider(requireContext()).setFrom("result")
                    findNavController().navigate(R.id.action_resultFragment_to_detailFragment, null, navOptions)
                }
            })
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}