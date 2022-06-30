package com.easylife.quotify.ui.screens.settings

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easylife.quotify.R
import com.easylife.quotify.ui.theme.Red

enum class Setting(
    @StringRes val settingName: Int,
    @DrawableRes val icon: Int,
) {
    NOTIFICATION(R.string.settings_notification, R.drawable.ic_notification),
    FAVORITES(R.string.settings_favorites, R.drawable.ic_fav),
    SEEN(R.string.settings_seen, R.drawable.ic_seen)
}

@Composable
fun SettingList(
    map: Map<Int?, List<Setting>>,
    onClicked: (Setting) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        for (title in map.keys) {
            title?.let {
                Text(text = stringResource(id = title), style = MaterialTheme.typography.labelSmall)
                Spacer(modifier = Modifier.height(16.dp))
            }
            LazyColumn(userScrollEnabled = false) {
                itemsIndexed(map[title]?.toList() ?: emptyList()) { _, item ->
                    TextButton(
                        onClick = { onClicked(item) },
                        shape = MaterialTheme.shapes.medium,
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = "",
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = stringResource(id = item.settingName),
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
