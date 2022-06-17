package com.inc.sada_pay_test.di

import com.inc.sada_pay_test.network.SearchRepositioryServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideAuthApi(retrofit: Retrofit): SearchRepositioryServiceApi {
        return retrofit.create(SearchRepositioryServiceApi::class.java)
    }

}