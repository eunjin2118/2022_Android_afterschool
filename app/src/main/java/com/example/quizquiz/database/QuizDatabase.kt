package com.example.quizquiz.database

import android.content.Context
import androidx.room.*

// Data Access Object
@Dao
interface QuizDAO {
    // @이런거 붙여줘야 구현을 자동으로 해줌(상속 안 받아도 됨)
    @Insert
    fun insert(quiz: Quiz): Long
    @Update
    fun update(quiz: Quiz)
    @Delete
    fun delete(quiz: Quiz)
    @Query("SELECT * FROM quiz")
    fun getAll(): List<Quiz>
}

// 3
@Database(entities = [Quiz::class], version = 1) // 우리가 사용할 entity클래스 정보
@TypeConverters(StringListTypeConverter::class)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun quizDAO(): QuizDAO

    // 정적 메서드(클래스 메서드)
    companion object {
        private var INSTANCE: QuizDatabase? = null

        // 싱글턴 패턴
        // 객체를 하나만 만들겠다는 것!
        fun getInstance(context: Context): QuizDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java, "database.db"
                ).build()
            }
            return INSTANCE!!
        }
    }
}