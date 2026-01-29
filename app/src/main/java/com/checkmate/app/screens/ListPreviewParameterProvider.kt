package com.checkmate.app.screens

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.checkmate.app.R
import com.checkmate.app.model.Concert

class ListPreviewParameterProvider : PreviewParameterProvider<List<Concert>> {
    override val values = sequenceOf(
        populateList()
    )
}

fun populateList(): List<Concert> {
    return listOf(
        Concert(
            "Soul Night Jazz Festival",
            ticketImage = R.drawable.concert1,
            contentDescription = ""
        ),
        Concert("Jazz Session", ticketImage = R.drawable.concert2, contentDescription = ""),
        Concert("Live Music", ticketImage = R.drawable.concert3, contentDescription = ""),
        Concert("Music Fest", ticketImage = R.drawable.concert4, contentDescription = ""),
        Concert("90' Music Event", ticketImage = R.drawable.concert5, contentDescription = ""),
//        Concert("", ticketImage = R.drawable.),
//        Concert("", ticketImage = R.drawable.),
    )
}