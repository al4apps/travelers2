package com.al4apps.travelers2.features.create_trip.time.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.core.ui.getString
import com.al4apps.travelers2.design_system.TimeWheelPicker
import com.al4apps.travelers2.design_system.button.PrimaryButton
import com.al4apps.travelers2.features.create_trip.model.TripTime
import com.al4apps.travelers2.features.create_trip.time.TimeDialogChild
import com.al4apps.travelers2.shared_model.ButtonUiState
import com.al4apps.travelers2.theme.AppTheme
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.create_trip_time_picker_confirm
import travelers2.composeapp.generated.resources.create_trip_time_picker_title

@Composable
fun TimePickerDialog(child: TimeDialogChild.TimePicker) {
    var hour by remember { mutableStateOf(child.initial.hour) }
    var minute by remember { mutableStateOf(child.initial.minute) }

    Dialog(onDismissRequest = child.onDismiss) {
        ElevatedCard(
            modifier = Modifier.widthIn(min = 280.dp, max = 360.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = AppTheme.colors.background.default,
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = UiText.Resource(Res.string.create_trip_time_picker_title).getString(),
                    style = AppTheme.typography.h2,
                    color = AppTheme.colors.text.default,
                )
                TimeWheelPicker(
                    initialHour = child.initial.hour,
                    initialMinute = child.initial.minute,
                    onValueChange = { newHour, newMinute ->
                        hour = newHour
                        minute = newMinute
                    },
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    PrimaryButton(
                        uiState = ButtonUiState(
                            text = UiText.Resource(Res.string.create_trip_time_picker_confirm),
                            enabled = true,
                        ),
                        onClick = { child.onConfirm(TripTime(hour = hour, minute = minute)) },
                    )
                }
            }
        }
    }
}
