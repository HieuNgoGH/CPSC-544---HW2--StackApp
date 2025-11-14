package com.example.cpsc544group9stackapp

import android.os.Bundle
import android.widget .* // Added for the new imports
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cpsc544group9stackapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    private val stack = IntArray(3)
    private var top = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)){ v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set the click listener for the button.
        binding.btnPush.setOnClickListener {
            val command = binding.inputField.text.toString().trim()
            handlePushCommand(command)
            binding.inputField.text.clear() // Clear input after processing.
        }

        // Update the display for the initial state.
        updateStackDisplay()

    }// <-- The 'onCreate' method ends here.

    // This function is now correctly defined within the class, not inside onCreate.
    private fun handlePushCommand(command: String) {
        val parts = command.lowercase().split(" ")
        if (parts.size == 2 && parts[0] == "push") {
            val value = parts[1].toIntOrNull()
            if (value != null && value in 0..9) {
                if (top < 2) { // Stack indices are 0, 1, 2.
                    top++
                    stack[top] = value
                    binding.messageView.text = "Success: $value pushed to stack"
                } else {
                    binding.messageView.text = "Cannot Push: stack is full (3/3)"
                }
            } else {
                binding.messageView.text = "Invalid input: N must be a single digit (0-9)"
            }
        } else {
            binding.messageView.text = "Invalid command format. Use 'push N'."
        }

        // This must be called to refresh the UI after every command.
        updateStackDisplay()
    }

    // This function is also correctly defined within the class.
    private fun updateStackDisplay() {
        val currentStack = if (top >= 0) {
            stack.slice(0..top).joinToString(separator = ", ", prefix = "[", postfix = "]")
        } else {
            "[]"
        }
        binding.stackView.text = "Stack: $currentStack"
    }

}// <-- The 'MainActivity' class ends here.