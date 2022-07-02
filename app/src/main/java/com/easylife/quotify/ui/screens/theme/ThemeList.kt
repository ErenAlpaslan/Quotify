package com.easylife.quotify.ui.screens.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easylife.quotify.R
import com.easylife.quotify.common.enums.ThemeTypes
import com.easylife.quotify.data.models.Theme

@Composable
fun ThemeList(
    list: List<Theme> = emptyList(),
    onClicked: (Theme) -> Unit
) {
    LazyVerticalGrid(columns = GridCells.Fixed(4)) {
        itemsIndexed(items = list, key = { _, item -> item.themeId }) { _, item ->
            ThemeListItem(item) {
                onClicked(item)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ThemeListItem(
    theme: Theme,
    onClicked: () -> Unit,
) {
    Box(modifier = Modifier
        .padding(5.dp)
        .height(150.dp)) {
        Card(
            onClick = {
                onClicked()
            },
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
                Text(text = stringResource(id = R.string.themes_default_text_content), style = MaterialTheme.typography.bodySmall)
            }
        }
        if (theme.type == ThemeTypes.PREMUIM.type) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_premium),
                    contentDescription = "Premium icon",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

