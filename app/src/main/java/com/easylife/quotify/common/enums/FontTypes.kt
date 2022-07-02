package com.easylife.quotify.common.enums

import androidx.annotation.FontRes
import com.easylife.quotify.R

enum class FontTypes(val fontName: String, @FontRes val fontRes: Int) {
    AMATIC("amatic", R.font.amatic),
    ASAP("asap", R.font.asap),
    BEAURIVAGE("beaurivage", R.font.beaurivage),
    LIBREBASKERVILLE("librebaskerville", R.font.librebaskerville),
    LOBSTER("lobster", R.font.lobster),
    MONOTON("monoton", R.font.monoton),
    OSWALD("oswald", R.font.oswald),
    RAMPARTONE("rampartone", R.font.rampartone),
    ROBOTOMONO("robotomono", R.font.robotomono),
    RUBIKMOONROCKS("rubikmoonrocks", R.font.rubikmoonrocks),
    SPEACILELITE("specialelite", R.font.specialelite),
    SPLASH("splash", R.font.splash),
    YELLOWTAIL("yellowtail", R.font.yellowtail)
}