package jp.cordea.jetpackcomposearchitecture

class QuestionRepository(
    private val api: StackExchangeApi
) {
    fun find(tag: String) = api.getQuestions(
        tagged = tag,
        site = "stackoverflow",
        order = "desc",
        sort = "creation"
    )
}
