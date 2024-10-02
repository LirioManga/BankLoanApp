package com.example.emprestimobank

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val totalPayment = intent.getDoubleExtra("totalPayment", 0.0)
        findViewById<TextView>(R.id.resultTextView).text = String.format("Valor  a pagar: %.2f ", totalPayment, "Mt")
        Toast.makeText(this, "Total a pagar: %.2f".format(totalPayment), Toast.LENGTH_LONG).show()

        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}