package com.demo.jetpackcomposedemo.di


import com.demo.domain.usecase.GetLatestMovieDetailsUseCase
import com.demo.domain.usecase.GetLatestMovieListUseCase
import com.demo.domain.usecase.ILatestMovieDetailsUseCase
import com.demo.domain.usecase.ILatestMoviesListUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
@Module
@InstallIn(ViewModelComponent::class)
interface UseCasesModule {

    @Binds
    fun bindGetLatestMovieDetailsUseCase(
        getLatestMovieDetails: GetLatestMovieDetailsUseCase
    ): ILatestMovieDetailsUseCase

    @Binds
    fun bindGetLatestMoviesListUseCase(
        getLatestMoviesList: GetLatestMovieListUseCase
    ): ILatestMoviesListUseCase
}
