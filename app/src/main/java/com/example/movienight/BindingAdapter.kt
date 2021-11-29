package com.example.movienight

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.movienight.network.ResultsItem

@BindingAdapter("imagUrl")
fun bindImage(imageView: ImageView,imgUrl:String?){
    imgUrl.let {
        val imgUri = imgUrl?.toUri()?.buildUpon()?.build()
        Glide.with(imageView)
            .load("https://image.tmdb.org/t/p/w500${imgUri}")
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(imageView)
    }
}
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,data:List<ResultsItem>?){
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}
@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: MovieApiStatus?){
    when (status){
        MovieApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MovieApiStatus.DONE ->{
            statusImageView.visibility = View.GONE


        }
        MovieApiStatus.ERROR ->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.wifi_dawn)
        }
    }

}