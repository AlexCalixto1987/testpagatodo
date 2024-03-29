package com.example.myapppagatodo.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        CoreModule::class
    ]
)

@InstallIn(SingletonComponent::class)
class AppModule