package com.checkmate.app.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.checkmate.app.state.MiExitUntilCollapsedState
import com.checkmate.app.ui.theme.CheckMateTheme

val MinToolbarHeight = 96.dp
val MaxToolbarHeight = 176.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "FrequentlyChangingValue")
@Composable
fun MainView() {
    val appToolbarHeightRange = with(LocalDensity.current) {
        MinToolbarHeight.roundToPx()..MaxToolbarHeight.roundToPx()
    }
    val toolbarState = rememberSaveable(saver = MiExitUntilCollapsedState.Saver) {
        MiExitUntilCollapsedState(heightRange = appToolbarHeightRange)
    }
    val scrollState = rememberScrollState()
    toolbarState.scrollValue = scrollState.value

//    Scaffold(topBar = {
//        AnimatedAppBar(
//            modifier = Modifier.fillMaxSize(),
//            scrollState = scrollState,
//            progress = toolbarState.progress
//        )
//    }) { innerPadding ->
//        Surface(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = innerPadding.calculateTopPadding()),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            NavHostView(navHostController)
//        }
//    }

    Scaffold(
        modifier = Modifier.fillMaxSize(), content = {
            AnimatedAppBar(
                progress = toolbarState.progress,
                modifier = Modifier.fillMaxSize(),
                scrollState = scrollState,
                columns = 1,
                list = populateList(),
            )
        })
}


@Preview(showBackground = true)
@Composable
fun MainViewPreview(
) {
    CheckMateTheme {
        MainView()
    }
}