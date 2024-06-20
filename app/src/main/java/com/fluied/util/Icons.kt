package com.fluied.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.fluied.R

object Icons {

    @Composable
    fun arrowBack() = R.drawable.ic_arrow_back.vector()
    @Composable
    fun arrowForward() = R.drawable.ic_arrow_forward.vector()
    @Composable
    fun bookMark() = R.drawable.ic_bookmark.vector()
    @Composable
    fun bookMarkLong() = R.drawable.ic_bookmark_long.vector()
    @Composable
    fun check() = R.drawable.ic_check.vector()
    @Composable
    fun close() = R.drawable.ic_close.vector()
    @Composable
    fun faithful() = R.drawable.ic_faithful.vector()
    @Composable
    fun play() = R.drawable.ic_play.vector()
    @Composable
    fun resume() = R.drawable.ic_resume.vector()

    @Composable
    private fun Int.vector() = ImageVector.vectorResource(this)
}