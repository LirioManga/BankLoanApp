package com.example.emprestimobank

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculateButton: Button = findViewById(R.id.calculateButton)
        calculateButton.setOnClickListener {

            val principal = findViewById<EditText>(R.id.principalInput).text.toString().toDoubleOrNull()
            val installments = findViewById<EditText>(R.id.installmentsInput).text.toString().toIntOrNull()
            if (principal == null || installments == null) {
                Toast.makeText(this, "Por favor insira todos os campos corretamente", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val totalPayment = calculateLoan(principal, installments)
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("totalPayment", totalPayment)
            startActivity(intent)
        }
    }

    private fun calculateLoan(principal: Double, installments: Int): Double {
        val interestRate = when {
            installments == 1 -> 0.15
            installments in 2..4 -> 0.20
            installments in 5..7 -> 0.25
            else -> 0.30 * installments
        }esle
        return principal + (principal * interestRate)
    }
}