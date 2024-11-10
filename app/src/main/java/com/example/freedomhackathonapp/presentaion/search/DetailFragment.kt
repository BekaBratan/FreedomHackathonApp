package com.example.freedomhackathonapp.presentaion.search

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.freedomhackathonapp.R
import com.example.freedomhackathonapp.data.SharedProvider
import com.example.freedomhackathonapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {

            val user = SharedProvider(requireContext()).getUser()

            btnBack.setOnClickListener {
                SharedProvider(requireContext()).clearShared()
                findNavController().navigateUp()
            }

            tvName.text = user.fullName
            tvBirthDate.text = user.birthDate
            tvCountry.text = user.country
            tvEducation.text = user.education

            if (user.conformityAssessment == "Подходит") {
                binding.circularProgressIndicator.setIndicatorColor(ContextCompat.getColor(requireContext(), R.color.green))
                binding.tvPercentAppropriate.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
            } else {
                binding.circularProgressIndicator.setIndicatorColor(ContextCompat.getColor(requireContext(), R.color.red))
                binding.tvPercentAppropriate.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
            }

            tvPercentAppropriate.text = user.percentAppropriate
            binding.circularProgressIndicator.progress = user.percentAppropriate.toString().replace("%", "").toInt()


            tvExperience.text = user.experience
            tvSkills.text = user.highSkills

            btnDownload.setOnClickListener {
                downloadPdf(requireContext(), "http://10.0.2.2:8000${user.resumeLink}", "sample")
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