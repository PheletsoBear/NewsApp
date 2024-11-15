package com.loc.newsapp.presentation.home


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.Dimens.mediumPadding1
import androidx.compose.runtime.getValue //manual import
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loc.newsapp.R
import com.loc.newsapp.presentation.common.ArticleList
import com.loc.newsapp.presentation.common.SearhBar
import com.loc.newsapp.presentation.navigation.Route

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(articles: LazyPagingItems<Article>, navigate: (String) -> Unit){

    val titles by remember {

        derivedStateOf {
            if(articles.itemCount > 10){
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " 🚨"){it.title}
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = mediumPadding1)
            .statusBarsPadding()
    ){
        Image(
            painter = painterResource(
                id = R.drawable.ic_logo
            ),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(
                    horizontal = mediumPadding1
                )
        )

        Spacer(modifier = Modifier.height(mediumPadding1))

        SearhBar(
            modifier = Modifier.padding(horizontal = mediumPadding1),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick =  {
                navigate(Route.SearchScreen.route)
            },
            onSearch = {}
        )

        Spacer(modifier = Modifier.height(mediumPadding1))

        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = mediumPadding1)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(
                id = R.color.placeholder
            )
        )

        Spacer(modifier = Modifier.height(mediumPadding1))

        ArticleList(
            modifier = Modifier.padding(horizontal = mediumPadding1),
            articles = articles,
            onClick = {
                navigate(Route.DetailsScreen.route)
            }
        )
    }

}