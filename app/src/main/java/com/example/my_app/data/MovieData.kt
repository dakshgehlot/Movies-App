package com.example.my_app.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.my_app.R
import java.io.Serializable

data class MovieData (
    @DrawableRes val imageResourceId: Int,
    @StringRes val nameId: Int,
    @StringRes val descId: Int,
    @StringRes val dateId: Int
    ) : Serializable

val movies = listOf(
    MovieData(R.drawable.movie_1, R.string.movie_1_name, R.string.movie_1_desc, R.string.movie_1_date),
    MovieData(R.drawable.movie_2, R.string.movie_2_name, R.string.movie_2_desc, R.string.movie_2_date),
    MovieData(R.drawable.movie_3, R.string.movie_3_name, R.string.movie_3_desc, R.string.movie_3_date),
    MovieData(R.drawable.movie_4, R.string.movie_4_name, R.string.movie_4_desc, R.string.movie_4_date),
    MovieData(R.drawable.movie_5, R.string.movie_5_name, R.string.movie_5_desc, R.string.movie_5_date),
    MovieData(R.drawable.movie_6, R.string.movie_6_name, R.string.movie_6_desc, R.string.movie_6_date),
    MovieData(R.drawable.movie_7, R.string.movie_7_name, R.string.movie_7_desc, R.string.movie_7_date)
)