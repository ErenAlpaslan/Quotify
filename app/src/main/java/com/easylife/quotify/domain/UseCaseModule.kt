package com.easylife.quotify.domain

import com.easylife.quotify.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory { CacheQuoteListByCategoryUseCase(get(), get(), get()) }
    single { GetQuoteListByCategoryUseCase(get(), get(), get(), get()) }
    factory { FavQuoteUseCase(get(), get()) }
    factory { GetFavoritesUseCase(get(), get()) }
    factory { UnFavoriteUseCase(get(), get()) }
}