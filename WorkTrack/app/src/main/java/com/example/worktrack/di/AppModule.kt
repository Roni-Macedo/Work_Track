package com.example.worktrack.di

import android.content.Context
import androidx.room.Room
import com.example.worktrack.data.local.AppDatabase
import com.example.worktrack.data.local.WorkDao
import com.example.worktrack.data.repository.WorkRepositoryImpl
import com.example.worktrack.domain.repository.WorkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "work_db"
        ).build()
    }

    @Provides
    fun provideWorkDao(db: AppDatabase): WorkDao = db.workDao()

    @Provides
    @Singleton
    fun provideWorkRepository(dao: WorkDao): WorkRepository {
        return WorkRepositoryImpl(dao)
    }
}
