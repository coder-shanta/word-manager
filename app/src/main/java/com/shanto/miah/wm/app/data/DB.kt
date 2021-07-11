package com.shanto.miah.wm.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shanto.miah.wm.app.data.dao.WordDao
import com.shanto.miah.wm.app.data.entitys.Word

@Database(entities = [Word::class], version = 1)
abstract class DB: RoomDatabase() {

    abstract fun getWordDao() : WordDao

    companion object {
        @Volatile
        var INSTANCE: DB? = null

        fun getDatabase(context: Context): DB{
            if(INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, DB::class.java, "wordmanager").build()
                }
            }

            return INSTANCE!!
        }
    }
}