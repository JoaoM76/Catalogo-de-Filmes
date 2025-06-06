package com.example.catalogofilme

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddMovieActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        dbHelper = DBHelper (this)

        val titleEdit = findViewById<EditText>(R.id.editTitle)
        val descEdit = findViewById<EditText>(R.id.editDescription)
        val yearEdit = findViewById<EditText>(R.id.editYear)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val title = titleEdit.text.toString()
            val desc = descEdit.text.toString()
            val year = yearEdit.text.toString().toIntOrNull()

            if (title.isNotEmpty() && desc.isNotEmpty() && year != null) {
                val movie = Movie(title = title, description = desc, year = year)
                dbHelper.addMovie(movie)
                Toast.makeText(this, "Filme salvo!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Preencha todos os campos corretamente!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
