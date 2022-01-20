package com.mertselcukdemir.core.di.modules

import com.mertselcukdemir.core.BuildConfig
import com.mertselcukdemir.core.data.repositories.GithubRepoRepository
import com.mertselcukdemir.core.network.services.GithubRepoService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by mertselcukdemir on 20.01.2022
 * All rights reserved.
 */

/**
 * Class that supplies the necessary dependent methods for the Network
 *
 * @see Module
 */
@Module
object NetworkModule {

    /**
     * Create a provider method binding for [HttpLoggingInterceptor].
     *
     * @return Instance of http interceptor.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    /**
     * Create a provider method binding for [OkHttpClient].
     *
     * @return Instance of http client.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        //if (BuildConfig.DEBUG) {
        clientBuilder.addInterceptor(interceptor)
        //}
        return clientBuilder.build()
    }

    /**
     * Create a provider method binding for [Retrofit].
     *
     * @return Instance of retrofit.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL) //Defined as BuildConfig variable because there can be different urls depending on the build type.
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
    }

    /**
     * Create a provider method binding for [Moshi].
     *
     * @return Instance of Moshi.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        val builder = Moshi.Builder()
        return builder.add(KotlinJsonAdapterFactory()).build()
    }

    /**
     * Create a provider method binding for [GithubRepoService].
     *
     * @return Instance of Github search service.
     * @see Provides
     */
    @Provides
    fun provideRepoService(retrofit: Retrofit): GithubRepoService = retrofit.create(GithubRepoService::class.java)

    /**
     * Create a provider method binding for [GithubRepoRepository].
     *
     * @return Instance of GitHubRepo repository.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideRepoRepository(service: GithubRepoService) = GithubRepoRepository(service)

}