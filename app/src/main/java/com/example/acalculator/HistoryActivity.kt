package com.example.acalculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class HistoryActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
    }

    override fun onStart() {
        super.onStart()

//        val hist = intent.getStringArrayListExtra(EXTRA_HISTORY)
//        hist?.let {
//            list_historic.adapter =
//                HistoryAdapter(this, R.layout.item_expression, it)
//
//        }

        val operations =
            intent.getParcelableArrayListExtra<Operation>(EXTRA_HISTORY)
        Log.i(TAG, "AQUIIII: $operations")
        list_historic.layoutManager = LinearLayoutManager(this)
        list_historic.adapter = HistoryAdapter(this, R.layout.item_expression, operations)

//        list_historic.adapter = HistoryAdapter(this, R.layout.item_expression,
//            operations )
    }
}