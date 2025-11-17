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
        // Validation: Only accepts integers 0-9
        binding.btnPush.setOnClickListener {
            val input = binding.editTextNumber.text.toString()
            if (input.isNotEmpty()) {
                try {
                    val number = input.toInt()

                    // Check if number is in valid range (0-9)
                    // Feature: As a player, I want to be able to add only integers 0-9
                    if (number in 0..9) {
                        // Check if stack has room for more items (max 3 items)
                        // Feature: As a player, I want the StackApp to hold only 3 integers max at a time
                        if (stack.size < 3) {
                            stack.push(number)
                            binding.editTextNumber.text.clear()
                            updateStackDisplay()
                        }
                        else if (stack.size == 3) {
                                updateStackDisplay()
                        }
                    } else {
                        // Display error message for out of range values
                        Toast.makeText(this, "Please enter a number between 0 and 9", Toast.LENGTH_SHORT).show()
                    }
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
        }
        else if (stack.size == 3) {
            binding.stackDisplay.text = "Stack is full\n${stack.reversed().joinToString(separator = "\n")}"
            Toast.makeText(this, "Stack is full! Maximum 3 items allowed. Pop an item first.", Toast.LENGTH_SHORT).show()

        }
        else {
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