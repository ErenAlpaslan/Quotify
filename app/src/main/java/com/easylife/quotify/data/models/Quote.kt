package com.easylife.quotify.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Quotes")
@Parcelize
data class Quote(
    @PrimaryKey
    val id: Int = 0,
    @SerializedName("Author")
    val author: String?,
    @SerializedName("Category")
    val category: String? = "",
    @SerializedName("Quote")
    val quote: String? = "",
    var isFavorite: Boolean = false,
    var isCollected: Boolean = false,
    var isShown: Boolean = false
): Parcelable
