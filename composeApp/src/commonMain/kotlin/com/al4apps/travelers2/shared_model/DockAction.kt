package com.al4apps.travelers2.shared_model

import kotlinx.serialization.Serializable

@Serializable
data class DockAction(
    val id: Long,
    val title: String,
    val iconEnum: IconEnum?,
)

object DockActionIds {
    const val DELETE_ID = 1L
}
