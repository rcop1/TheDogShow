package com.rodrigocopetti.thedogshow.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rodrigocopetti.thedogshow.data.network.OperationStatus
import com.rodrigocopetti.thedogshow.navigation.toNavArgs
import com.rodrigocopetti.thedogshow.screens.viewmodels.BreedViewModel
import com.rodrigocopetti.thedogshow.ui.DogBreedCard
import com.rodrigocopetti.thedogshow.ui.ListTopAppBar
import com.rodrigocopetti.thedogshow.ui.theme.TheDogShowTheme

/**
 * Breed list Screen.
 *
 * This screen will:
 *  - Show a list of breeds.
 *  - Allow the user to select a breed and present it on a second second screen.
 *  - Show a progress indicator while there's an ongoing internet request
 */
@Composable
fun BreedListScreen(
    navController: NavController,
    breedViewModel: BreedViewModel = viewModel()
) {
    /**
     * Observables
     */
    val breeds by breedViewModel.breedList.collectAsState()
    val breedImages by breedViewModel.breedImages.collectAsState()

    val breedListStatus by breedViewModel.breedListStatus.collectAsState()
    val breedImagesStatus by breedViewModel.breedImagesStatus.collectAsState()

    /**
     * UI Implementation
     */
    Scaffold(
        topBar = {
            ListTopAppBar()
        },
        content = { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(top = 10.dp, start = 5.dp, end = 5.dp)
            ) {
                items(breeds) { breed ->
                    DogBreedCard(
                        breed = breed,
                        onClick = {
                            breedViewModel.getListOfBreedImages(breed)
                        }
                    )
                }
            }
        }
    )

    // Only show the progress indicator if the internet request is in progress
    if (breedListStatus.status == OperationStatus.IN_PROGRESS ||
        breedImagesStatus.status == OperationStatus.IN_PROGRESS) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }

    // Once we've downloaded the breed images, navigate to the details screen
    if (breedImages != null){
        LaunchedEffect(Unit){
            navController.navigate("BreedDetails/${breedImages!!.breed}?${breedImages!!.images.toNavArgs("breedImages")}")
            breedViewModel.clearListOfBreedImages()
        }
    }

    // This is executed when the screen is shown
    LaunchedEffect(Unit){
        breedViewModel.clearListOfBreedImages()
        breedViewModel.getListOfBreeds()
    }
}

/**
 * Preview
 */

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    TheDogShowTheme(dynamicColor = false) {
        BreedListScreen(rememberNavController())
    }
}