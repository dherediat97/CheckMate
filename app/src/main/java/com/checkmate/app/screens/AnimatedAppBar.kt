package com.checkmate.app.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.checkmate.app.R
import com.checkmate.app.model.Concert
import com.checkmate.app.ui.theme.Primary
import com.checkmate.app.ui.theme.Secondary
import com.checkmate.app.ui.theme.White

@SuppressLint("LocalContextResourcesRead")
@OptIn(ExperimentalMotionApi::class)
@Composable
fun AnimatedAppBar(
    list: List<Concert>,
    columns: Int,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    progress: Float,
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene).readBytes().decodeToString()
    }

    val chunkedList = remember(list, columns) {
        list.chunked(columns)
    }


    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = modifier
            .fillMaxSize()
            .background(White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.gradient),
            contentDescription = "Toolbar Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .layoutId("collapsing_box")
                .fillMaxWidth()
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Primary, Secondary),
                        startY = size.height / 3,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                },
            alignment = BiasAlignment(0f, 1f - ((1f - progress) * 0.50f)),
        )
        Text(
            text = stringResource(R.string.app_name),
            color = Primary,
            modifier = Modifier
                .layoutId("motion_text")
                .zIndex(1f),
            fontSize = 16.sp
        )

        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .layoutId("data_content")
                .background(White),
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(contentPadding.calculateTopPadding())
            )

            chunkedList.forEach { chunk ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {

                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(contentPadding.calculateStartPadding(LocalLayoutDirection.current))
                    )

                    chunk.forEach { listItem ->
                        GridConcertCard(
                            concertItem = listItem, modifier = Modifier
                                .padding(2.dp)
                                .weight(1f)
                        )
                    }

                    val emptyCells = columns - chunk.size
                    if (emptyCells > 0) {
                        Spacer(modifier = Modifier.weight(emptyCells.toFloat()))
                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(contentPadding().calculateEndPadding(LocalLayoutDirection.current))
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            )
        }

    }
}
