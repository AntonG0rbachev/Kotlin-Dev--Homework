package com.example.kotlin_dev__homework

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etInputTop = findViewById<EditText>(R.id.etInput1)
        val etInputMiddle = findViewById<EditText>(R.id.etInput2)
        val etInputBottom = findViewById<EditText>(R.id.etInput3)

        val arrayOfNumberDigits = arrayOf(
            R.id.btn0,
            R.id.btn1,
            R.id.btn2,
            R.id.btn3,
            R.id.btn4,
            R.id.btn5,
            R.id.btn6,
            R.id.btn7,
            R.id.btn8,
            R.id.btn9,
            R.id.btnComma
        )
        var currentText: String
        for (btn in arrayOfNumberDigits) {
            val currentButton = findViewById<Button>(btn)
            currentButton.setOnClickListener {
                if (btn == R.id.btnComma){
                    if (etInputBottom.text.isBlank()){
                        etInputBottom.setText("0.")
                    }
                    else if (!etInputBottom.text.toString().contains('.')){
                        currentText = etInputBottom.text.toString() + '.'
                        etInputBottom.setText(currentText)
                    }
                }
                else if (btn == R.id.btn0){
                    if (etInputBottom.text.toString() != "0"){
                        currentText = etInputBottom.text.toString() + currentButton.text.toString()
                        etInputBottom.setText(currentText)
                    }
                }
                else {
                    currentText = etInputBottom.text.toString() + currentButton.text.toString()
                    etInputBottom.setText(currentText)
                }
            }
        }

        val arrayOfOperation = arrayOf(
            R.id.btnDel,
            R.id.btnMul,
            R.id.btnMin,
            R.id.btnPlus,
            R.id.btnEqu
        )
        var result: String
        for (btn in arrayOfOperation) {
            val currentButton = findViewById<Button>(btn)
            currentButton.setOnClickListener {
                if (etInputBottom.text.isNotBlank() && etInputBottom.text.toString() != "-") {
                    if (etInputTop.text.isNotBlank()) {
                        result = resolve(
                            etInputTop
                                .text
                                .toString(),
                            etInputMiddle
                                .text
                                .toString(),
                            etInputBottom
                                .text
                                .toString()
                        )
                        if (result != ""){
                            etInputTop.setText(result)
                            etInputBottom.setText("")
                            if (btn == R.id.btnEqu) {
                                etInputTop.setText("")
                                etInputMiddle.setText("")
                                etInputBottom.setText(result)
                            }
                        }
                    }
                    else{
                        if (btn != R.id.btnEqu) {
                            etInputTop
                                .setText(
                                    etInputBottom
                                        .text
                                        .toString()
                                )
                            etInputBottom.setText("")
                        }
                    }
                    if (btn != R.id.btnEqu){
                        etInputMiddle
                            .setText(
                                currentButton
                                    .text
                                    .toString()
                            )
                    }
                }
                else{
                    if(btn == R.id.btnMin){
                        etInputBottom.setText("-")
                    }
                }
            }
        }

        val buttonAC = findViewById<Button>(R.id.btnAC)
        buttonAC.setOnClickListener {
            etInputBottom.setText("")
        }
    }

    private fun resolve(var1: String, operation: String, var2: String): String {
        val result = when (operation) {
            "+" -> var1.toDouble() + var2.toDouble()
            "-" -> var1.toDouble() - var2.toDouble()
            "*" -> var1.toDouble() * var2.toDouble()
            "/" -> {
                if (var2.toDouble() == 0.0) {
                    Toast.makeText(
                        this,
                        "Деление на ноль!",
                        Toast.LENGTH_SHORT
                    ).show()
                    ""
                } else {
                    var1.toDouble() / var2.toDouble()
                }
            }
            else -> {
                null
            }
        }
        return result.toString()
    }
}