package com.demo.domain


import com.demo.domain.model.Movie
import com.demo.domain.model.Movies




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
