package com.onurkaraduman.loopify.di

import android.app.Application
import androidx.room.Room
import com.onurkaraduman.loopify.common.Constants.BASE_URL
import com.onurkaraduman.loopify.common.Constants.PRODUCT_DATABASE_NAME
import com.onurkaraduman.loopify.data.local.dao.ProductDao
import com.onurkaraduman.loopify.data.local.database.ProductsDatabase
import com.onurkaraduman.loopify.data.repository.MyRepositoryImpl
import com.onurkaraduman.loopify.data.service.MyApi
import com.onurkaraduman.loopify.domain.repository.MyRepository
import com.onurkaraduman.loopify.domain.use_case.local.DeleteProduct
import com.onurkaraduman.loopify.domain.use_case.local.IsFavoriteProduct
import com.onurkaraduman.loopify.domain.use_case.local.LocalProductUseCase
import com.onurkaraduman.loopify.domain.use_case.local.SelectProduct
import com.onurkaraduman.loopify.domain.use_case.local.SelectProducts
import com.onurkaraduman.loopify.domain.use_case.local.UpsertProduct
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): MyApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMyRepository(
        api: MyApi,
        productDao: ProductDao
    ): MyRepository = MyRepositoryImpl(api, productDao)

    // ROOM Database

    @Provides
    @Singleton
    fun provideProductDatabase(
        application: Application
    ): ProductsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = ProductsDatabase::class.java,
            name = PRODUCT_DATABASE_NAME
        )
            .fallbackToDestructiveMigration() // Tablo yapısında bir değişiklik olduğunda eski tb yi siler ve yenisini oluşturur
            .build()
    }

    @Provides
    @Singleton
    fun provideProductDao(
        productsDatabase: ProductsDatabase
    ): ProductDao = productsDatabase.productDao

    @Provides
    @Singleton
    fun provideLocalUseCases(
        myRepository: MyRepository
    ): LocalProductUseCase {
        return LocalProductUseCase(
            upsertProduct = UpsertProduct(myRepository),
            selectProduct = SelectProduct(myRepository),
            deleteProduct = DeleteProduct(myRepository),
            selectProducts = SelectProducts(myRepository),
            isFavoriteProduct = IsFavoriteProduct(myRepository)
        )
    }

}