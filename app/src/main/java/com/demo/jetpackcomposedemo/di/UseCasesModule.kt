package com.demo.jetpackcomposedemo.di

import com.demo.jetpackcomposedemo.domain.usecase.GetLatestMovieDetailsUseCase
import com.demo.jetpackcomposedemo.domain.usecase.GetLatestMovieListUseCase
import com.demo.jetpackcomposedemo.domain.usecase.ILatestMovieDetailsUseCase
import com.demo.jetpackcomposedemo.domain.usecase.ILatestMoviesListUseCase
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
