package com.example.my_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.my_app.data.MovieData
import com.example.my_app.ui.theme.My_AppTheme

class MovieDetailsPage : ComponentActivity() {

    val movie: MovieData by lazy{
        intent?.getSerializableExtra(MOVIE_ID) as MovieData
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            My_AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DetailsPage(movie = movie)
                }
            }
        }
    }
    companion object {
        const val MOVIE_ID = "movie_id"
        fun newIntent(context: Context, movie: MovieData) =
            Intent(context, MovieDetailsPage::class.java).apply {
                putExtra(MOVIE_ID, movie)
            }

    }
}

@Composable
fun DetailsPage(movie: MovieData){

    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            ) {
        BoxWithConstraints {
            Surface {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    MovieHeader(movie = movie, containerHeight = this@BoxWithConstraints.maxHeight)
                    MovieDetails(label = "Release Date:", id = movie.dateId)
                    MovieDetails(label = "Description:", id = movie.descId)

                }
            }
        }
    }
}

@Composable
fun MovieHeader(movie: MovieData, containerHeight: Dp){

    Column (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .heightIn(max = containerHeight / 2)
                .fillMaxWidth(),
            painter = painterResource(movie.imageResourceId),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(movie.nameId),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 20.dp, start = 15.dp, bottom = 20.dp),
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
fun MovieDetails(label: String, @StringRes id: Int){
    Column (
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            ) {
        Divider()
        Text(
            text = label,
            modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp),
            style = MaterialTheme.typography.caption,
            color = Color.LightGray,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id),
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Justify

        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    My_AppTheme {
        //Greeting(name = movie.nameId)
    }
}