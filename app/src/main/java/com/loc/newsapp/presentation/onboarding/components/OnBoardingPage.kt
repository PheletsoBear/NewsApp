package com.loc.newsapp.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.loc.newsapp.R
import com.loc.newsapp.presentation.Dimens.mediumPadding1
import com.loc.newsapp.presentation.Dimens.mediumPadding2
import com.loc.newsapp.presentation.onboarding.Page

@Composable
fun OnBoardingPage(
    modifier: Modifier,
    page: Page,
) {
    Column {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f),
            painter = painterResource(id = page.image),
            contentDescription = "OnBoarding Image",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(mediumPadding1))
        Text(
            text = page.tile,
            modifier = Modifier.padding(horizontal = mediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.display_small)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = page.description,
            modifier = Modifier
                .padding(
                    horizontal = mediumPadding2
                ),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium)
        )
    }
}
