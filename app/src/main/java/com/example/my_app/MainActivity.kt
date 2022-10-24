package com.example.my_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.my_app.data.MovieData
import com.example.my_app.data.movies
import com.example.my_app.ui.theme.My_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            My_AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp {
                        startActivity(MovieDetailsPage.newIntent(this, it))
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp(navigateToProfile: (MovieData) -> Unit){
    Scaffold (
        backgroundColor = Color.Black,
        topBar = {
            MovieTopAppBar()
        }
            ) {
        MovieList(navigateToProfile = navigateToProfile)
    }
}

@Composable
fun MovieList(navigateToProfile: (MovieData) -> Unit){
    LazyColumn(
        modifier = Modifier
            .padding(top = 10.dp)
    ){
        items(
            items = movies,
            itemContent = {
                MovieListItem(movie = it, navigateToProfile = navigateToProfile)
            }
        )
        }
    }

@Composable
fun MovieListItem(movie: MovieData, navigateToProfile: (MovieData) -> Unit){
    Card(
        modifier = Modifier
            .padding(20.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(10),
    ){
        Row (
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navigateToProfile(movie) }
                ){
            Image(
                painter = painterResource(movie.imageResourceId),
                contentDescription = stringResource(movie.nameId),
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10))
                    .size(100.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(15.dp)
            ) {
                Text(
                    text = stringResource(movie.nameId),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Text(
                    text = stringResource(movie.dateId),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.LightGray
                )

            }
        }
    }
}

@Composable
fun MovieTopAppBar(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.surface),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp),
            painter = painterResource(R.drawable.ic_baseline_movie_24),
            tint = Color.White,
            contentDescription = null
        )
        Text(
            text = "Movies",
            style = MaterialTheme.typography.h1,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    My_AppTheme {
        MovieTopAppBar()
    }
}