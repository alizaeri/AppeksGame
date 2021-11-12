package com.example.appeksgame

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QustionDao {
    @Insert
    fun insert(question: Question)

    @Delete
    fun delete(question: Question)

    @Query("Select * from question_table")
    fun getAll() :  List<Question>
}