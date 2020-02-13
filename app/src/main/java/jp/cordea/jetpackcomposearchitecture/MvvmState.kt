package jp.cordea.jetpackcomposearchitecture

import androidx.compose.Model

@Model
class MvvmState(var items: List<MvvmListItemModel> = emptyList())

class MvvmListItemModel(
    val vote: String,
    val title: String,
    val name: String
)
