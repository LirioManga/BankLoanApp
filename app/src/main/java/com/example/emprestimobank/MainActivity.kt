package com.example.emprestimobank

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val installmentsSpinner: Spinner = findViewById(R.id.installmentsSpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.installments_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            installmentsSpinner.adapter = adapter
        }

        val calculateButton: Button = findViewById(R.id.calculateButton)
        calculateButton.setOnClickListener {
            val principal = findViewById<EditText>(R.id.principalInput).text.toString().toDoubleOrNull()
            val installments = installmentsSpinner.selectedItem.toString().toIntOrNull()

            if (principal == null || installments == null) {
                Toast.makeText(this, "Por favor insira todos os campos correctamente", Toast.LENGTH_SHORT).show()
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
            installments >= 8 -> 0.30 * installments
            else -> 0.0
        }
        return principal + (principal * interestRate)
    }
}