package com.rodrigocopetti.thedogshow.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rodrigocopetti.thedogshow.ui.theme.TheDogShowTheme

/**
 * Re-usable Jetpack Compose cards
 */

@Composable
fun DogBreedCard(breed: String, onClick: () -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Box(Modifier.fillMaxWidth()) {
            Text(
                text = breed.replaceFirstChar(Char::titlecase),
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
            Icon(
                imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowRight,
                contentDescription = "See photos",
                Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun DogBreedCardPreview() {
    val breed = "hound"
    TheDogShowTheme(dynamicColor = false) {
        DogBreedCard(breed = breed, onClick = {})
    }
}

@Composable
fun DogImageCard(breedImage: String, placeholder: Painter? = null) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            model = breedImage,
            placeholder = placeholder,
            contentDescription = null
        )
    }
}

@Preview(showBackground = false)
@Composable
fun DogImageCardPreview() {
    val placeholder = BrushPainter(
        Brush.linearGradient(
            listOf(
                Color(color = 0xFFAAAFFF),
                Color(color = 0xFFDDDDDF),
            )
        )
    )
    TheDogShowTheme(dynamicColor = false) {
        DogImageCard(breedImage = "", placeholder = placeholder)
    }
}