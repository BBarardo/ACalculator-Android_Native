package com.example.acalculator

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.OnClick
import butterknife.Optional
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_calculator.*
import net.objecthunter.exp4j.ExpressionBuilder

const val EXTRA_HISTORY = "com.example.acalculator.HISTORY"

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    private var cal = java.util.Calendar.getInstance()
    private var hours =
        "" + cal.time.hours.toString().padStart(2, '0') + ":" + cal.time.minutes.toString()
            .padStart(2, '0') + ":" + cal.time.seconds.toString().padStart(2, '0')

    private val VISOR_KEY = "visor"
    private val HISTORY_KEY = "history"

    private var operations = mutableListOf<Operation>(
        Operation("1+1", 2.0),
        Operation("2+3", 5.0),
        Operation("1+1", 2.0),
        Operation("2+3", 5.0),
        Operation("1+1", 2.0),
        Operation("2+3", 5.0),
        Operation("1+1", 2.0),
        Operation("2+3", 5.0),
        Operation("1+1", 2.0),
        Operation("2+3", 5.0),
        Operation("1+1", 2.0),
        Operation("2+3", 5.0),
        Operation("1+1", 2.0),
        Operation("2+3", 5.0),
        Operation("1+1", 2.0),
        Operation("2+3", 5.0),
        Operation("1+1", 2.0),
        Operation("2+3", 5.0),
        Operation("1+1", 2.0),
        Operation("2+3", 5.0),
        Operation("1+1", 2.0),
        Operation("2+3", 5.0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "O método onCreate foi invocado")
        Toast.makeText(this, hours, Toast.LENGTH_SHORT).show()
        NavigationManager.goToCalculatorFragment(supportFragmentManager, operations)

        setContentView(R.layout.activity_main)
    }


}