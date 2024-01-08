package com.humxa.galleryapp.feature.media.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.humxa.galleryapp.R

@Composable
fun EmptyStateScreen(onRetryClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_nothing),
                contentDescription = "Empty Image",
                modifier = Modifier.size(150.dp)
            )

            Text(
                text = stringResource(id = R.string.label_could_not_find_anything),
                style = TextStyle(color = Color.Gray, fontSize = 14.sp),
                modifier = Modifier.padding(top = 16.dp)
            )
            Button(
                onClick = { onRetryClicked() },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(stringResource(id = R.string.btn_retry))
            }
        }
    }
}


@Preview
@Composable
fun PreviewEmptyScreen() {
    EmptyStateScreen {

    }
}