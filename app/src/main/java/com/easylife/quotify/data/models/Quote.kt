package com.easylife.quotify.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Quotes")
@Parcelize
data class Quote(
    @PrimaryKey
    var id: String = "",
    val Author: String? = "",
    val Category: String? = "",
    val Quote: String? = "",
    val popularity: Long? = null,
    var isFavorite: Boolean = false,
    var isCollected: Boolean = false,
    var isShown: Boolean = false
): Parcelable
