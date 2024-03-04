package com.demo.jetpackcomposedemo

import com.demo.jetpackcomposedemo.data.model.MovieResponse
import com.demo.jetpackcomposedemo.data.model.MoviesResponse
import com.demo.jetpackcomposedemo.domain.model.Movie
import com.demo.jetpackcomposedemo.domain.model.Movies


val fakeSuccessMovieResponse = MoviesResponse(
    page = 1,
    totalPages = 3,
    results = listOf(
        MovieResponse(
            id = 1,
            title = "The One",
            overview = "Fake Movie Overview",
            posterPath = "/fake_poster_path",
            backdropPath = "/fake_backdrop_path",
            releaseDate = "2021-01-01",
            originalTitle = "Fake Original Title"
        ),
        MovieResponse(
            id = 2,
            title = "That Will",
            overview = "Fake Movie Overview",
            posterPath = "/fake_poster_path",
            backdropPath = "/fake_backdrop_path",
            releaseDate = "2021-02-02",
            originalTitle = "Fake Original Title"
        ),
        MovieResponse(
            id = 3,
            title = "Blow Your Mind",
            overview = "Fake Movie Overview",
            posterPath = "/fake_poster_path",
            backdropPath = "/fake_backdrop_path",
            releaseDate = "2021-03-03",
            originalTitle = "Fake Original Title"
        )
    )
)

val fakeSuccessMappedResponse = Movies(
    currentPage = 1,
    totalPages = 3,
    movies = listOf(
        Movie(
            id = 1,
            title = "The One",
            overview = "Fake Movie Overview",
            posterUrl = "https://image.tmdb.org/t/p/w185/fake_poster_path",
            backdropUrl = "https://image.tmdb.org/t/p/w780/fake_backdrop_path",
            releaseDate = "2021-01-01"
        ),
        Movie(
            id = 2,
            title = "That Will",
            overview = "Fake Movie Overview",
            posterUrl = "https://image.tmdb.org/t/p/w185/fake_poster_path",
            backdropUrl = "https://image.tmdb.org/t/p/w780/fake_backdrop_path",
            releaseDate = "2021-02-02"
        ),
        Movie(
            id = 3,
            title = "Blow Your Mind",
            overview = "Fake Movie Overview",
            posterUrl = "https://image.tmdb.org/t/p/w185/fake_poster_path",
            backdropUrl = "https://image.tmdb.org/t/p/w780/fake_backdrop_path",
            releaseDate = "2021-03-03"
        )
    )
)

val movie1 = Movie(
    id = 1,
    title = "The One",
    overview = "Fake Movie Overview",
    posterUrl = "https://image.tmdb.org/t/p/w185/fake_poster_path",
    backdropUrl = "https://image.tmdb.org/t/p/w780/fake_backdrop_path",
    releaseDate = "2021-01-01"
)
