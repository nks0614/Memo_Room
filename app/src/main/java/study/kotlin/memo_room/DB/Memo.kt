package study.kotlin.memo_room.DB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Memo(@PrimaryKey var id : Long?,
           @ColumnInfo(name = "title") var title : String,
           @ColumnInfo(name = "content") var content : String
) {
    constructor(): this(null, "", "")
}