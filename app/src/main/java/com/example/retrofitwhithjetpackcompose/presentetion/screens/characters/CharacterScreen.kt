package com.example.retrofitwhithjetpackcompose.presentetion.screens.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.retrofitwhithjetpackcompose.R
import com.example.retrofitwhithjetpackcompose.presentetion.activity.CharacterViewModel
import com.example.retrofitwhithjetpackcompose.ui.theme.DarkGray
import com.example.retrofitwhithjetpackcompose.ui.theme.Gray
import com.example.retrofitwhithjetpackcompose.ui.theme.LightGray

@Composable
fun MangaList(viewModel: CharacterViewModel) {
    val stateData = viewModel.characterData.collectAsState()

    Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "Rick and Morty persons: ", color = Color.White)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)) {
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(
                        16.dp,
                        Alignment.CenterHorizontally
                    )
                ) {

                    items(stateData.value) { characterModel ->
                        FirstCharacterCard(
                            imageVector = characterModel.image,
                            mangaTitle = characterModel.name,
                            status = characterModel.status,
                            species = characterModel.species
                        )
                    }
                }
            }
            items(stateData.value) { characterModel ->
                SecondCharacterCard(
                    imageVector = characterModel.image,
                    personName = characterModel.name,
                    status = characterModel.status,
                    species = characterModel.species,
                    location = characterModel.location.name,
                    orign = characterModel.origin.name
                )

            }
        }
    }
}

@Composable
fun ProgressBar(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier.size(100.dp).padding(100.dp),
        strokeWidth = 10.dp,
        color = Color.White
    )
}

@Composable
fun FirstCharacterCard(imageVector: String, mangaTitle: String, status: String, species: String) {
    val isLoading = remember { mutableStateOf(true) }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10))
    ) {

        AsyncImage(
            modifier = Modifier
                .size(200.dp),
            model = ImageRequest.Builder(LocalContext.current).data(imageVector).build(),
            placeholder = painterResource(id = R.drawable.img),
            contentDescription = "Manga poster",
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomStart)
        ) {
            Text(
                text = mangaTitle,
                fontSize = 16.sp,
                color = DarkGray,
            )
            Text(
                text = stringResource(id = R.string.species_and_status, status, species),
                color = Gray
            )
        }
    }
}

@Composable
fun SecondCharacterCard(
    modifier: Modifier = Modifier,
    imageVector: String,
    personName: String,
    status: String,
    species: String,
    location: String,
    orign: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Gray)
    ) {
        AsyncImage(
            modifier = Modifier.size(120.dp),
            model = ImageRequest.Builder(LocalContext.current).data(imageVector).build(),
            contentDescription = "Image with $personName",
            placeholder = painterResource(id = R.drawable.img),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(start = 20.dp)
                .align(Alignment.CenterVertically),
        ) {
            Text(
                text = personName,
                fontSize = 14.sp,
                color = Color.White
            )
            Text(
                text = stringResource(id = R.string.species_and_status, status, species),
                fontSize = 10.sp,
                color = Color.White
            )
            Text(text = "Last know location:", fontSize = 10.sp, color = LightGray)
            Text(text = location, fontSize = 14.sp, color = Color.White)
            Text(text = "First seen in: ", fontSize = 10.sp, color = LightGray)
            Text(text = orign, fontSize = 14.sp, color = Color.White)


        }
    }
}

@Composable
@Preview
fun PreviewScannerManga() {
    MangaList(viewModel = CharacterViewModel())
}