package com.example.movienight

import android.util.Log
import androidx.lifecycle.*
import com.example.movienight.network.MovieApi
import com.example.movienight.network.ResultsItem
import kotlinx.coroutines.launch
import java.nio.file.Files.find
enum class MovieApiStatus {LOADING, ERROR, DONE}

class MovieViewModel:ViewModel() {
    private val _status = MutableLiveData<MovieApiStatus>()
    val status:LiveData<MovieApiStatus> = _status

    private val _movieInfo = MutableLiveData<List<ResultsItem?>>()
    val moveInfo:LiveData<List<ResultsItem?>> = _movieInfo
    val _movieName = MutableLiveData<String>()
    val moviePoster = MutableLiveData<String>()
    val overView = MutableLiveData<String>()
    val rateing = MediatorLiveData<Double>()
    init {
        getMovieInfo()

    }



    private fun getMovieInfo(){
        viewModelScope.launch {
            _status.value = MovieApiStatus.LOADING
            try {
                _movieInfo.value = MovieApi.retrofitServer.getPhoto().results
                _status.value = MovieApiStatus.DONE
            }catch (e:Exception){
                _status.value=MovieApiStatus.ERROR
                _movieInfo.value = listOf()
            }
        }
    }
 fun moviInfoData(position:Int){
     val item= _movieInfo.value?.get(position)
     Log.e("TAG","idMovie:${item?.id}")
     Log.e("TAG","id view:${position}")
     _movieName.value = item?.originalTitle
     moviePoster.value=item?.posterPath
     overView.value = item?.overview
     rateing.value = item?.voteAverage

     Log.e("TAG","movie picture:${moviePoster}")


 }

//
//    fun moviInfoData(position:Int){
//        var item= _movieInfo.value?.find { it?.id.toString().equals(position.toString()) }
//        Log.e("TAG","idMovie:${item?.id}")
//        Log.e("TAG","id view:${position}")
//        movieName.value = item?.originalTitle
//        Log.e("TAG","movie view:${movieName.value}")
//
//    }
}