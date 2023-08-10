package com.neslihanyagmurca.kotlincalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.neslihanyagmurca.kotlincalculatorapp.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
        btn0.appendClick("0")
            btn1.appendClick("1")
            btn2.appendClick("2")
            btn3.appendClick("3")
            btn4.appendClick("4")
            btn5.appendClick("5")
            btn6.appendClick("6")
            btn7.appendClick("7")
            btn8.appendClick("8")
            btn9.appendClick("9")
            btnDecal.appendClick("-")
            btnMultiply.appendClick("*")
            btnSum.appendClick("+")
            btnDivide.appendClick("/")
            btnBrackets.appendClick("(")
            btnBrackets.setOnLongClickListener {
                binding.processEditText.append(")")
                true
            }
            btnPoint.appendClick(".")
            btnAc.setOnClickListener {
                binding.processEditText.text = null
                binding.liveResultEditText.text = ""
            }
            btnDelete.setOnClickListener {
                val expression = processEditText.text.toString()
                if (expression.isNotEmpty()) {
                    processEditText.text = expression.substring(0, expression.length - 1)
                }
            }
            btnResult.setOnClickListener {
                try {
                    val expression = ExpressionBuilder(binding.processEditText.text.toString()).build()
                    val result = expression.evaluate()
                    val longResult = result.toLong()

                    if (result == longResult.toDouble()) {
                        binding.liveResultEditText.text = longResult.toString()
                    } else {
                        binding.liveResultEditText.text = result.toString()
                    }
                }catch (e:Exception){
                    Log.d("Exception", "Message: ${e.message}")
                }


            }


        }

    }
    private fun View.appendClick(string: String) {
        setOnClickListener {
            binding.processEditText.append(string)
        }
    }

}