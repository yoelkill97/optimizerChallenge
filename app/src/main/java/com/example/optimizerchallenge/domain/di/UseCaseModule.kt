package com.example.optimizerchallenge.domain.di

import com.example.optimizerchallenge.domain.usecase.creation.CreateApplicationUseCase
import com.example.optimizerchallenge.domain.usecase.creation.CreateApplicationUseCaseImpl
import com.example.optimizerchallenge.domain.usecase.lista.GetAllApplicationUseCase
import com.example.optimizerchallenge.domain.usecase.lista.GetAllApplicationUseCaseImpl
import com.example.optimizerchallenge.domain.usecase.recommendations.GetRecommendationsUseCase
import com.example.optimizerchallenge.domain.usecase.recommendations.GetRecommendationsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @ViewModelScoped
    @Binds
    abstract fun bindUseCaseGetRecommendations(useCaseImpl: GetRecommendationsUseCaseImpl): GetRecommendationsUseCase

    @ViewModelScoped
    @Binds
    abstract fun binCreateApplicationUseCase(useCaseImpl: CreateApplicationUseCaseImpl): CreateApplicationUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindGetAllApplicationUseCase(useCaseImpl: GetAllApplicationUseCaseImpl): GetAllApplicationUseCase

}