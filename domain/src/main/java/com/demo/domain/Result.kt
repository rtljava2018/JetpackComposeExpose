package com.demo.domain

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()

    data class Error<T>(val errorType: ErrorType) : Result<T>()
}

enum class ErrorType {
    GENERIC,
    MOVIE_NOT_FOUND;

}
