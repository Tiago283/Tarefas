package com.tiago.tarefas.data

import android.content.Context
import androidx.room.Room
import com.tiago.tarefas.data.local.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "tarefas-database.db"
        ).build()
    }

    @Provides
    fun provideTaskDao(db: AppDatabase) : TaskDao = db.taskDao()
}