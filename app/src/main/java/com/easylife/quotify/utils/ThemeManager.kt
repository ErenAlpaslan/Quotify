package com.easylife.quotify.utils

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.easylife.quotify.common.enums.FontTypes
import com.easylife.quotify.common.enums.TextAlignTypes
import com.easylife.quotify.data.models.Theme

object ThemeManager {

    fun createTypographyFromFont(font: Theme.Font): Typography {
        val chosenFontType = FontTypes.values().toList().firstOrNull { it.fontName == font.fontName }
        val typography: Typography
        if (chosenFontType != null) {
            typography = Typography(
                bodyLarge = TextStyle(
                    fontSize = font.fontSize.sp,
                    fontFamily = FontFamily(Font(chosenFontType.fontRes)),
                    textAlign = when(font.textAlign) {
                        TextAlignTypes.LEFT.type -> TextAlign.Start
                        TextAlignTypes.RIGHT.type -> TextAlign.End
                        else -> TextAlign.Center
                    }
                )
            )
        }else {
            typography = Typography(
                bodyLarge = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.5.sp
                )
            )
        }
        return typography
    }

}