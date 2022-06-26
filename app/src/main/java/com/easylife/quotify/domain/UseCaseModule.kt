package com.easylife.quotify.domain

import com.easylife.quotify.domain.usecase.CacheQuoteListByCategoryUseCase
import com.easylife.quotify.domain.usecase.FavQuoteUseCase
import com.easylife.quotify.domain.usecase.GetQuoteListByCategoryUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { CacheQuoteListByCategoryUseCase(get(), get(), get()) }
    single { GetQuoteListByCategoryUseCase(get(), get(), get(), get()) }
    factory { FavQuoteUseCase(get(), get()) }
}