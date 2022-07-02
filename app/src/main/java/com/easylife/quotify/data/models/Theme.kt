package com.easylife.quotify.data.models

import android.os.Parcelable
import androidx.room.Entity
import com.easylife.quotify.common.enums.ThemeTypes
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Themes")
@Parcelize
data class Theme(
    val themeId: Int,
    val backgroundUrl: String = "",
    val font: Font,
    val type: String = ThemeTypes.FREE.type,
    val isSelected: Boolean = false
): Parcelable {

    @Parcelize
    data class Font(
        val fontName: String,
        val fontSize: Int,
        val textAlign: String,
    ): Parcelable

}
