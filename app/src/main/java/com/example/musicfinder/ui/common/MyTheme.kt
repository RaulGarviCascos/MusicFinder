package com.example.musicfinder.ui.common
import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object MyTheme {
    private val _isDarkTheme = MutableStateFlow(true)
    private val isDarkTheme = _isDarkTheme.asStateFlow()

    fun isDarkTheme(): StateFlow<Boolean> {
        return isDarkTheme
    }

    fun changeTheme(isDark:Boolean){
        _isDarkTheme.value = isDark
    }





}