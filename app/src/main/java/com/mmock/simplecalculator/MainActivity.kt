package com.mmock.simplecalculator

//Micah Mock
//11/20/2023
//CSC475 Platform-based development
//Colorado State University Global
//Professor Chintan Thakkar
//Assignment: CT1 built a simple calculator that can + - * and /
//Resources: https://youtu.be/4DGLcL4v6Qo?si=Q41Veg-TyYMCKDgY

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

private const val TAG = "MainActivity"
private var buttonState = "Add"
class MainActivity : AppCompatActivity() {
    private lateinit var tvAppTitleName: TextView
    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var tvOutLabel: TextView
    private lateinit var tvResult: TextView
    private lateinit var bnAdd: Button
    private lateinit var bnSub: Button
    private lateinit var bnMul: Button
    private lateinit var bnDiv: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get interface components
        tvAppTitleName = findViewById(R.id.titleText)
        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        tvOutLabel = findViewById(R.id.outPutTextLabel)
        tvResult = findViewById(R.id.output)
        bnAdd = findViewById(R.id.addButton)
        bnSub = findViewById(R.id.subtractButton)
        bnMul = findViewById(R.id.multiplyButton)
        bnDiv = findViewById(R.id.divisionButton)

        //set editText functionality
        input1.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "input1TextChanged $s")
                computeOperation()
            }

        })
        input2.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "input1TextChanged $s")
                computeOperation()
            }

        })

        //set button functionality
        bnAdd.setOnClickListener(){
            buttonState = "Add"
            computeOperation()
        }
        bnSub.setOnClickListener(){
            buttonState = "Subtract"
            computeOperation()
        }
        bnMul.setOnClickListener(){
            buttonState = "Multiply"
            computeOperation()
        }
        bnDiv.setOnClickListener(){
            buttonState = "Divide"
            computeOperation()
        }
    }

    private fun getDoubleFromEditText(editText: EditText): Double {
        return if (editText.text.isEmpty()){
            0.0
        } else
            editText.text.toString().toDouble()
    }

    private fun computeOperation(){
        val in1 = getDoubleFromEditText(input1)
        val in2 = getDoubleFromEditText(input2)

        val result = when (buttonState) {
            "Add" -> in1 + in2
            "Subtract" -> in1 - in2
            "Multiply" -> in1 * in2
            "Divide" -> {
                //avoid division by zero
                if (in2.toInt() != 0) {
                    in1 / in2
                } else {
                    tvResult.text = "Undefined"
                    return
                }
            }
            else -> {
                throw IllegalArgumentException("Invalid button state: $buttonState")
                return
            }
        }
        tvResult.text = "" + result
    }

}



