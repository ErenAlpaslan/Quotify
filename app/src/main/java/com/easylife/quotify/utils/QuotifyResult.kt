package com.easylife.quotify.utils

sealed class QuotifyResult <T> {
    data class Success <T> (val data: T?): QuotifyResult<T>()
    data class Error <T> (val message: String?) : QuotifyResult<T>()
}