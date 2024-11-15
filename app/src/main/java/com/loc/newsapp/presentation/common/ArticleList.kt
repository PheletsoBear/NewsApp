package com.loc.newsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.Dimens.ExtraSmallPadding2
import com.loc.newsapp.presentation.Dimens.mediumPadding1


@Composable
fun ArticleList(
    modifier: Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
){
    val handlePagingResult = handlePagingResult( articles = articles)
    if(handlePagingResult){
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(mediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ){
            items(count = articles.itemCount){ index ->
                articles[index]?.let { article ->
                    ArticleCard(
                        modifier = Modifier,
                        article = article,
                        onClick = { onClick(article) }
                    )
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>
): Boolean{

/*
Condition Check: loadState.refresh is LoadState.Error
This part checks if loadState.refresh is of type LoadState.Error, meaning an error occurred during the initial load.
If the condition is true, this part casts loadState.refresh to LoadState.Error and assigns it to the variable error.
*/

    val loadState = articles.loadState
    val error = when{
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when{

        /*
       This code essentially checks:
       Is the data loading? If so, show the loading animation and return false.
       Did an error occur? If so, return false.
       Is the data fully loaded with no issues? If so, return true.

       */
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }
        error != null -> {
            EmptyScreen()
            false
        }
        else -> true
    }

}

@Composable
private fun ShimmerEffect(){
    Column(
        verticalArrangement = Arrangement.spacedBy(mediumPadding1)
    ){
        repeat(10){
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = mediumPadding1)
            )
        }
    }
}