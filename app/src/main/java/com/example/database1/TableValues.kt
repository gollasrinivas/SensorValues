package com.example.database1

import android.app.ActionBar
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import kotlinx.android.synthetic.main.table_values.*

class TableValues:AppCompatActivity() {
val data:Data=Data(this)
    var id1:TextView?=null

var count=0
    var gv=true
    var time1:TextView?=null
    var test1:TextView?=null
    var acc_x1:TextView?=null
lateinit var c:Cursor
    var acc_y1:TextView?=null
    var acc_z1:TextView?=null
    var gyro_x1:TextView?=null
    var gyro_y1:TextView?=null
    var gyro_z1:TextView?=null

    lateinit var mtable:TableLayout
    lateinit var mrow:TableRow
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table_values)
         c=data.getData()



         mtable=findViewById<TableLayout>(R.id.main_table)
        mtable.setColumnStretchable(0,true)
        mtable.setColumnStretchable(1,true)
        mtable.setColumnStretchable(2,true)
        mtable.setColumnStretchable(3,true)
        mtable.setColumnStretchable(4,true)
        mtable.setColumnStretchable(5,true)
        mtable.setColumnStretchable(6,true)
        mtable.setColumnStretchable(7,true)
        mtable.setColumnStretchable(8,true)
        val tr_head=TableRow(this)
        tr_head.setBackgroundColor(Color.GRAY)
        tr_head.layoutParams= ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
    }
    fun take_test(v:View){
        val intent=Intent(this,MainActivity::class.java)

        startActivity(intent)
        this.finish()
    }

    fun getvalues(v: View) {
        if (gv) {
            gv=false
            if (c != null) {

                c.moveToFirst()
                while (!c.isAfterLast) {
                    mrow = TableRow(this)
                    id1 = TextView(this)
                    acc_x1 = TextView(this)
                    acc_y1 = TextView(this)
                    acc_z1 = TextView(this)
                    time1 = TextView(this)
                    test1 = TextView(this)
                    gyro_x1 = TextView(this)
                    gyro_y1 = TextView(this)
                    gyro_z1 = TextView(this)
                    id1?.text = c.getString(0)
                    acc_x1?.text = c.getString(1)
                    acc_y1?.text = c.getString(2)
                    acc_z1?.text = c.getString(3)
                    test1?.text = c.getString(8)
                    time1?.text = c.getString(4)
                    gyro_x1?.text = c.getString(5)
                    gyro_y1?.text = c.getString(6)
                    gyro_z1?.text = c.getString(7)
                    mrow.addView(id1)
                    mrow.addView(time1)
                    mrow.addView(test1)
                    mrow.addView(acc_x1)
                    mrow.addView(acc_y1)
                    mrow.addView(acc_z1)

                    mrow.addView(gyro_x1)
                    mrow.addView(gyro_y1)
                    mrow.addView(gyro_z1)
                    mtable.addView(mrow)
                    c.moveToNext()
                }
            }


            c.close()

        }
    }
}