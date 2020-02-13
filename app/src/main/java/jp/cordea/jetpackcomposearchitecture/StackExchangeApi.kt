package jp.cordea.jetpackcomposearchitecture

import jp.cordea.jetpackcomposearchitecture.response.Questions
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface StackExchangeApi {
    @GET("/2.2/questions")
    fun getQuestions(
        @Query("site") site: String,
        @Query("order") order: String,
        @Query("sort") sort: String,
        @Query("tagged") tagged: String
    ): Flow<Questions>
}
