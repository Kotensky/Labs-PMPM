package com.kotensky.lab1calc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.sqrt

class MainActivity : AppCompatActivity() {

    private var isStartOver = false
    private var isZeroDiv = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clear_btn.setOnClickListener {
            clearField()
        }
    }

    private fun clearField() {
        main_field_txt.text = "0"
        first_number_txt.text = ""
        operation_txt.text = ""
        isZeroDiv = false
    }

    fun onClickNumber(v: View) {
        if (isStartOver || isZeroDiv) {
            clearField()
            isStartOver = false
        }

        if (v !is TextView || main_field_txt.text.length >= 11)
            return
        if (v.text == "." && first_number_txt.text.toString().contains('.')) {
            return
        }
        if (main_field_txt.text.toString().toDouble() == 0.0)
            main_field_txt.text = ""

        main_field_txt.append(v.text)
        main_field_scroll.post({
            main_field_scroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
        })
    }

    fun onClickBackspace(view: View) {
        if (isZeroDiv) {
            clearField()
            return
        }
        if (main_field_txt.text.isEmpty())
            return
        main_field_txt.text = main_field_txt.text.toString().substring(0, main_field_txt.text.length - 1)
        if (main_field_txt.text.isEmpty())
            main_field_txt.text = "0"
    }


    fun onClickOperation(view: View) {
        if (isZeroDiv) {
            clearField()
            return
        }
        if (operation_txt.text.isEmpty()) {
            first_number_txt.text = main_field_txt.text.toString()
            main_field_txt.text = "0"
        }
        operation_txt.text = when (view.id) {
            R.id.plus_btn -> "+"
            R.id.mul_btn -> "*"
            R.id.minus_btn -> "-"
            R.id.div_btn -> "/"
            else -> ""
        }
    }

    fun onClickSqrt(view: View) {
        if (isZeroDiv) {
            clearField()
            return
        }
        val number =
                if (!first_number_txt.text.isEmpty()) {
                    first_number_txt.text.toString().toDouble()
                } else if (!main_field_txt.text.isEmpty()) {
                    main_field_txt.text.toString().toDouble()
                } else {
                    0.0
                }
        clearField()
        main_field_txt.text = "${sqrt(number)}"
    }

    fun onClickEqual(view: View) {
        if (isZeroDiv)
            return

        var firstNumber =
                if (first_number_txt.text.isEmpty()) {
                    0.0
                } else {
                    first_number_txt.text.toString().toDouble()
                }
        val secondNumber =
                if (main_field_txt.text.isEmpty()) {
                    0.0
                } else {
                    main_field_txt.text.toString().toDouble()
                }
        val operation = operation_txt.text.toString()
        when (operation) {
            "+" -> firstNumber += secondNumber
            "-" -> firstNumber -= secondNumber
            "*" -> firstNumber *= secondNumber
            "/" -> {
                if (secondNumber == 0.0) {
                    clearField()
                    isZeroDiv = true
                    main_field_txt.text = getString(R.string.zero_div_error)
                    return
                } else {
                    firstNumber /= secondNumber
                }
            }
            else -> firstNumber = secondNumber
        }
        clearField()
        main_field_txt.text = "$firstNumber"
        isStartOver = true
    }
}