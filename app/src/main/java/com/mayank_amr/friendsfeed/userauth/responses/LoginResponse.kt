package com.mayank_amr.friendsfeed.userauth.responses

data class LoginResponse(
    val message: List<Message>,
    val status: Int
) {
    data class Message(
        val access_token: String,
        val active: Int,
        val token_type: String,
        val user: List<User>
    ) {
        data class User(
            val active: String,
            val bio: Any?,
            val created_at: String,
            val dob: String,
            val email: String,
            val follow: Int,
            val followers: Int,
            val following: Int,
            val gender: String,
            val id: Int,
            val name: String,
            val profileImage: Any?,
            val username: String
        )
    }
}