package com.demo.domain


import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import com.demo.domain.usecase.GetLatestMovieListUseCase
import com.demo.domain.usecase.ILatestMoviesListUseCase
import com.google.common.truth.Truth

class GetLatestMovieListUseCaseTest {
    @MockK
    val mockKMovieRepository = mockk<MovieRepository>()

    @Test
    fun `when we fetch latest movies list then return success`() = runTest {
        coEvery { mockKMovieRepository.fetchLatestMovies() } returns Result.Success(
            fakeSuccessMappedResponse
        )
        val useCase= createGetLatestMovieListUseCase(mockKMovieRepository)

        val result=useCase.invoke()

        // then
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        Truth.assertThat((result as Result.Success).data.movies).isNotEmpty()

    }

    @Test
    fun `when we fetch latest movies list then return fail`() = runTest {
        coEvery { mockKMovieRepository.fetchLatestMovies() } returns Result.Error(
            ErrorType.GENERIC
        )
        val useCase= createGetLatestMovieListUseCase(mockKMovieRepository)

        val result=useCase.invoke()

        // then
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat((result as Result.Error).errorType).isEqualTo(ErrorType.GENERIC)

    }


    private fun createGetLatestMovieListUseCase(
        repository: MovieRepository
    ): ILatestMoviesListUseCase {
        return GetLatestMovieListUseCase(movieRepository = repository)
    }
}