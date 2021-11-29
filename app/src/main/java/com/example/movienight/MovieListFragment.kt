package com.example.movienight

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.movienight.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment() {
   // private var binding:FragmentMovieListBinding? = null
    private val viewModel:MovieViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     val  binding = FragmentMovieListBinding.inflate(inflater)
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel
        binding?.MovieListRecycle?.adapter = MovieAdapter()
        return binding?.root

    }


}