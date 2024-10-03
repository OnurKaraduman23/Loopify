package com.onurkaraduman.loopify.di

import com.google.firebase.auth.FirebaseAuth
import com.onurkaraduman.loopify.data.repository.auth_repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseRepository(auth: FirebaseAuth): AuthRepository = AuthRepository(auth)


}