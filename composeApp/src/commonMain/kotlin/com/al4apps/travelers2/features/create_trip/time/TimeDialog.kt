package com.al4apps.travelers2.features.create_trip.time

import com.al4apps.travelers2.features.create_trip.model.TripTime
import kotlinx.serialization.Serializable

@Serializable
sealed interface TimeDialogConfig {
    @Serializable
    data class TimePicker(
        val initialHour: Int,
        val initialMinute: Int,
    ) : TimeDialogConfig
}

sealed interface TimeDialogChild {
    data class TimePicker(
        val initial: TripTime,
        val onConfirm: (TripTime) -> Unit,
        val onDismiss: () -> Unit,
    ) : TimeDialogChild
}
