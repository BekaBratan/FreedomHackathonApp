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
import com.example.freedomhackathonapp.presentaion.RcViewItemClickIdCallback
import okhttp3.Response

class ResultAdapter: RecyclerView.Adapter<ResultAdapter.MyViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<SearchResponse.Result>() {
        override fun areItemsTheSame(
            oldItem: SearchResponse.Result,
            newItem: SearchResponse.Result
        ): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(
            oldItem: SearchResponse.Result,
            newItem: SearchResponse.Result
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<SearchResponse.Result>) {
        differ.submitList(list.toMutableList())
    }

    private var listenerClickAtItem: RcViewItemClickIdCallback? = null
    fun setOnItemClickListener(listener: RcViewItemClickIdCallback) {
        this.listenerClickAtItem = listener
    }


    inner class MyViewHolder(private var binding: ItemResultBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(item: SearchResponse.Result){
            binding.tvDescription.text = item.candidates[0].content.parts[0].text
            itemView.setOnClickListener {
                listenerClickAtItem?.onClick(item.candidates[0].index)
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