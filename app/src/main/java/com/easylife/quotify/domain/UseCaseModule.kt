package com.easylife.quotify.domain

import com.easylife.quotify.domain.usecase.GetQuoteListDataUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetQuoteListDataUseCase(get(), get(), get(), get(), get()) }
}