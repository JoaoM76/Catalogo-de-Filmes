package com.example.catalogofilme

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context) :
    SQLiteOpenHelper(context, "movies.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE movies (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                description TEXT,
                year INTEGER
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS movies")
        onCreate(db)
    }

    fun addMovie(movie: Movie): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("title", movie.title)
            put("description", movie.description)
            put("year", movie.year)
        }
        return db.insert("movies", null, values)
    }

    fun deleteMovie(id: Int): Boolean {
        val db = this.writableDatabase
        return db.delete("movies", "id = ?", arrayOf(id.toString())) > 0
    }

    fun getAllMovies(): List<Movie> {
        val movies = mutableListOf<Movie>()
        val cursor = readableDatabase.rawQuery("SELECT * FROM movies", null)
        if (cursor.moveToFirst()) {
            do {
                val movie = Movie(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    title = cursor.getString(cursor.getColumnIndexOrThrow("title")),
                    description = cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    year = cursor.getInt(cursor.getColumnIndexOrThrow("year"))
                )
                movies.add(movie)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return movies
    }
}
