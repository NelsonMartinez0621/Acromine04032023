package com.example.acromine04032023.data.models

import com.squareup.moshi.Json

data class Var(
    @Json(name = "lf")
    var lf    : String? = null,
    @Json(name = "freq")
    var freq  : Int?    = null,
    @Json(name = "since")
    var since : Int?    = null
)
