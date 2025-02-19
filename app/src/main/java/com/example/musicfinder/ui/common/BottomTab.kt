package com.example.musicfinder.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.musicfinder.R


@Composable
fun BottomTab(isListen:Boolean,isList:Boolean,onClickListen :()-> Unit,onClickHistorical:() -> Unit, modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(unbounded = true)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(unbounded = true),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ButtonTabBar(isThis = isListen, icon_name = R.drawable.ic_microphone, label = stringResource(id = R.string.bottom_bar_listen), onClick = onClickListen)
            ButtonTabBar(isThis = isList, icon_name = R.drawable.ic_list, label = stringResource(id = R.string.bottom_bar_historical), onClick =onClickHistorical)

        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewTab(){
   BottomTab(isListen = true,isList = false, onClickListen = {}, onClickHistorical = {})
}

