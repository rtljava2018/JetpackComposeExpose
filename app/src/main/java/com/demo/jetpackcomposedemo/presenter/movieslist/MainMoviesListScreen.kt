package com.demo.jetpackcomposedemo.presenter.movieslist

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.demo.domain.model.Movie
import com.demo.jetpackcomposedemo.R
import com.demo.jetpackcomposedemo.coreui.CustomToolbarScreen

@Composable
fun MoviesScreen(
    moviesState: MoviesScreenState,
    navController: NavHostController,
    onMovieSelected: (Movie) -> Unit,
    onErrorActionClicked: () -> Unit,
){

    Scaffold(
        topBar = {
            CustomToolbarScreen(navController = navController, title = stringResource(id =R.string.movies), false)
        }
    ){ innerPadding ->

            Column(modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                if (moviesState.isLoading){
                    //loadding screen
                }else if (moviesState.errorMsg !=null){
                    Spacer(modifier = Modifier.weight(0.5f))
                    ErrorScreen(errorMsg = moviesState.errorMsg, errorActionTitle = R.string.error_retry) {
                        onErrorActionClicked()
                    }
                    Spacer(modifier = Modifier.weight(0.5f))
                }else if (moviesState.movies.isEmpty()){
                    EmptyListScreen()
                }else{
                   MainMoviesListScreen(moviesState = moviesState,onMovieSelected = onMovieSelected)
                }

            }
    }

}

@Composable
fun MainMoviesListScreen(
    moviesState: MoviesScreenState,
    onMovieSelected: (Movie) -> Unit){
    LazyColumn {
        items(moviesState.movies) { movie ->
            MovieItem(movie = movie){
                onMovieSelected(it)
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
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_card_round_shap_8dp)),
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(id = R.dimen.padding_card_16dp),
                end = dimensionResource(id = R.dimen.padding_card_16dp),
                top = dimensionResource(id = R.dimen.padding_card_16dp)
            ),
        colors = CardDefaults.cardColors(containerColor =  White),
        elevation = CardDefaults.cardElevation(defaultElevation =  dimensionResource(id = R.dimen.padding_card_elevation_8dp)),
        onClick = { onMovieSelected(movie) }
    ) {
        val typography = MaterialTheme.typography
        Row(
            modifier = Modifier
        ) {
            ItemImage(
                movie,
                Modifier.padding(end = dimensionResource(id = R.dimen.padding_5dp))
            )
            Column(modifier = Modifier
                .weight(1f)
                .padding(dimensionResource(id = R.dimen.padding_5dp))) {
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
@Composable
fun ErrorScreen(
    @StringRes errorMsg: Int,
    @StringRes errorActionTitle: Int,
    onErrorActionClicked: () -> Unit
){
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier =Modifier.weight(0.5f))
        Text(
            text = stringResource(id = errorMsg),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Button(
            onClick = { onErrorActionClicked() },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = errorActionTitle))
        }
        Spacer(modifier =Modifier.weight(0.5f))
    }
}

@Composable
private fun ColumnScope.EmptyListScreen() {
    Spacer(modifier = Modifier.weight(0.5f))
    Text(
        text = "----- empty list---",
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(16.dp),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Medium,
        style = MaterialTheme.typography.titleLarge
    )
    Spacer(modifier = Modifier.weight(0.5f))
}



