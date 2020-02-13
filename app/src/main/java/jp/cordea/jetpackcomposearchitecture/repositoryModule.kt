package jp.cordea.jetpackcomposearchitecture

import org.koin.dsl.module

val repositoryModule = module {
    single { QuestionRepository(get()) }
}