package com.checkmate.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.checkmate.app.R
import com.checkmate.app.receiver.NfcBroadcastReceiver
import com.checkmate.app.ui.theme.CheckMateTheme

@Composable
fun TicketScreen(modifier: Modifier = Modifier) {
    var nfcCardId by remember {
        mutableStateOf("")
    }
    NfcBroadcastReceiver { tag ->
        nfcCardId = tag.id.toHexString()
    }


    Column(modifier = modifier.padding(12.dp)) {
        if (nfcCardId.isEmpty()) {
            Text(stringResource(R.string.scan_hint_nfc_card))
            //TODO REPLACE TO LOTTIE ANIMATION OR SIMILAR
            Image(
                painter = painterResource(R.drawable.ic_nfc),
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = modifier
                    .width(24.dp)
                    .size(24.dp),
                contentDescription = ""
            )
        } else {
            Text(text = "Read Card : $nfcCardId", modifier = modifier)
        }
    }


}


@Composable
@Preview(showSystemUi = true)
fun Preview() {
    CheckMateTheme {
        MainView(navHostController = rememberNavController())
    }
}