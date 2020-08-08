package study.kotlin.memo_renewal.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Query("SELECT * FROM Memo")
    fun getAll(): List<Memo>

    @Insert
    fun insert(memo : Memo)

    @Query("UPDATE Memo SET time = :time, title = :title, content = :content WHERE time = :originalTime")
    fun update(title : String, time : String, content : String, originalTime : String)

    @Query("DELETE FROM Memo WHERE title = :title AND time = :time")
    fun delete(title : String, time : String)
}