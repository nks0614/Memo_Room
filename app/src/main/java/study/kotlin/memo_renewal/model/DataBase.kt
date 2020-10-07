package study.kotlin.memo_renewal.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Memo::class], version = 3)
abstract class DataBase : RoomDatabase() {

    abstract  fun dao() : Dao

    companion object{
        private var INSTANCE : DataBase? = null

        fun getInstance(context : Context) : DataBase?{
            if(INSTANCE == null){
                synchronized(Memo::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DataBase::class.java, "Memo.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }

}