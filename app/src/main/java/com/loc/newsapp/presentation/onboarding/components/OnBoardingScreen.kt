package com.loc.newsapp.presentation.onboarding.components

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.loc.newsapp.presentation.Dimens.PageIndicatorWidth
import com.loc.newsapp.presentation.Dimens.mediumPadding2
import com.loc.newsapp.presentation.common.NewsButton
import com.loc.newsapp.presentation.common.NewsTextButton
import com.loc.newsapp.presentation.onboarding.pages
import kotlinx.coroutines.launch
import java.nio.file.WatchEvent

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        val buttonState = remember {
            derivedStateOf {

                val isFirstPage = pagerState.currentPage == 0
                val isLastPage = pagerState.currentPage == pages.size - 1

                when {
                    isFirstPage -> listOf("", "Next")
                    isLastPage -> listOf("back", "get Started")
                    else -> listOf("Back", "Next")
                }
            }
        }

        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(
                page = pages[index],
               modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.weight(1f)) //takes space not occupied by other composables

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = mediumPadding2),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            PageIndicator(
                modifier = Modifier.width(PageIndicatorWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                val scope = rememberCoroutineScope()

                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(
                        text = buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        }
                    )
                }
                NewsButton(
                    text = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == pages.size - 1) {
                                //TODO: Navigate to Home Screen
                            } else {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1
                                )
                            }
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }

}
