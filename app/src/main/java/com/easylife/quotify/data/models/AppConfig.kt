package com.easylife.quotify.data.models

import com.easylife.quotify.common.AppConstant

data class AppConfig(
    val category: String = AppConstant.DEFAULT_CATEGORY,
    val isPremiumSellingEnabled: Boolean = AppConstant.DEFAULT_IS_PREMIUM_SELLING_ENABLED,
    val shouldReset: Boolean = AppConstant.DEFAULT_SHOULD_RESET,
    val adsPerQuote: Int = AppConstant.ADS_PER_QUOTE
)