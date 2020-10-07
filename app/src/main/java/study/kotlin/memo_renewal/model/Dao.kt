package study.kotlin.memo_renewal.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Query("SELECT * FROM Memo")
    fun getAll(): List<Memo>

    @Insert
    fun insert(memo : Memo)

    @Query("UPDATE Memo SET time = :time, title = :title, content = :content WHERE id = :id")
    fun update(title : String, time : String, content : String, id : Long)

    @Query("DELETE FROM Memo WHERE id = :id")
    fun delete(id : Long)
}