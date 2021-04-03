package com.example.acalculator

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import kotlinx.android.synthetic.main.fragment_calculator.*
import kotlinx.android.synthetic.main.fragment_calculator.list_historic
import net.objecthunter.exp4j.ExpressionBuilder
import java.util.ArrayList

private const val INIT_OPERATIONS = "initOperations"


class CalculatorFragment : Fragment() {
    private val TAG = MainActivity::class.java.simpleName

    private var operations = mutableListOf<Operation>()


    override fun onStart() {
        super.onStart()

        list_historic?.layoutManager = LinearLayoutManager(activity as Context)
        list_historic?.adapter = HistoryAdapter(activity as Context, R.layout.item_expression, operations)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            operations = it.getParcelableArrayList<Operation>(INIT_OPERATIONS) as MutableList<Operation>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container,false)
        ButterKnife.bind(this, view)
        // Inflate the layout for this fragment
        return view
    }


    @Optional
    @OnClick(
        R.id.button_0,
        R.id.button_00,
        R.id.button_1,
        R.id.button_2,
        R.id.button_3,
        R.id.button_4,
        R.id.button_5,
        R.id.button_6,
        R.id.button_7,
        R.id.button_8,
        R.id.button_9,
        R.id.button_adition,
        R.id.button_backspace,
        R.id.button_ce,
        R.id.button_division,
        R.id.button_dot,
        R.id.button_minus,
        R.id.button_multiply
    )
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

    @OnClick(R.id.button_equals)
    fun onClickEquals() {
        Log.i(TAG, "Click no botão =")

        val toOperationExp = text_visor.text.toString()
        val expression = ExpressionBuilder(text_visor.text.toString()).build()
        text_visor.text = expression.evaluate().toString()
        Log.i(TAG, "O resultado da expressão é ${text_visor.text}")

        val toOperationRes = text_visor.text.toString().toDouble()

        operations.add(Operation(toOperationExp, toOperationRes))

        list_historic?.adapter =
            HistoryAdapter(activity as Context, R.layout.item_expression, operations)

        Log.i(TAG, "OPERATIONS ON EQUALS ${operations}")
    }

    @OnClick(R.id.floatingActionButton)
    fun onClickHistory(view: View) {
        Log.i(TAG,"onClickHistory" )

        NavigationManager.goToHistoryFragment(activity?.supportFragmentManager!!, operations)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 init_operations.
         * @return A new instance of fragment HistoryFragment.
         */
        @JvmStatic
        fun newInstance(operations: MutableList<Operation>?): CalculatorFragment {
            val args = Bundle()
            args.putParcelableArrayList(INIT_OPERATIONS, operations as ArrayList<out Parcelable>)
            val fragment = CalculatorFragment()
            fragment.arguments = args
            return fragment
        }
    }

}