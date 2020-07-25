package study.kotlin.memo_room.DB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query


@Dao
interface MemoDao {
    @Query("SELECT * FROM memo")
    fun getAll(): List<Memo>

    @Insert(onConflict = REPLACE)
    fun insert(memo : Memo)

    @Query("DELETE FROM memo ")
    fun deleteAll()

    @Query("DELETE FROM memo WHERE time = :time")
    fun delete(time : String)
}