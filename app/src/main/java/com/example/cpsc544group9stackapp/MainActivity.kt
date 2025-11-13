package com.example.cpsc544group9stackapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cpsc544group9stackapp.databinding.ActivityMainBinding
import java.util.Stack

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private val stack = Stack<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupButtons()
        updateStackDisplay()
    }

    private fun setupButtons() {
        // Push button - adds number to stack
        binding.btnPush.setOnClickListener {
            val input = binding.editTextNumber.text.toString()
            if (input.isNotEmpty()) {
                try {
                    val number = input.toInt()
                    stack.push(number)
                    binding.editTextNumber.text.clear()
                    updateStackDisplay()
                    Toast.makeText(this, "Pushed: $number", Toast.LENGTH_SHORT).show()
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
            }
        }

        // Pop button - removes top element from stack
        binding.btnPop.setOnClickListener {
            if (stack.isNotEmpty()) {
                val poppedValue = stack.pop()
                updateStackDisplay()
                Toast.makeText(this, "Popped: $poppedValue", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Stack is empty", Toast.LENGTH_SHORT).show()
            }
        }

        // Quit button - exits the app
        binding.btnQuit.setOnClickListener {
            quitApplication()
        }
    }

    private fun updateStackDisplay() {
        if (stack.isEmpty()) {
            binding.stackDisplay.text = "Stack is empty"
        } else {
            // Display stack from top to bottom
            binding.stackDisplay.text = "Stack (top to bottom):\n${stack.reversed().joinToString("\n")}"
        }
    }

    // Function to quit the application
    // Called when the Quit button is pressed
    private fun quitApplication() {
        finish()
    }
}