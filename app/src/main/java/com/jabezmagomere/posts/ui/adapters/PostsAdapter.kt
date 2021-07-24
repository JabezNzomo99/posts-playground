package com.jabezmagomere.posts.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jabezmagomere.posts.databinding.RowPostBinding
import com.jabezmagomere.posts.ui.models.PostView

class PostsAdapter : ListAdapter<PostView, PostsAdapter.PostViewHolder>(PostDiffUtil) {

    inner class PostViewHolder(private val binding: RowPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostView) {
            binding.tvTitle.text = post.title
            binding.tvBody.text = post.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            RowPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object PostDiffUtil : DiffUtil.ItemCallback<PostView>() {
    override fun areItemsTheSame(oldItem: PostView, newItem: PostView): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostView, newItem: PostView): Boolean {
        return oldItem == newItem
    }

}