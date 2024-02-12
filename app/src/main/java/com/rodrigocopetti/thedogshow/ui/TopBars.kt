package com.rodrigocopetti.thedogshow.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigocopetti.thedogshow.R
import com.rodrigocopetti.thedogshow.ui.theme.TheDogShowTheme

/**
 * Re-usable Jetpack Compose top bars
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListTopAppBar() {
    TopAppBar(
        title = {
            Text(
                stringResource(R.string.available_dog_breeds),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
    )
}

@Preview(showBackground = true)
@Composable
fun ListTopAppBarPreview() {
    TheDogShowTheme(dynamicColor = false) {
        ListTopAppBar()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopAppBar(breed: String, onClick: () -> Unit, scrollBehavior: TopAppBarScrollBehavior) {
    MediumTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                "The ${breed.replaceFirstChar(Char::titlecase)} breed",
                maxLines = 1,
                overflow = TextOverflow.Visible
            )
        },
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Go back"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ListDetailAppBarPreview() {
    val breed = "hound"
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    TheDogShowTheme(dynamicColor = false) {
        DetailTopAppBar(breed = breed, onClick = {}, scrollBehavior = scrollBehavior)
    }
}