package com.demo.jetpackcomposedemo.data

import com.demo.jetpackcomposedemo.data.model.MovieResponse
import com.demo.jetpackcomposedemo.data.model.MoviesResponse
import com.demo.jetpackcomposedemo.domain.model.GenericException
import com.demo.jetpackcomposedemo.domain.model.Movie
import com.demo.jetpackcomposedemo.domain.model.Movies
import java.net.HttpURLConnection
import javax.inject.Inject

class NetworkServiceSourceImpl @Inject constructor(
    private val apiServiceInterface: ApiServiceInterface
):NetworkServiceSource{
    override suspend fun getListMovies(): Movies {

        val response = apiServiceInterface.getLatestMovies(
            language =  "en-US",
            page = 1,
            sortBy = "popularity.desc",
            includeAdult = false,
            includeVideo = false
        )

        val result = response.body()

        return if (response.isSuccessful && result!=null ){
            result.toDomainMoviesResponseModel()
        } else{
            val exception = mapResponseCodeToExceptionError(response.code())
            throw exception
        }
    }
}

fun MoviesResponse.toDomainMoviesResponseModel(): Movies =
    Movies(
        currentPage = page,
        totalPages = totalPages,
        movies = results.map { it.toDomainMoviesResponseModel() }
    )

fun MovieResponse.toDomainMoviesResponseModel(): Movie = Movie(
    id = id,
    title = title,
    overview = overview,
    posterUrl = "${Config.BASE_URL_FOR_IMAGE}${Config.IMAGE_SIZE_W185}$posterPath",
    backdropUrl = "${Config.BASE_URL_FOR_IMAGE}${Config.IMAGE_SIZE_W780}$backdropPath",
    releaseDate = releaseDate
)
fun mapResponseCodeToExceptionError(code: Int): Throwable = when (code) {
    HttpURLConnection.HTTP_UNAUTHORIZED -> GenericException("Unauthorized access : $code")
    else -> GenericException("error : $code")
}
