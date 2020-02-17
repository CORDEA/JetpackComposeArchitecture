package jp.cordea.jetpackcomposearchitecture

import androidx.compose.Model
import jp.cordea.jetpackcomposearchitecture.response.Questions

@Model
class MvvmState(var items: List<MvvmListItemModel> = emptyList())

class MvvmListItemModel(
    val id: Long,
    val link: String,
    val vote: String,
    val title: String,
    val name: String
) {
    companion object {
        fun from(questions: Questions) =
            questions.items.map {
                MvvmListItemModel(
                    it.questionId,
                    it.link,
                    it.score.toString(),
                    it.title,
                    it.owner.displayName
                )
            }
    }
}
