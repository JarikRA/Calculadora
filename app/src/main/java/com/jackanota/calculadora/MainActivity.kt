package com.jackanota.calculadora

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.mariuszgromada.math.mxparser.*

@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvOperations: TextView
    private lateinit var tvResult: TextView
    private lateinit var buttons: Map<Int, Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                try {
                    val result = eval(s.toString())

                    tvResult.text = if (result.toString() == "NaN") "" else result.toString()
                } catch (e: Exception) {
                    tvResult.text = "Error"
                }
            }
        }

        tvOperations.addTextChangedListener(textWatcher)
    }

    private fun initComponents() {
        tvOperations = findViewById(R.id.tv_operations)
        tvResult = findViewById(R.id.tv_result)

        buttons = mapOf(
            R.id.btn_zero to findViewById(R.id.btn_zero),
            R.id.btn_one to findViewById(R.id.btn_one),
            R.id.btn_two to findViewById(R.id.btn_two),
            R.id.btn_three to findViewById(R.id.btn_three),
            R.id.btn_four to findViewById(R.id.btn_four),
            R.id.btn_five to findViewById(R.id.btn_five),
            R.id.btn_six to findViewById(R.id.btn_six),
            R.id.btn_seven to findViewById(R.id.btn_seven),
            R.id.btn_eight to findViewById(R.id.btn_eight),
            R.id.btn_nine to findViewById(R.id.btn_nine),
            R.id.btn_point to findViewById(R.id.btn_point),
            R.id.btn_delete to findViewById(R.id.btn_delete),
            R.id.btn_equals to findViewById(R.id.btn_equals),
            R.id.btn_add to findViewById(R.id.btn_add),
            R.id.btn_sub to findViewById(R.id.btn_sub),
            R.id.btn_mul to findViewById(R.id.btn_mul),
            R.id.btn_div to findViewById(R.id.btn_div),
            R.id.btn_open_parenthesis to findViewById(R.id.btn_open_parenthesis),
            R.id.btn_close_parentesis to findViewById(R.id.btn_close_parentesis),
            R.id.btn_ac to findViewById(R.id.btn_ac)
        )
        buttons.values.forEach { it.setOnClickListener(this) }
    }

    fun eval(expression: String): Double {
        val e = Expression(expression)
        return e.calculate()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_zero -> {
                tvOperations.append("0")
            }

            R.id.btn_one -> {
                tvOperations.append("1")
            }

            R.id.btn_two -> {
                tvOperations.append("2")
            }

            R.id.btn_three -> {
                tvOperations.append("3")
            }

            R.id.btn_four -> {
                tvOperations.append("4")
            }

            R.id.btn_five -> {
                tvOperations.append("5")
            }

            R.id.btn_six -> {
                tvOperations.append("6")
            }

            R.id.btn_seven -> {
                tvOperations.append("7")
            }

            R.id.btn_eight -> {
                tvOperations.append("8")
            }

            R.id.btn_nine -> {
                tvOperations.append("9")
            }

            R.id.btn_point -> {
                addOperator(".")
            }

            R.id.btn_delete -> {
                if (tvOperations.text.isNotEmpty()) {
                    tvOperations.text =
                        tvOperations.text.subSequence(0, tvOperations.text.length - 1)
                }
            }

            R.id.btn_equals -> {

                val resultText = tvResult.text.toString()
                val message = "Expresión no válida"
                if (resultText.isEmpty() || resultText == message) {
                    tvResult.text = message
                    return
                }

                tvOperations.text = resultText
            }

            R.id.btn_add -> {
                addOperator("+")
            }

            R.id.btn_sub -> {
                addOperator("-")
            }

            R.id.btn_mul -> {
                addOperator("*")
            }

            R.id.btn_div -> {
                addOperator("/")
            }

            R.id.btn_open_parenthesis -> {
                tvOperations.append("(")
            }

            R.id.btn_close_parentesis -> {
                tvOperations.append(")")
            }

            R.id.btn_ac -> {
                tvOperations.text = ""
            }
        }
    }

    private fun addOperator(operator: String) {
        if (!tvOperations.text.toString().endsWith(operator)) {
            tvOperations.append(operator)
        }
    }
}