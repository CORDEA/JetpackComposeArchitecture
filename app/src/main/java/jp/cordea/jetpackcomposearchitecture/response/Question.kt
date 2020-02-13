package jp.cordea.jetpackcomposearchitecture.response

import com.squareup.moshi.Json

class Questions(
    val items: List<Question>
)

class Question(
    val owner: Owner,
    val tags: List<String>,
    @Json(name = "view_count") val viewCount: Int,
    @Json(name = "favorite_count") val favoriteCount: Int,
    @Json(name = "down_vote_count") val downVoteCount: Int,
    @Json(name = "up_vote_count") val upVoteCount: Int,
    @Json(name = "answer_count") val answerCount: Int,
    val score: Int,
    @Json(name = "last_activity_date") val lastActivityDate: Long,
    @Json(name = "creation_date") val creationDate: Long,
    @Json(name = "last_edit_date") val lastEditDate: Long,
    @Json(name = "question_id") val questionId: Long,
    val link: String,
    val title: String,
    val body: String
)

class Owner(
    val reputation: Int,
    @Json(name = "user_id") val userId: Long,
    @Json(name = "user_type") val userType: String,
    @Json(name = "accept_rate") val acceptRate: Int,
    @Json(name = "profile_image") val profileImage: String,
    @Json(name = "display_name") val displayName: String,
    val link: String
)
