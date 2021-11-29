package com.example.movienight

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movienight.databinding.MovieItemBinding
import com.example.movienight.network.ResultsItem

class MovieAdapter:ListAdapter<ResultsItem,MovieAdapter.MovieInfoViewHolder>(DiffCallback) {

//   class TaskAdapter(var list: MutableList<DataTask>)
//    : RecyclerView.Adapter<TaskAdapter.itemViewHolder>() {
//
//
////    val card: CardView =view.findViewById(R.id.card)
//   }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieInfoViewHolder {
     return MovieInfoViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieInfoViewHolder, position: Int) {
        val resultsItems = getItem(position)
        holder.bind(resultsItems)
       holder.card.setOnClickListener {
           Log.e("TAG","id view :${position}")
           val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(
               position)
           holder.itemView.findNavController().navigate(action)

       }
    }

    class MovieInfoViewHolder(private var binding:
                              MovieItemBinding
    ): RecyclerView.ViewHolder(binding.root){

val card:CardView = binding.cerdView

    fun bind(resultsItems:ResultsItem) {
        binding.movieInformtion = resultsItems
        binding.executePendingBindings()
    }
    }
companion object DiffCallback : DiffUtil.ItemCallback<ResultsItem>(){
    override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
        return oldItem.posterPath == newItem.posterPath
    }

}
}