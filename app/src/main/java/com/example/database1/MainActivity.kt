package com.example.database1

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.example.database1.R.string.*
import kotlinx.android.synthetic.main.activitymain.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(),SensorEventListener {
var afterCount=0
    lateinit var sensorManager: SensorManager
    lateinit var msensor:Sensor
    lateinit var gsensor:Sensor

    var stop1=false
    var start1=false
    var xx:Float=0.0f
    var yy:Float=0.0f
    var zz=0.0f
    var gxx:Float=0.0f
    var gyy:Float=0.0f
    var gzz=0.0f
   private  var thread:Thread= Thread()
    private lateinit var thread1:Thread
    var data:Data=Data(this)
private  val minter=1000
private  var lastCheck:Long=0
    var count=0
    var mcount= RowDetails()
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {

        if (start1) {


           // var currTime=System.currentTimeMillis()
            start1 = false

            if (event != null) {
                if(event.sensor.type==Sensor.TYPE_GYROSCOPE) {
                    gxx = event.values[0]
                    gyy = event.values[1]
                    gzz = event.values[2]

                }

                if(event.sensor.type==Sensor.TYPE_LINEAR_ACCELERATION) {
                    xx = event.values[0]
                    yy = event.values[1]
                    zz = event.values[2]

                }




            }

            addEntry(xx,yy,zz,gxx,gyy,gzz)


        }

    }
    fun start2(v:View){
        start1=true
        sensorManager.registerListener(this,msensor,1000000,1000000)
        sensorManager.registerListener(this,gsensor,1000000,1000000)


    }

    fun stop2(v:View){
        start1=false
        sensorManager.unregisterListener(this)


    }
       // data.addData(ac_datax.text.toString().toFloat(),ac_datay.text.toString().toFloat(),ac_dataz.text.toString().toFloat(),tym)



    private fun addEntry(xx:Float,yy:Float,zz:Float,gxx:Float,gyy:Float,gzz:Float){
        val cal= Calendar.getInstance()

        val sdf= SimpleDateFormat("hh:mm:ss")

             val tym=sdf.format(cal.time)
                var l= data.addData(xx,yy,zz,gxx,gyy,gzz,tym)



            ac_datax.text="${xx} ::: ${gxx}"
            ac_datay.text="${yy} ::: ${gyy}"
            ac_dataz.text= "${zz} ::: ${gzz}"

       // Toast.makeText(this,c.getString(0),Toast.LENGTH_SHORT).show()
        sendData()
    }
    private fun sendData(){
      //  Toast.makeText(this,c.getString(0),Toast.LENGTH_SHORT).show()


        thread= Thread(Runnable {





                try{
                   // Toast.makeText(this,c.getString(0),Toast.LENGTH_SHORT).show()

                  Thread.sleep(20)
                    start1=true
                }catch (e:InterruptedException){
                    e.printStackTrace()
                }

        })
        thread.start()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitymain)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
     msensor=sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        gsensor=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        sensorManager.registerListener(this,msensor,1000000,1000000)
        sensorManager.registerListener(this,gsensor,1000000,1000000)

//
//        var intent=intent
//       afterCount= intent.getIntExtra("afterCount",count)


        act.setOnClickListener {

             try {
                 data.setcc(tests.text.toString().toInt())
                 val i = Intent(this, TableValues::class.java)


                 thread.interrupt()
                 sensorManager.unregisterListener(this)


                 startActivity(i)
                 this.finish()
             }catch(e:Exception)
             {
                 invalid.text="test    value cannot be empty "
             }
        }
    }

    override fun onPause() {
        super.onPause()
        thread.interrupt()
        sensorManager.unregisterListener(this)
    }

    override fun onDestroy() {
        sensorManager.unregisterListener(this)


        thread.interrupt()
        super.onDestroy()

    }

    override fun onPostResume() {
        super.onPostResume()
        sensorManager.registerListener(this,msensor,1000000,1000000)
        sensorManager.registerListener(this,gsensor,1000000,1000000)

    }

}
