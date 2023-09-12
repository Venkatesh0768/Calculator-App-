package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvInput : TextView? = null
    var lastnumeric : Boolean =false
    var lastDot : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvinput)
    }

    fun onDigit(view : View){
        tvInput?.append((view as Button).text)
        lastnumeric = true
        lastDot = false
    }
    fun onClear(view: View){
        tvInput?.text=""
    }
    fun onBack(view: View){
        var result1 = tvInput?.text.toString()
       tvInput?.text = result1.substring(0 ,result1.length-1)


    }

    fun ondecimal(view: View){
        if (lastnumeric && !lastDot){
            tvInput?.append(".")
            lastnumeric= false
            lastDot = true
        }
    }
    fun onoperator(view: View){
        tvInput?.text?.let {
            if(lastnumeric && !isoperatoradd(it.toString())){
                tvInput?.append((view as Button).text)
                lastnumeric = false
                lastDot =false
            }
        }
    }
    fun onEqual(view: View){
        if(lastnumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix+ one
                    }
                    tvInput?.text = removeZero((one.toDouble() - two.toDouble()).toString())
                }else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix+ one
                    }
                    tvInput?.text = removeZero((one.toDouble() + two.toDouble()).toString())
                }else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix+ one
                    }
                    tvInput?.text = removeZero((one.toDouble() * two.toDouble()).toString())
                }else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix+ one
                    }
                    tvInput?.text = removeZero((one.toDouble() / two.toDouble()).toString())
                }else if(tvValue.contains("%")){
                    val splitValue = tvValue.split("%")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix+ one
                    }
                    tvInput?.text = removeZero((one.toDouble() % two.toDouble()).toString())
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }

    }

    private fun isoperatoradd(value : String) : Boolean{
        return if(value.startsWith("-")){
            false
        }else{
              value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                      ||value.contains("-")
        }
    }
    private fun removeZero(result: String) : String {
         var value  =  result
        if(result.contains(".0"))
            value = result.substring(0  , result.length - 2)
        return value
    }


}