package com.example.freedomhackathonapp.presentaion.search

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.freedomhackathonapp.R
import com.example.freedomhackathonapp.databinding.FragmentSearchBinding
import com.example.freedomhackathonapp.domain.RcViewItemClickLinkCallback
import java.io.File

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
//                downloadPdf(requireContext(), "http://10.0.2.2:8000/static/resumes/Резюме_Mobile_developer_Assanalikhan_Mamyrov_от_21_10_2024_15_43.pdf", "sample")

                var vacancy = etSpecialization.text.toString()
                val requirements = adapterRequirements.getItems().joinToString("; ")
                if (requirements.isNotEmpty()) {
                    vacancy += ". Навыки: $requirements"
                }
                Log.d("AAA", vacancy)
                viewModel.fetch(vacancy)
                llSearch.visibility = View.GONE
                llProgress.visibility = View.VISIBLE
            }

            viewModel.response.observe(viewLifecycleOwner) {
                llProgress.visibility = View.GONE
                llResults.visibility = View.VISIBLE
                val adapterResults = ResultAdapter()
                binding.rcResults.adapter = adapterResults
                adapterResults.submitList(it.data)
                adapterResults.setOnItemClickListener(object : RcViewItemClickLinkCallback {
                    override fun onClick(link: String) {
                        downloadPdf(requireContext(), "http://10.0.2.2:8000${link}", "sample")
                    }
                })
            }
        }
    }

    fun downloadPdf(context: Context, url: String, fileName: String) {
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle("Downloading PDF...")
            .setDescription("Downloading $fileName")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setAllowedOverMetered(true)  // Allows download over mobile data
            .setAllowedOverRoaming(false)  // Disables download when roaming
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "$fileName.pdf")

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)

        Toast.makeText(context, "Download started...", Toast.LENGTH_SHORT).show()
    }

}