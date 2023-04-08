package com.example.acromine04032023.data.models

import com.squareup.moshi.Json

data class Lf (
    @Json(name = "freq")
    var freq  : Int?    = null,
    @Json(name = "lf")
    var lf    : String? = null,
    @Json(name = "since")
    var since : Int? = null,
    @Json(name = "vars")
    var vars: List<Var?>? = null,
)