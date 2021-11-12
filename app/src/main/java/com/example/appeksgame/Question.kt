
package com.example.appeksgame

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "question_table")
data class Question (
    @PrimaryKey(autoGenerate = true ) var id : Int,
    @ColumnInfo(name = "questionText") var questionText : String,
    @ColumnInfo(name = "alternativ1")var alternative1 : Int,
    @ColumnInfo(name = "alternative2")var alternative2 : Int,
    @ColumnInfo(name = "alternative3")var alternative3 : Int,
    @ColumnInfo(name = "alternative4")var alternative4 : Int,
    @ColumnInfo(name = "alternative5")var alternative5 : Int,
    @ColumnInfo(name = "alternative6")var alternative6 : Int,
    @ColumnInfo(name = "alternative7")var alternative7 : Int,
    @ColumnInfo(name = "alternative8")var alternative8 : Int,
    @ColumnInfo(name = "alternative9")var alternative9 : Int,
    @ColumnInfo(name = "backgrund")var backgrund : Int,
    @ColumnInfo(name = "currectAnswer") var currectAnswer: List<Int>


){

}
class Converters {
    @TypeConverter
    fun intListToString(value: List<Int>): String = value.toString()

    @TypeConverter
    fun stringToIntList(string: String?): List<Int> {
        return string!!.map {it.toInt()}

    }
}