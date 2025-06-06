package com.example.catalogofilme

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(
    private val movieList: MutableList<Movie>,
    private val onDeleteClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.textTitle)
        val desc: TextView = view.findViewById(R.id.textDescription)
        val year: TextView = view.findViewById(R.id.textYear)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete) // novo botão
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movieList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.title.text = movie.title
        holder.desc.text = movie.description
        holder.year.text = movie.year.toString()
        holder.btnDelete.setOnClickListener {
            onDeleteClick(movie)
        }
    }

    fun removeMovie(movie: Movie) {
        val position = movieList.indexOf(movie)
        if (position != -1) {
            movieList.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}
