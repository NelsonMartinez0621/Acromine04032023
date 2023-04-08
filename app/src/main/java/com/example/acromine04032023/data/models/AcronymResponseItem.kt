package com.example.acromine04032023.data.models

import com.squareup.moshi.Json
import java.util.Objects

data class AcronymResponseItem(
    @Json(name = "lfs")
    val lfs: List<Lf>? = null,
    @Json(name = "sf")
    val sf: String? = null
)
