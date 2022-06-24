package com.easylife.quotify.data.mapper

interface DataMapper<T, R> {
    fun transform(list: List<T>): R
}