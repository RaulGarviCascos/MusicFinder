package com.example.musicfinder.ui.common

import SettingsAnimation
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(menuIsVisible:MutableState<Boolean>,menuLogin:MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE0E0E0))
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Music Finder",
                        fontSize = 20.sp,

                        style  = MaterialTheme.typography.titleLarge
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = { if(!menuLogin.value) menuIsVisible.value = !menuIsVisible.value }) {
                    Icon(
                        imageVector = if (menuIsVisible.value) Icons.Filled.ArrowBack else Icons.Filled.Menu,
                        contentDescription = "Menu Settings",
                    )
                }
            },
            actions = {
                IconButton(onClick = { if(!menuIsVisible.value) menuLogin.value = !menuLogin.value }) {
                    Icon(
                        imageVector = if(menuLogin.value) Icons.Filled.ArrowForward else Icons.Filled.Person,
                        contentDescription = "Menu Login"
                    )
                }
            }, modifier = Modifier.fillMaxWidth()
        )
    }



}

