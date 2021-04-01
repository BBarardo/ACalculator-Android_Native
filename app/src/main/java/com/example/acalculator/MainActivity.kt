package com.example.acalculator

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
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

//    private var hist = arrayListOf("1+1=1", "2+3=5");

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
        Operation("2+3", 5.0),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "O método onCreate foi invocado")
        setContentView(R.layout.activity_main)
        Toast.makeText(this, hours, Toast.LENGTH_SHORT).show()


//            list_historic.setOnItemClickListener { parent, view, position, id ->
//                val element = list_historic?.adapter?.getItem(position) // The item that was clicked
//                Toast.makeText(this, element.toString().substringAfter('='), Toast.LENGTH_SHORT)
//                    .show()
//            }

    }

    override fun onDestroy() {
        Log.i(TAG, "O método onDestroy foi invocado")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run { putString(VISOR_KEY, text_visor.text.toString()) }
//        outState.run { putStringArrayList(HISTORY_KEY, hist) }
        outState.run { putParcelableArrayList(HISTORY_KEY, operations as ArrayList<Operation>) }

        Log.i(
            TAG,
            "ON SAVE operations as ArrayList<Operation>: ${operations as ArrayList<Operation>}"
        )
        Log.i(TAG, "ON SAVE operations: $operations")

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        text_visor.text = savedInstanceState?.getString(VISOR_KEY)
//        hist = savedInstanceState?.getStringArrayList(HISTORY_KEY) as ArrayList<String>
        operations =
            savedInstanceState?.getParcelableArrayList<Operation>(HISTORY_KEY) as MutableList<Operation>
//        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "ON RESTORE operations: $operations")

        if (this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            list_historic?.layoutManager = LinearLayoutManager(this)
            list_historic?.adapter =
                HistoryAdapter(this, R.layout.item_expression, operations)
        }

    }

    override fun onStart() {
        Toast.makeText(this, hours, Toast.LENGTH_SHORT).show()
        super.onStart()

//        button_0.setOnClickListener { onClickSymbol("0") }
//        button_1.setOnClickListener { onClickSymbol("1") }
//        button_2.setOnClickListener { onClickSymbol("2") }
//        button_3.setOnClickListener { onClickSymbol("3") }
//        button_4.setOnClickListener { onClickSymbol("4") }
//        button_5.setOnClickListener { onClickSymbol("5") }
//        button_6.setOnClickListener { onClickSymbol("6") }
//        button_7.setOnClickListener { onClickSymbol("7") }
//        button_8.setOnClickListener { onClickSymbol("8") }
//        button_9.setOnClickListener { onClickSymbol("9") }
//
//        button_ce.setOnClickListener { onClickSymbol("CE") }
//        button_backspace.setOnClickListener { onClickSymbol("<") }
//        button_division.setOnClickListener { onClickSymbol("/") }
//        button_multiply.setOnClickListener { onClickSymbol("*") }
//        button_minus.setOnClickListener { onClickSymbol("-") }
//        button_adition.setOnClickListener { onClickSymbol("+") }
//        button_dot.setOnClickListener { onClickSymbol(".") }
        button_equals.setOnClickListener { onClickEquals() }

//        list_historic.setOnItemClickListener { parent, view, position, id ->
//            val element = list_historic?.adapter?.getItem(position) // The item that was clicked
//            Toast.makeText(this, element.toString().substringAfter('='), Toast.LENGTH_SHORT).show()
//        }
    }

    fun onClickSymbol(view: View /*symbol: String*/) {
        val symbol = view.tag.toString()
        Log.i(TAG, "Click no botão $symbol")

        if (symbol == "0") {
            if (text_visor.text != "0") {
                text_visor.append("0")
            }
        } else if (symbol == "00") {
            if (text_visor.text != "0") {
                text_visor.append("00")
            }
        } else if (Regex("[1-9.]").matches(symbol)) {
            if (text_visor.text == "0") {
                text_visor.text = symbol
            } else {
                text_visor.append(symbol)
            }
        } else if (symbol == "CE") {
            text_visor.text = "0";
        } else if (symbol == "<") {
            if (text_visor.text.length == 1) {
                text_visor.text = "0"
            } else {
                text_visor.text = text_visor.text.subSequence(0, text_visor.text.length - 1)
            }
        } else if (symbol == "+") {
            text_visor.append("+")
        } else if (symbol == "-") {
            text_visor.append("-")
        } else if (symbol == "*") {
            text_visor.append("*")
        } else if (symbol == "/") {
            text_visor.append("/")
        } else if (symbol == "%") {
            text_visor.append("%")
        }
    }

    private fun onClickEquals() {
        Log.i(TAG, "Click no botão =")
//        var toHist = text_visor.text.toString()
        val toOperationExp = text_visor.text.toString()
        val expression = ExpressionBuilder(text_visor.text.toString()).build()
        text_visor.text = expression.evaluate().toString()
        Log.i(TAG, "O resultado da expressão é ${text_visor.text}")
//        toHist += "=" + text_visor.text.toString()
        val toOperationRes = text_visor.text.toString().toDouble()
//        hist.add(toHist)
        operations.add(Operation(toOperationExp, toOperationRes))
//        Log.i(TAG, toHist)

//        list_historic?.adapter =
//            HistoryAdapter(this, R.layout.item_expression, hist)
        list_historic?.adapter =
            HistoryAdapter(this, R.layout.item_expression, operations)

        Log.i(TAG, "OPERATIONS ON EQUALS ${operations}")

    }

    fun onClickHistory(view: View) {
        val intent = Intent(this, HistoryActivity::class.java)
        intent.apply {
            putParcelableArrayListExtra(
                EXTRA_HISTORY,
                ArrayList(operations)
            )
        }
        startActivity(intent)
//        finish()
    }

}