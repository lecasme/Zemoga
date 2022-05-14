package com.leo.zemoga

import com.google.gson.GsonBuilder
import com.leo.zemoga.data.datasource.local.PostLocalDataSource
import com.leo.zemoga.data.datasource.local.PostLocalDataSourceImpl
import com.leo.zemoga.data.datasource.local.database.AppDataBase
import com.leo.zemoga.data.datasource.remote.PostRemoteDataSource
import com.leo.zemoga.data.datasource.remote.PostRemoteDataSourceImpl
import com.leo.zemoga.data.datasource.remote.UserRemoteDataSource
import com.leo.zemoga.data.datasource.remote.UserRemoteDataSourceImpl
import com.leo.zemoga.data.datasource.remote.service.PostService
import com.leo.zemoga.data.datasource.remote.service.UserService
import com.leo.zemoga.data.repository.PostsRepositoryImpl
import com.leo.zemoga.data.repository.UserRepositoryImpl
import com.leo.zemoga.domain.repository.PostRepository
import com.leo.zemoga.domain.repository.UserRepository
import com.leo.zemoga.domain.usecases.*
import com.leo.zemoga.presentation.features.details.DetailsViewModel
import com.leo.zemoga.presentation.features.home.HomeViewModel
import com.leo.zemoga.presentation.features.home.fragment.PageViewModel
import com.leo.zemoga.presentation.features.splash.SplashViewModel
import com.leo.zemoga.presentation.utils.API_POSTS
import com.leo.zemoga.presentation.utils.Connectivity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val appComponent = listOf(
    createService(API_POSTS),
    createViewModels(),
    createUseCases(),
    createDataBase(),
    createRepositories(),
    createDataSources(),
    createUtils()
)

fun createViewModels() = module{
    viewModel { SplashViewModel(get()) }
    viewModel { PageViewModel(get(),get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { DetailsViewModel(get(), get()) }
}

fun createRepositories() = module{
    factory { PostsRepositoryImpl(get(), get()) as PostRepository }
    factory { UserRepositoryImpl(get()) as UserRepository }
}

fun createUseCases() = module{
    factory { GetPostUseCase(get(), get()) }
    factory { UpdatePostUseCase(get()) }
    factory { RemovePostUseCase(get()) }
    factory { RemoveAllPostUseCase(get()) }
    factory { GetUserUseCase(get(), get()) }
}

fun createDataSources() = module{
    factory { PostRemoteDataSourceImpl(get()) as PostRemoteDataSource }
    factory { UserRemoteDataSourceImpl(get()) as UserRemoteDataSource }
    factory { PostLocalDataSourceImpl(get()) as PostLocalDataSource }
}

fun createDataBase() = module{
    val database = "DATABASE"
    single(named(database)) { AppDataBase.buildDatabase(androidContext()) }
    factory { (get(named(database)) as AppDataBase).postDao() }
}

fun createUtils() = module{
    factory { Connectivity(get()) }
}

fun createService(baseUrl: String) = module {

    single {
        val logginInterceptor = HttpLoggingInterceptor()
        logginInterceptor.apply { logginInterceptor.level = HttpLoggingInterceptor.Level.BODY }

        OkHttpClient.Builder()
            .addInterceptor(logginInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    factory { get<Retrofit>().create(PostService::class.java) }
    factory { get<Retrofit>().create(UserService::class.java) }

}