package com.easylife.quotify.data.mapper

import org.koin.dsl.module

val mapperModule = module {
    factory { QuoteListDataMapper() }
}