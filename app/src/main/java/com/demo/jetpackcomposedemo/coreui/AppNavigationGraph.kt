package com.demo.jetpackcomposedemo.coreui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.demo.jetpackcomposedemo.presenter.moviesdetails.MovieDetailsScreen
import com.demo.jetpackcomposedemo.presenter.moviesdetails.MovieDetailsScreenIntent
import com.demo.jetpackcomposedemo.presenter.moviesdetails.MovieDetailsViewModel
import com.demo.jetpackcomposedemo.presenter.movieslist.MainMoviesViewModel
import com.demo.jetpackcomposedemo.presenter.movieslist.MoviesScreen
import com.demo.jetpackcomposedemo.presenter.movieslist.MoviesScreenIntent

@Composable
fun AppNavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = Destinations.MOVIES.route, modifier) {
        composable(Destinations.MOVIES.route) {
            val viewModel = hiltViewModel<MainMoviesViewModel>()
            viewModel.state.collectAsState().value.let { state ->
                MoviesScreen(
                    moviesState = state,
                    navController,
                    onMovieSelected = {movie->
                        navController.navigate("${Destinations.MOVIE_DETAILS.route}/${movie.id}")
                    },
                    onErrorActionClicked = {
                        viewModel.initIntentProcess(MoviesScreenIntent.LoadLatestMovies)
                    }
                )
            }
        }
        composable(Destinations.MOVIE_DETAILS.route + "/{movieId}") { navBackStackEntry ->
            val movieId = navBackStackEntry.arguments?.getString("movieId")?.toInt()
            requireNotNull(movieId)

            val viewModel = hiltViewModel<MovieDetailsViewModel>()

            viewModel.processIntent(MovieDetailsScreenIntent.LoadMovieDetail(movieId))

            viewModel.state.collectAsState().value.let { state ->
                MovieDetailsScreen(
                    state = state,
                    navController
                )
            }
        }
    }
}

enum class Destinations(val route: String) {
    MOVIES("movies"),
    MOVIE_DETAILS("movie_details")
}
