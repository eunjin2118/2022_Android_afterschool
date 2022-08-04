package com.example.mimo.database

import androidx.room.*

@Dao
interface MiMoDAO {
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

class MiMoDatabase {
}