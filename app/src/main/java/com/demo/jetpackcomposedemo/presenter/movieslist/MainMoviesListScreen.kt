package com.demo.jetpackcomposedemo.presenter.movieslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.demo.domain.model.Movie
import com.demo.jetpackcomposedemo.coreui.CustomToolbarScreen


@Composable
fun MoviesScreen(
    moviesState: MoviesScreenState,
    navController: NavHostController,
    onMovieSelected: (Movie) -> Unit
){
    val state = rememberScrollState()
    LaunchedEffect(Unit) { state.animateScrollTo(100) }
    Scaffold(
        topBar = {
            CustomToolbarScreen(navController = navController, title = "Movies", false)
        }
    ){ innerPadding ->

            Column(modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                LazyColumn() {
                    items(moviesState.movies) { movie ->
                        MovieItem(movie = movie){
                            onMovieSelected(it)
                        }
                    }
                }
            }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier,
    onMovieSelected: (Movie) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        colors = CardDefaults.cardColors(containerColor =  White),
        elevation = CardDefaults.cardElevation(defaultElevation =  8.dp),
        onClick = { onMovieSelected(movie) }
    ) {
        val typography = MaterialTheme.typography
        Row(
            modifier = Modifier
        ) {
            ItemImage(
                movie,
                Modifier.padding(end = 5.dp)
            )
            Column(modifier = Modifier.weight(1f).padding(8.dp)) {
                Text(
                    movie.title,
                    style = typography.titleMedium, maxLines = 2,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,)
                Text(movie.overview,
                    style = typography.bodyMedium,
                    maxLines =3,
                    overflow = TextOverflow.Ellipsis)
            }
        }
    }
}
@Composable
fun ItemImage(item: Movie, modifier: Modifier = Modifier) {
    Image(
        painter = rememberAsyncImagePainter(item.posterUrl),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = modifier
            .size(120.dp)
    )
}

