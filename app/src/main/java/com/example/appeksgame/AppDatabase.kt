package com.example.appeksgame

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import kotlin.coroutines.CoroutineContext


@Database(entities= arrayOf(Question::class),version = 1)
@TypeConverters(Converters::class)


abstract class AppDatabase : RoomDatabase() {
    abstract val questionDao: QustionDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? =null

        @InternalCoroutinesApi
        fun getInstance(context: Context): AppDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance==null)
                {
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "question_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE=instance
                }
                return instance
            }
        }
    }
}