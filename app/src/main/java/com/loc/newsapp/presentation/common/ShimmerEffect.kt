package com.loc.newsapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.loc.newsapp.R
import androidx.compose.runtime.getValue
import com.loc.newsapp.presentation.Dimens.ArticleCardSize
import com.loc.newsapp.presentation.Dimens.CardShimmerEffectHeight
import com.loc.newsapp.presentation.Dimens.CardShimmerEffectHeight2
import com.loc.newsapp.presentation.Dimens.ExtraSmallPadding
import com.loc.newsapp.presentation.Dimens.mediumPadding1
import com.loc.newsapp.ui.theme.NewsAppTheme

fun Modifier.shimmerEffect() = composed{
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val scale by  infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    this.background(colorResource(id = R.color.shimmer).copy(alpha = scale))
}

@Composable
fun ArticleCardShimmerEffect(
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
    ){

        Box(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect(),
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding)
                .height(ArticleCardSize)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(CardShimmerEffectHeight)
                    .padding(horizontal = mediumPadding1)
                    .shimmerEffect(),
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(CardShimmerEffectHeight2)
                        .padding(horizontal = mediumPadding1)
                        .shimmerEffect(),
                )
            }
        }
    }
}

@Preview (showBackground = true)
@Preview (showBackground = false, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardShimmerEffectPreview(){

    NewsAppTheme{
        ArticleCardShimmerEffect(
            modifier = Modifier)
        }
    }
