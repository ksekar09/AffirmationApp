package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var button: Button
    private lateinit var selectedCategory: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinner)
        button = findViewById(R.id.button)

        // Spinner setup
        val categories = arrayOf("Self-Love", "Motivation", "Gratitude", "Success", "Health")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Spinner onItemSelectedListener
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedCategory = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

        // Button setOnClickListener
        button.setOnClickListener {
            val affirmation = getRandomAffirmation(selectedCategory)
            Toast.makeText(this, "Affirmation: $affirmation", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getRandomAffirmation(category: String): String {
        val affirmations = mapOf(
            "Self-Love" to listOf("I am worthy of love and respect.", "I love myself unconditionally.", "I am enough."),
            "Motivation" to listOf("I can achieve anything I set my mind to.", "Every day is a new opportunity.", "I am capable of great things."),
            "Gratitude" to listOf("I am grateful for all that I have.", "I appreciate the little things in life.", "Gratitude brings me joy."),
            "Success" to listOf("I am successful in everything I do.", "I attract success and abundance.", "My hard work pays off."),
            "Health" to listOf("I am healthy and full of energy.", "I take care of my body and mind.", "My body is strong and capable.")
        )
        val categoryAffirmations = affirmations[category] ?: listOf("No affirmations available")
        return categoryAffirmations[Random.nextInt(categoryAffirmations.size)]
    }
}
