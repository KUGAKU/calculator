package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){
        val textView = findViewById<TextView>(R.id.textView)
        textView.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View){
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = ""
        lastDot = false
        lastNumeric = false
    }

    fun onDecimalPoint(view: View) {
        if(lastNumeric && !lastDot) {
            val textView = findViewById<TextView>(R.id.textView)
            textView.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual(view: View) {
        val textView = findViewById<TextView>(R.id.textView)
        if (lastNumeric){
            var textViewValue = textView.text.toString()
            var prefix = ""
            try{
                if(textViewValue.startsWith("-")) {
                    prefix = "-"
                    textViewValue = textViewValue.substring(1)
                }
                if (textViewValue.contains("-")) {
                    val splitValue = textViewValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    textView.text = (one.toDouble() - two.toDouble()).toString()
                }
                else if (textViewValue.contains("+")) {
                    val splitValue = textViewValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    textView.text = (one.toDouble() + two.toDouble()).toString()
                }
                else if (textViewValue.contains("/")) {
                    val splitValue = textViewValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    textView.text = (one.toDouble() / two.toDouble()).toString()
                }
                else if (textViewValue.contains("*")) {
                    val splitValue = textViewValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    textView.text = (one.toDouble() * two.toDouble()).toString()
                }

            }catch(e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun onOperator(view: View) {
        val textView = findViewById<TextView>(R.id.textView)
        if(lastNumeric && !isOperatorAdded(textView.text.toString())) {
            textView.append((view as Button).text)
            lastDot = false
        }
    }

    private fun isOperatorAdded(value: String) : Boolean{
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}