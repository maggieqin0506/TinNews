package com.laioffer.tinnews

import android.app.Application
import androidx.room.Room
import com.ashokvarma.gander.Gander
import com.ashokvarma.gander.imdb.GanderIMDB
import com.facebook.stetho.Stetho
import com.laioffer.tinnews.database.TinNewsDatabase

// app level configuration so as to have getter library connect to the app
class TinNewsApplication : Application() {
    // app's lifecycle 全局初始化的操作
    // app打开的一瞬间先运行这个
    var database: TinNewsDatabase? = null
        private set

    override fun onCreate() {
        super.onCreate()
        Gander.setGanderStorage(GanderIMDB.getInstance())
        // stetho: debugging tool, 把运行的APP连接到一个chrome web page
        Stetho.initializeWithDefaults(this)
        database = Room.databaseBuilder<TinNewsDatabase>(this, TinNewsDatabase::class.java, "tinnews_db").build()
    }

}