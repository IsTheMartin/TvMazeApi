@file:OptIn(ExperimentalMaterial3Api::class)

package com.mrtnmrls.tvmazeapi.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mrtnmrls.tvmazeapi.R
import com.mrtnmrls.tvmazeapi.domain.model.CurrentTvSchedule
import com.mrtnmrls.tvmazeapi.ui.actions.ScheduleListAction
import com.mrtnmrls.tvmazeapi.ui.theme.DarkBrown
import com.mrtnmrls.tvmazeapi.ui.theme.DeepBlue
import com.mrtnmrls.tvmazeapi.ui.theme.SandBeige
import com.mrtnmrls.tvmazeapi.ui.theme.TvMazeApiTheme
import com.mrtnmrls.tvmazeapi.ui.theme.WaveWhite
import java.util.Locale

@Composable
internal fun CurrentTvScheduleView(
    scheduleList: List<CurrentTvSchedule>,
    onAction: (ScheduleListAction) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(scheduleList) { scheduleItem ->
            Column {
                TvScheduleItemView(scheduleItem = scheduleItem) {
                    onAction(ScheduleListAction.OnTvShowClicked(scheduleItem.showId))
                }
            }
        }
    }
}

@Composable
internal fun TvScheduleItemView(
    modifier: Modifier = Modifier,
    scheduleItem: CurrentTvSchedule,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .background(WaveWhite),
        onClick = onClick
    ) {
        Row {
            ImageShow(
                showName = scheduleItem.showName,
                originalUrl = scheduleItem.originalImageUrl,
                mediumUrl = scheduleItem.mediumImageUrl
            )
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = scheduleItem.showName,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = DarkBrown
                )
                Text(text = scheduleItem.episodeName, color = DeepBlue)
                Text(text = scheduleItem.type)
                Row(
                    modifier = Modifier.padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LanguageView(language = scheduleItem.language)
                    Text(text = "${scheduleItem.airtime} - ${scheduleItem.scheduledTime}")
                }
            }
        }
    }
}

@Composable
internal fun LanguageView(language: String) {
    if (language.isNotEmpty()) {
        Column(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 2.dp)
                .background(SandBeige)
                .clip(RoundedCornerShape(10.dp))
        ) {
            Text(
                modifier = Modifier.padding(3.dp),
                text = language.uppercase(Locale.ROOT).substring(0..1),
                color = DarkBrown
            )
        }
    }
}

@Composable
internal fun ImageShow(showName: String, originalUrl: String, mediumUrl: String) {
    val url = mediumUrl.ifEmpty { originalUrl }
    if (url.isNotEmpty()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_background),
            contentDescription = showName,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.size(width = 110.dp, height = 150.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TvScheduleItemPreview() {
    TvMazeApiTheme {
        Surface {
            TvScheduleItemView(
                scheduleItem = CurrentTvSchedule(
                    showId = 1,
                    showName = "Morning on Merit Street",
                    status = "Running",
                    language = "English",
                    runtime = 120,
                    airtime = "08:00",
                    type = "News",
                    originalImageUrl = "",
                    mediumImageUrl = "",
                    episodeId = 1,
                    episodeName = "Episode 44"
                )
            ) {}
        }
    }
}