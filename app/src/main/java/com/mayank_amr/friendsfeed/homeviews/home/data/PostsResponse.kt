package com.mayank_amr.friendsfeed.homeviews.home.data


data class PostsResponse(
    val links: Links,
    val message: List<Message>,
    val status: Int
) {
    data class Links(
        val current_page: Int,
        val first_page_url: String,
        val from: Int,
        val last_page: Int,
        val last_page_url: String,
        val next_page_url: String,
        val path: String,
        val per_page: Int,
        val prev_page_url: Any?,
        val to: Int,
        val total: Int
    )

    data class Message(
        val comments_count: Int,
        val created_at: String,
        val id: Int,
        val liked: Int,
        val likes_count: Int,
        val post: String,
        val post_image1: String?,
        val post_image2: Any?,
        val post_image3: Any?,
        val post_image4: Any?,
        val post_image5: Any?,
        val updated_at: String,
        val user: User,
        val user_id: Int
    ) {
        data class User(
            val id: Int,
            val name: String,
            val profileImage: Any?,
            val username: String
        )
    }
}