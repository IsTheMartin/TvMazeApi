package com.mrtnmrls.tvmazeapi.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mrtnmrls.tvmazeapi.R
import com.mrtnmrls.tvmazeapi.domain.model.TvShow
import com.mrtnmrls.tvmazeapi.ui.theme.FoamWhite
import com.mrtnmrls.tvmazeapi.ui.theme.Typography

@Composable
internal fun TvShowDetailsView(modifier: Modifier = Modifier, tvShow: TvShow) {
    LazyColumn(
        modifier
            .fillMaxSize()
            .background(FoamWhite)
    ) {
        item {
            Spacer(modifier = Modifier.height(12.dp))
            PortraitImage(
                showName = tvShow.name,
                mediumImageUrl = tvShow.mediumImageUrl.orEmpty(),
                originalImageUrl = tvShow.originalImageUrl.orEmpty()
            )
        }
        item {
            Text(tvShow.name, style = Typography.titleLarge)
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(tvShow.type)
                Spacer(modifier = Modifier.width(12.dp))
                Text("${tvShow.runtime} mins")
                Spacer(modifier = Modifier.width(12.dp))
                Text(tvShow.language.orEmpty())
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
            val spannedSummary = HtmlCompat.fromHtml(tvShow.summary, HtmlCompat.FROM_HTML_MODE_COMPACT)
            Text(spannedSummary.toString())
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                if (!tvShow.imdb.isNullOrEmpty()) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Info, contentDescription = "Imdb")
                    }
                }
                if (!tvShow.officialSite.isNullOrEmpty()) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Star, contentDescription = "OfficialSite")
                    }
                }
            }
        }
    }
}

@Composable
internal fun PortraitImage(showName: String, mediumImageUrl: String, originalImageUrl: String) {
    val url = originalImageUrl.ifEmpty { mediumImageUrl }
    if (url.isNotEmpty()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_background),
            contentDescription = showName,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
    }
}