package com.al4apps.travelers2.design_system

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.theme.AppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SettingToggleRow(
    iconRes: DrawableResource,
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = AppTheme.colors.icon.grayDark,
            modifier = Modifier.size(22.dp),
        )
        Text(
            text = title,
            style = AppTheme.typography.body1,
            color = AppTheme.colors.text.default,
            modifier = Modifier.weight(1f),
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = AppTheme.colors.button.primaryTextColor,
                checkedTrackColor = AppTheme.colors.button.primary,
                uncheckedThumbColor = AppTheme.colors.button.primaryTextColor,
                uncheckedTrackColor = AppTheme.colors.border.divider,
                uncheckedBorderColor = AppTheme.colors.border.divider,
            ),
        )
    }
}
