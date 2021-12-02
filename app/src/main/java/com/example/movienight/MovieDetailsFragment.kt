package com.example.movienight


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.movienight.databinding.FragmentMovieDetailsBinding



class MovieDetailsFragment : Fragment() {

    private val viewModel:MovieViewModel by activityViewModels()
    var Movie = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            Movie=it!!.getInt("id")
            Log.e("TAG","id:${Movie}")
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  binding = FragmentMovieDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel2 = viewModel
        binding.share.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).putExtra(Intent.EXTRA_TEXT,"I'm watching ${viewModel._movieName.value} link : https://www.themoviedb.org/movie/${viewModel.movieId.value}")
                .setType("text/plain")
            if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
                startActivity(intent)
            }
        }
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("TAG","idMovie:${Movie}")
        viewModel.moviInfoData(Movie)


    }



}