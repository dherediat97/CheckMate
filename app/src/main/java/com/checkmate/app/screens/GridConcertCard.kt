package com.checkmate.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.checkmate.app.model.Concert
import com.checkmate.app.ui.theme.CheckMateTheme
import com.checkmate.app.ui.theme.Primary

@Composable
fun GridConcertCard(
    concertItem: Concert,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.aspectRatio(1.8f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            concertItem.ticketImage?.let { painterResource(it) }?.let {
                Image(
                    painter = it,
                    contentDescription = concertItem.contentDescription,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                )
            }
            BottomBar(concertItem.title)
        }
    }
}

@Composable
private fun BoxScope.BottomBar(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.10f)
            .background(Primary)
            .align(Alignment.BottomCenter)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GridItemCardPreview(
    @PreviewParameter(ListPreviewParameterProvider::class) list: List<Concert>
) {
    CheckMateTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            list.take(1).forEach {
                GridConcertCard(
                    concertItem = it,
                    modifier = Modifier
                        .padding(2.dp)
                        .weight(1f)
                        .wrapContentHeight()
                )
            }
        }
    }
}