package io.jamshid.pdpuz.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.jamshid.pdpuz.data.local.AppDatabase
import io.jamshid.pdpuz.data.local.dao.PdpDao
import io.jamshid.pdpuz.data.local.dao.PdpDao_Impl
import io.jamshid.pdpuz.domain.usecases.GetGroupsUseCase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "pdp_mobile"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(
        appDatabase: AppDatabase
    ): PdpDao {
        return PdpDao_Impl(appDatabase)
    }

    @Provides
    @Singleton
    fun provideGroupUseCase(
        pdpDao: PdpDao
    ):GetGroupsUseCase{
        return GetGroupsUseCase(pdpDao)
    }


}