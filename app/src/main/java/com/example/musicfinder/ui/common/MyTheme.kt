package com.example.musicfinder.ui.common
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object MyTheme {
    private val _isDarkTheme = MutableStateFlow(false)
    private val isDarkTheme = _isDarkTheme.asStateFlow()

    fun isDarkTheme(): StateFlow<Boolean> {
        return isDarkTheme
    }

    fun changeAndReturnTheme(isDark:Boolean): StateFlow<Boolean> {
        _isDarkTheme.value = isDark
        return isDarkTheme
    }
    fun changeTheme(isDark:Boolean){
        _isDarkTheme.value = isDark
    }





}