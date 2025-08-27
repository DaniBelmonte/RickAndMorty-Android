package com.rudo.rickAndMorty.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.rudo.rickAndMorty.domain.entity.Character
import coil.compose.AsyncImage
import com.rudo.rickAndMorty.R
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * MainScreen composable.
 * Represents the main screen of the application.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (id: Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    val collapse = scrollBehavior.state.collapsedFraction
                    val alpha = 1f - collapse
                    Text(
                        text = "Rick & Morty",
                        modifier = Modifier.alpha(alpha),
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.robotobold))
                    )
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Black,
                    scrolledContainerColor = Color.Black,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.Black)
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            stickyHeader {
                Surface(
                    shadowElevation = 1.dp,
                    color = Color.Black
                ) {
                    CharacterSearchBar(
                        query = uiState.query,
                        onQueryChange = { viewModel.getCharactersByName(it) }
                    )
                }
            }

            items(uiState.characters) { character ->
                CharacterItem(character, navigateToDetail, )
                if (uiState.characters[uiState.characters.size - 4] == character) {
                    viewModel.loadMoreCharacters()
                }
            }
        }
    }
}

@Composable
fun CharacterSearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = { Text("Search characters") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
    }
}

@Composable
fun CharacterItem(character: Character, navigateToDetail: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable{
                navigateToDetail(character.id)
            },

        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = character.name,
                    color = Color(0xFF2B3B62),
                    fontFamily = FontFamily(Font(R.font.robotobold)),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
                Row {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    color = when (character.status) {
                                        "Alive" -> Color.Green
                                        "Dead" -> Color.Red
                                        else -> Color.Unspecified
                                    }
                                )
                            ) {
                                append(character.status)
                            }
                            append(" - ${character.location.name}")
                        },
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                }
            }
            Icon(
                imageVector = if (character.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorite",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


@Preview()
@Composable
fun MainScreenPreview() {
    HomeScreen(
        navigateToDetail = {}
    )
}