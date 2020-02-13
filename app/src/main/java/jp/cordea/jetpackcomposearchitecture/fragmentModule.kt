package jp.cordea.jetpackcomposearchitecture

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fragmentModule = module {
    viewModel { MvvmViewModel(get()) }
}