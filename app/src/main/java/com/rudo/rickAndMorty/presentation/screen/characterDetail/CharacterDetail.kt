package com.rudo.rickAndMorty.presentation.screen.characterDetail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.rudo.rickAndMorty.R
import com.rudo.rickAndMorty.domain.entity.CharacterDetail
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Arrangement

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel = hiltViewModel(),
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val darkBg = Color(0xFF2B2B2B)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Character detail",
                        color = Color.White,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = darkBg,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.fillMaxWidth()
            ) { data ->
                Snackbar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(78.dp)
                        .padding(horizontal = 16.dp),
                    containerColor = Color(0xFFFFF59D),
                    contentColor = Color.Black
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = data.visuals.message,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black
                        )
                        TextButton(onClick = { data.dismiss() }) {
                            Text("✕", color = Color.Black)
                        }
                    }
                }
            }
        },
        containerColor = darkBg
    ) {
        uiState.character?.let {
            CharacterDetailContent(
                isFavorite = uiState.isFavorite,
                onToggle = { wasFavorite ->
                    scope.launch {
                        if (wasFavorite) {
                            snackbarHostState.showSnackbar(message = "The character has been removed from favourites.")
                        }
                    }
                    viewModel.toggleFavorite()
                },
                detail = it
            )
        }
    }
}

@Composable
private fun CharacterDetailContent(
    isFavorite: Boolean,
    onToggle: (Boolean) -> Unit,
    detail: CharacterDetail
) {
    val darkBg = Color(0xFF2B2B2B)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBg)
            .padding(horizontal = 16.dp)
    ) {
        item { Spacer(Modifier.height(12.dp)) }

        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = detail.image,
                    contentDescription = detail.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(258.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = { onToggle(isFavorite) },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(y = 12.dp)
                        .padding(end = 8.dp)
                        .size(42.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF66BB6A))
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = Color.Black
                    )
                }
            }
        }

        item { Spacer(Modifier.height(16.dp)) }

        // Title
        item {
            Text(
                text = detail.name.uppercase(),
                color = Color.White,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp
            )
        }

        item { Spacer(Modifier.height(12.dp)) }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    LabeledValue(label = "Specie:", value = detail.species.ifEmpty { "Unknow" })
                    Spacer(Modifier.height(8.dp))

                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "From:", color = Color(0xFFBDBDBD))
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = detail.origin.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                            color = Color(0xFFBBDEFB),
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.weight(1f).clickable { /* TODO: navigate origin */ },
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(Modifier.height(8.dp))

                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "First time seen:", color = Color(0xFFBDBDBD))
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = detail.origin.name,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.White,
                            maxLines = 1,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(Modifier.height(8.dp))

                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Last time seen:", color = Color(0xFFBDBDBD))
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = detail.location.name.ifEmpty { "Unknow" },
                            overflow = TextOverflow.Ellipsis,
                            color = Color.White,
                            maxLines = 1,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Spacer(Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    LabeledValue(label = "Type:", value = detail.type.ifEmpty { "-" })
                    Spacer(Modifier.height(8.dp))

                    val statusColor = when (detail.status) {
                        "Alive" -> Color(0xFF66BB6A)
                        "Dead" -> Color(0xFFE57373)
                        else -> Color(0xFFBDBDBD)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Status:",
                            color = Color(0xFFBDBDBD)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = detail.status,
                            overflow = TextOverflow.Ellipsis,
                            color = statusColor,
                            maxLines = 1,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
        item { Spacer(Modifier.height(12.dp)) }

        item {
            Text(
                text = "List of episodes:",
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }

        item { Spacer(Modifier.height(8.dp)) }

        items(detail.episode) { url ->
            val id = url.substringAfterLast("/").toIntOrNull() ?: 0
            val label = "S01E$id"
            Text(
                text = "$label - $url",
                color = Color(0xFFFFEB3B),
                textDecoration = TextDecoration.Underline,
                fontFamily = FontFamily(Font(R.font.robotobold)),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .clickable {

                    }

            )
        }

        item { Spacer(Modifier.height(24.dp)) }
    }
}

@Composable
private fun LabeledValue(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, color = Color(0xFFBDBDBD))
        Spacer(Modifier.width(6.dp))
        Text(
            text = value,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
    }
}