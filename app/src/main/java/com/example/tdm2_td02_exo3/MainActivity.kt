package com.example.tdm2_td02_exo3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
companion object{
    private var HOUR_CODE  = 0
    val listHours = ArrayList<String>()
}
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listHours.add(first_priere_edit.text.toString())
        listHours.add(second_priere_edit.text.toString())
        listHours.add(third_priere_edit.text.toString())
        listHours.add(fourth_priere_edit.text.toString())
        listHours.add(fifth_priere_edit.text.toString())

        time_picker.setIs24HourView(true)
        first_priere_edit.setOnClickListener {
            time_picker.visibility = View.VISIBLE
            check_button.visibility = View.VISIBLE
            HOUR_CODE  = 1
        }
        second_priere_edit.setOnClickListener {
            time_picker.visibility = View.VISIBLE
            check_button.visibility = View.VISIBLE
            HOUR_CODE  = 2
        }
        third_priere_edit.setOnClickListener {
            time_picker.visibility = View.VISIBLE
            check_button.visibility = View.VISIBLE
            HOUR_CODE  = 3
        }
        fourth_priere_edit.setOnClickListener {
            time_picker.visibility = View.VISIBLE
            check_button.visibility = View.VISIBLE
            HOUR_CODE  = 4
        }
        fifth_priere_edit.setOnClickListener {
            time_picker.visibility = View.VISIBLE
            check_button.visibility = View.VISIBLE
            HOUR_CODE  = 5
        }
        check_button.setOnClickListener {
            time_picker.visibility = View.GONE
            check_button.visibility = View.GONE
            when(HOUR_CODE){
                1 -> {
                    first_priere_edit.text = ""+time_picker.currentHour + ":"+time_picker.currentMinute
                    listHours[0] = first_priere_edit.text.toString()
                }

                2 -> {
                    second_priere_edit.text = ""+time_picker.currentHour + ":"+time_picker.currentMinute
                    listHours[1] = second_priere_edit.text.toString()
                }
                3 -> {
                    third_priere_edit.text = ""+time_picker.currentHour + ":"+time_picker.currentMinute
                    listHours[2] = third_priere_edit.text.toString()
                }
                4 -> {
                    fourth_priere_edit.text = ""+time_picker.currentHour + ":"+time_picker.currentMinute
                    listHours[3] = fourth_priere_edit.text.toString()
                }
                5 -> {
                    fifth_priere_edit.text = ""+time_picker.currentHour + ":"+time_picker.currentMinute
                    listHours[4] = fifth_priere_edit.text.toString()
                }
            }
        }
        val serviceIntent = Intent(this, MyService::class.java)
        ContextCompat.startForegroundService(this, serviceIntent)

    }
}
