package jp.cordea.jetpackcomposearchitecture

import jp.cordea.jetpackcomposearchitecture.response.Questions

interface MvvmListItemModel {
    val id: Long
    val link: String
    val vote: String
    val title: String
    val name: String
}

class MvvmListItemModelImpl(
    override val id: Long,
    override val link: String,
    override val vote: String,
    override val title: String,
    override val name: String
) : MvvmListItemModel {
    companion object {
        fun from(questions: Questions): List<MvvmListItemModel> =
            questions.items.map {
                MvvmListItemModelImpl(
                    it.questionId,
                    it.link,
                    it.score.toString(),
                    it.title,
                    it.owner.displayName
                )
            }
    }
}

fun List<MvvmListItemModel>.toState() =
    map { MvvmListItemState(it.id, it.link, it.vote, it.title, it.name) }
