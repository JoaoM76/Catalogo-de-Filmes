package com.example.catalogofilme

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        btnAdd = findViewById(R.id.btnAddMovie)

        recyclerView.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener {
            val intent = Intent(this, AddMovieActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        loadMovies()
    }

    private fun loadMovies() {
        val movies = dbHelper.getAllMovies().toMutableList()
        recyclerView.adapter = MovieAdapter(movies) { movie ->
            dbHelper.deleteMovie(movie.id)
            loadMovies()
        }
    }
}
