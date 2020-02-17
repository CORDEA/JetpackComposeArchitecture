package jp.cordea.jetpackcomposearchitecture

import androidx.compose.Model
import jp.cordea.jetpackcomposearchitecture.response.Id

@Model
class MvvmState(var items: List<MvvmListItemState> = emptyList())

@Model
class MvvmListItemState(
    override val id: Id,
    override val link: String,
    override val vote: String,
    override val title: String,
    override val name: String,
    var isChecked: Boolean = false
) : MvvmListItemModel
