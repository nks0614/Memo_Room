package study.kotlin.memo_renewal.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Memo")
class Memo (
    @PrimaryKey(autoGenerate = true) var id : Long,
    @ColumnInfo(name = "title") var title : String,
    @ColumnInfo(name = "time") var time : String,
    @ColumnInfo(name = "content") var content : String
)