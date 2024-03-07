package com.demo.domain


import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import com.demo.domain.usecase.GetLatestMovieDetailsUseCase
import com.demo.domain.usecase.ILatestMovieDetailsUseCase
import com.google.common.truth.Truth.assertThat

@OptIn(ExperimentalCoroutinesApi::class)
class GetLatestMovieDetailsUseCaseTest {

    @MockK
    val mockKMovieRepository = mockk<MovieRepository>()

    @Test
    fun `when we get a specific movies details ,then return success`() = runTest {
        coEvery { mockKMovieRepository.fetchLatestMovies() } returns Result.Success(
            fakeSuccessMappedResponse
        )
        val getLatestMovieDetailsUseCase = createGetLatestMovieDetailsUseCase(mockKMovieRepository)

        val result = getLatestMovieDetailsUseCase.invoke(movieId = 1)

        val expectedResult = movie1
        assertThat(result as Result.Success).isInstanceOf(Result.Success::class.java)
        assertThat(result.data).isEqualTo(expectedResult)
    }

    @Test
    fun `when we get a specific movies details and it's not found ,then return an error`() =
        runTest {
            coEvery { mockKMovieRepository.fetchLatestMovies() } returns Result.Success(
                fakeSuccessMappedResponse
            )

            val getLatestMovieDetailsUseCase =
                createGetLatestMovieDetailsUseCase(mockKMovieRepository)

            val result = getLatestMovieDetailsUseCase.invoke(movieId = 4)
            assertThat(result as Result.Error).isInstanceOf(Result.Error::class.java)
            assertThat(result.errorType)
                .isEqualTo(ErrorType.MOVIE_NOT_FOUND)
        }

    private fun createGetLatestMovieDetailsUseCase(
        repository: MovieRepository
    ): ILatestMovieDetailsUseCase {
        return GetLatestMovieDetailsUseCase(movieRepository = repository)
    }

}
