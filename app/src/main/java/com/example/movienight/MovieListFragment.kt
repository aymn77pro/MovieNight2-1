package com.example.movienight

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.movienight.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment() {
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
         setHasOptionsMenu(true)
        return binding?.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.movie_mune,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.SHOW_ALL -> viewModel.getMovieInfo()
            R.id.Show_sortByRelesDate -> viewModel.sortMovieByReleaseDate("release_date.lte")
            R.id.show_action_menu -> viewModel.getMoviewithGener(MovieGenre.ACTION)
            R.id.show_adventure_menu -> viewModel.getMoviewithGener(MovieGenre.ADVENTUR)
            else -> viewModel.getMoviewithGener(MovieGenre.ANIMATIONE)
        }

//        viewModel.updataList(
//            when (item.itemId) {
//                R.id.show_action_menu -> MovieGenre.ACTION
//                R.id.show_adventure_menu -> MovieGenre.DRAMA
//                else ->  MovieGenre.ANIMATIONE
//            }
//        )
        return true
    }


}