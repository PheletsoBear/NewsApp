package com.loc.newsapp.presentation.onboarding

import com.loc.newsapp.R
import androidx.annotation.DrawableRes

class Page (
    val tile: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        tile = "Lorem Ipsum is Simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding1
    ),
    Page(
        tile = "Lorem Ipsum is Simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding2),
    Page(
        tile = "Lorem Ipsum is Simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding3)
)