package com.easylife.quotify.data.mapper

interface DataMapper<T, R> {
    fun transform(t: T?): R
}