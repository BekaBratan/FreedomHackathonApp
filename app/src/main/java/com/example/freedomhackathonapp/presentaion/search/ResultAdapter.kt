package com.example.freedomhackathonapp.presentaion.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.freedomhackathonapp.R
import com.example.freedomhackathonapp.databinding.ItemResultBinding
import com.example.freedomhackathonapp.domain.SearchResponseItem
import com.example.freedomhackathonapp.presentaion.RcViewItemClickLinkCallback

class ResultAdapter: RecyclerView.Adapter<ResultAdapter.MyViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<SearchResponseItem>() {
        override fun areItemsTheSame(
            oldItem: SearchResponseItem,
            newItem: SearchResponseItem
        ): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(
            oldItem: SearchResponseItem,
            newItem: SearchResponseItem
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<SearchResponseItem>) {
        differ.submitList(list.toMutableList())
    }

    private var listenerClickAtItem: RcViewItemClickLinkCallback? = null
    fun setOnItemClickListener(listener: RcViewItemClickLinkCallback) {
        this.listenerClickAtItem = listener
    }


    inner class MyViewHolder(private var binding: ItemResultBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(item: SearchResponseItem){
            binding.tvName.text = item.full_name

            binding.tvBirthDate.text = item.birth_date
            binding.tvCountry.text = item.country
            binding.tvEducation.text = item.education

            binding.tvExperience.text = item.experience
            binding.tvHighSkills.text = item.high_skills

            if (item.conformity_assessment == "Подходит") {
                binding.circularProgressIndicator.setIndicatorColor(ContextCompat.getColor(itemView.context, R.color.green))
            } else {
                binding.circularProgressIndicator.setIndicatorColor(ContextCompat.getColor(itemView.context, R.color.red))
            }

            binding.circularProgressIndicator.progress = item.percent_appropriate.toString().replace("%", "").toInt()
            binding.tvPercentAppropriate.text = item.percent_appropriate.toString()

            binding.btnDownload.setOnClickListener {
                listenerClickAtItem?.onClick(item.resume_link)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(differ.currentList[position])
    }
}