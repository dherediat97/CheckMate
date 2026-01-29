package com.checkmate.app.model

import androidx.annotation.DrawableRes

data class Concert(
    val title: String,
    val contentDescription: String,
    @DrawableRes val ticketImage: Int? = null,
)