package com.mayank_amr.friendsfeed.homeviews.home.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mayank_amr.friendsfeed.R
import com.mayank_amr.friendsfeed.databinding.PostItemBinding
import com.mayank_amr.friendsfeed.homeviews.home.data.PostsResponse

/**
 * @Project Friendsfeed
 * @Created_by Mayank Kumar on 02-04-2021 09:28 PM
 */
class HomePostAdapter :
    PagingDataAdapter<PostsResponse.Message, HomePostAdapter.PostViewHolder>(POST_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class PostViewHolder(private val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostsResponse.Message) {
            binding.apply {
                binding.post = post

//                userFullNameTv.text = post.user.name
//                userNameTv.text = post.user.username

                Glide.with(itemView)
                    .load(post.post_image1)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_image)
                    .into(postImageIv)

                Glide.with(itemView)
                    .load(post.user.profileImage)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_person)
                    .into(userPicIv)
            }
        }
    }


    companion object {
        private val POST_COMPARATOR = object : DiffUtil.ItemCallback<PostsResponse.Message>() {
            override fun areItemsTheSame(
                oldItem: PostsResponse.Message,
                newItem: PostsResponse.Message
            ) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: PostsResponse.Message,
                newItem: PostsResponse.Message
            ) =
                oldItem == newItem
        }
    }
}