package com.example.database1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Parcel
import android.os.Parcelable
import com.example.database1.Data.values.COL_1
import com.example.database1.Data.values.COL_2
import com.example.database1.Data.values.COL_3
import com.example.database1.Data.values.COL_4
import com.example.database1.Data.values.COL_5
import com.example.database1.Data.values.COL_6
import com.example.database1.Data.values.COL_7
import com.example.database1.Data.values.COL_8
import com.example.database1.Data.values.DATABASE_NAME
import com.example.database1.Data.values.TABLE_NAME

import org.w3c.dom.Text

class Data(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    var cc=0
    object  values{
        var DATABASE_NAME = "myData"
        var TABLE_NAME="sensors"
        var COL_1="accx"
        var COL_2 ="accy"
        var COL_3="accz"

        var COL_4="time"
        var COL_5="gyrox"
        var COL_6="gyroy"
        var COL_7="gyroz"
        var COL_8="test"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table  ${TABLE_NAME} ( id INTEGER  primary key autoincrement , ${COL_1}  number(4,5) ,${COL_2}  number(4,5),${COL_3}  number(4,5),${COL_4}  text,${COL_5}  number(4,5),${COL_6}  number(4,5),${COL_7}  number(4,5) ,${COL_8} number(4)  )")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists ${TABLE_NAME}")
        onCreate(db)
    }
    fun addData(acx:Float?,acy :Float?,acz :Float?,gx:Float?,gy :Float?,gz :Float?,tym: String):Long{
        val db=this.writableDatabase

        val cv=ContentValues()
        cv.put("accx",acx)
        cv.put("accy",acy)
        cv.put("accz",acz)
        cv.put("gyrox",gx)
        cv.put("gyroy",gy)
        cv.put("gyroz",gz)
        cv.put("time",tym)
        cv.put("test",cc)
        val res=db.insert(TABLE_NAME,null,cv)
        db.close()
        return res

    }
    fun setcc(ccc:Int){
        cc=ccc
    }
    fun getData():Cursor{
        val db=this.writableDatabase
        val cur=db.rawQuery("select * from ${TABLE_NAME}",null)
        return cur
    }
}