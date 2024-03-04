package com.demo.jetpackcomposedemo.presenter.moviesdetails

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.demo.jetpackcomposedemo.R
import com.demo.jetpackcomposedemo.coreui.CustomToolbarScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieDetailsScreen(
    state: MovieDetailsScreenState,
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            CustomToolbarScreen(navController = navController, title = state.title?.let { "$it Details" }?:"", true)
        }
    ) { padding ->
        AnimatedContent(targetState = state, label = "Movie Detail") {state->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
            ) {
                MovieDetailsContent(state)
            }
        }
    }
}

@Composable
private fun MovieDetailsContent(state: MovieDetailsScreenState) {
    state.backdropUrl?.let { backDrop ->
        MovieDetailsBackDropBanner(
            backDropUrl = backDrop,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
    state.title?.let { title ->
        MovieDetailsTitle(
            title = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    8.dp
                )
        )
    }
    state.overview?.let { overview ->
        MovieDetailsDescription(
            title = overview,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    8.dp
                )
        )
    }
    state.releaseDate?.let { releaseDate ->
        MovieDetailsReleaseDate(
            releaseDate = releaseDate,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    8.dp
                )
        )
    }
}

@Composable
fun MovieDetailsTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier,
        fontSize = 20.sp,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun MovieDetailsDescription(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Normal
    )
}

@Composable
fun MovieDetailsReleaseDate(releaseDate: String, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.movie_details_release_date, releaseDate),
        modifier = modifier,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Thin
    )
}

@Composable
fun MovieDetailsBackDropBanner(
    backDropUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.FillWidth
) {
    AsyncImage(
        model = backDropUrl,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}
