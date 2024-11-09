package com.example.freedomhackathonapp.presentaion.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.freedomhackathonapp.databinding.ItemRequirementsBinding
import com.example.freedomhackathonapp.databinding.ItemResultBinding
import com.example.freedomhackathonapp.domain.SearchResponse
import okhttp3.Response

class ResultAdapter: RecyclerView.Adapter<ResultAdapter.MyViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<SearchResponse.Result.Candidate>() {
        override fun areItemsTheSame(
            oldItem: SearchResponse.Result.Candidate,
            newItem: SearchResponse.Result.Candidate
        ): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(
            oldItem: SearchResponse.Result.Candidate,
            newItem: SearchResponse.Result.Candidate
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<SearchResponse.Result.Candidate>) {
        differ.submitList(list.toMutableList())
    }


    inner class MyViewHolder(private var binding: ItemResultBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(item: SearchResponse.Result.Candidate){
            binding.tvName.text = item.content.toString()
            binding.tvDescription.text = item.finishReason
            itemView.setOnClickListener {

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