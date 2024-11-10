package com.example.freedomhackathonapp.presentaion.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.freedomhackathonapp.R
import com.example.freedomhackathonapp.databinding.ItemResultBinding
import com.example.freedomhackathonapp.domain.NewSearchResponse
import com.example.freedomhackathonapp.domain.RcViewItemClickLinkCallback

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.MyViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<NewSearchResponse.Data>() {
        override fun areItemsTheSame(
            oldItem: NewSearchResponse.Data,
            newItem: NewSearchResponse.Data
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: NewSearchResponse.Data,
            newItem: NewSearchResponse.Data
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<NewSearchResponse.Data>) {
        differ.submitList(list.toMutableList())
    }

    private var listenerClickAtItem: RcViewItemClickLinkCallback? = null
    fun setOnItemClickListener(listener: RcViewItemClickLinkCallback) {
        this.listenerClickAtItem = listener
    }

    inner class MyViewHolder(private var binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: NewSearchResponse.Data) {
            binding.tvName.text = item.fullName
            binding.tvCountry.text = item.country
            binding.tvHighSkills.text = item.highSkills

            if (item.conformityAssessment == "Подходит") {
                binding.circularProgressIndicator.setIndicatorColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.green
                    )
                )
            } else {
                binding.circularProgressIndicator.setIndicatorColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.red
                    )
                )
            }

            binding.circularProgressIndicator.progress =
                item.percentAppropriate.toString().replace("%", "").toInt()
            binding.tvPercentAppropriate.text = item.percentAppropriate.toString()
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