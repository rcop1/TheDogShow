package com.rodrigocopetti.thedogshow.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rodrigocopetti.thedogshow.ui.DetailTopAppBar
import com.rodrigocopetti.thedogshow.ui.DogImageCard
import com.rodrigocopetti.thedogshow.ui.theme.TheDogShowTheme

/**
 * Breed details Screen.
 *
 * This screen will:
 *  - Show a list of 10 images of a particular dog breed.
 *  - Allow the user to go back to the list screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedDetailsScreen(
    navController: NavController,
    breedName: String,
    breedImages: Array<String>
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    /**
     * UI Implementation
     */
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DetailTopAppBar(
                breed = breedName,
                onClick = { navController.popBackStack() },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(top = 10.dp, start = 5.dp, end = 5.dp)
        ) {
            items(breedImages) { breedImage ->
                DogImageCard(breedImage = breedImage)
            }
        }
    }
}

/**
 * Preview
 */

@Preview(showBackground = true)
@Composable
fun BreedDetailsPreview() {
    val breed = "hound"
    val breedImages = emptyArray<String>()

    TheDogShowTheme(dynamicColor = false) {
        BreedDetailsScreen(rememberNavController(), breed, breedImages)
    }
}