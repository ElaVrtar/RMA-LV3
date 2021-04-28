package com.example.birdscounter

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setPreferences()
        setListeners()
    }

    private fun setPreferences() {
        sharedPreferences = getSharedPreferences(getString(R.string.str_bird_states), Context.MODE_PRIVATE)
        tv_counter.text=sharedPreferences.getString(getString(R.string.str_bird_count_states),getString(R.string.str_initial_value))
        layout_main.background=ColorDrawable(sharedPreferences.getInt(getString(R.string.str_bird_color),ContextCompat.getColor(this,R.color.white)))
        btn_yellow.text=sharedPreferences.getString(getString(R.string.str_bird_yellow),getString(R.string.str_initial_value))
        btn_green.text=sharedPreferences.getString(getString(R.string.str_bird_green),getString(R.string.str_initial_value))
        btn_red.text=sharedPreferences.getString(getString(R.string.str_bird_red),getString(R.string.str_initial_value))
        btn_brown.text=sharedPreferences.getString(getString(R.string.str_bird_brown),getString(R.string.str_initial_value))
    }

    private fun setListeners() {
        btn_yellow.setOnClickListener(this)
        btn_green.setOnClickListener(this)
        btn_red.setOnClickListener(this)
        btn_brown.setOnClickListener(this)
        btn_reset.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        if (view != null) {


            if (view != btn_reset) {
                val button: Button = findViewById(view.id)
                button.text = (button.text.toString().toInt() + 1).toString()
                layout_main.background = button.background
                tv_counter.text = (tv_counter.text.toString().toInt() + 1).toString()
            } else {
                btn_yellow.setText(R.string.str_initial_value)
                btn_green.setText(R.string.str_initial_value)
                btn_red.setText(R.string.str_initial_value)
                btn_brown.setText(R.string.str_initial_value)
                layout_main.background = ColorDrawable(ContextCompat.getColor(this, R.color.white))
                tv_counter.text = getString(R.string.str_initial_value)
            }
        }
    }

    override fun onDestroy() {

        sharedPreferences.edit().putString(getString(R.string.str_bird_count_states),tv_counter.text.toString()).apply()
        sharedPreferences.edit().putInt(getString(R.string.str_bird_color),(layout_main.background as ColorDrawable).color).apply()
        sharedPreferences.edit().putString(getString(R.string.str_bird_yellow),btn_yellow.text.toString()).apply()
        sharedPreferences.edit().putString(getString(R.string.str_bird_green),btn_green.text.toString()).apply()
        sharedPreferences.edit().putString(getString(R.string.str_bird_red),btn_red.text.toString()).apply()
        sharedPreferences.edit().putString(getString(R.string.str_bird_brown),btn_brown.text.toString()).apply()

        super.onDestroy()
    }
}
