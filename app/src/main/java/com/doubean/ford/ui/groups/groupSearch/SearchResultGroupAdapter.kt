package com.doubean.ford.ui.groups.groupSearch

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.doubean.ford.databinding.ListItemGroupBinding
import com.doubean.ford.model.GroupSearchResultGroupItem

class SearchResultGroupAdapter :
    ListAdapter<GroupSearchResultGroupItem, SearchResultGroupAdapter.ViewHolder>(
        SearchResultGroupDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemGroupBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val group = getItem(position)
        holder.bind(group)
    }

    class ViewHolder(private val binding: ListItemGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.clickListener = View.OnClickListener {
                    v: View -> binding.group?.let{navigateToGroup(it, v)} }
        }

        private fun navigateToGroup(group: GroupSearchResultGroupItem, itemView: View) {
            val direction = GroupSearchFragmentDirections.actionGroupSearchToGroupDetail(group.id)
            findNavController(itemView).navigate(direction)
        }

        fun bind(item: GroupSearchResultGroupItem) {
            binding.group = item
            binding.executePendingBindings()
        }
    }
}

class SearchResultGroupDiffCallback : DiffUtil.ItemCallback<GroupSearchResultGroupItem>() {
    override fun areItemsTheSame(
        oldItem: GroupSearchResultGroupItem,
        newItem: GroupSearchResultGroupItem,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: GroupSearchResultGroupItem,
        newItem: GroupSearchResultGroupItem,
    ): Boolean {
        return oldItem == newItem
    }
}
