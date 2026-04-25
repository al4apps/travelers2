package com.al4apps.travelers2.core.dialog

data class DialogContentState(
    val background: DialogBackground,
    val align: DialogAlign,
    val isDismissAllow: Boolean = true,
)
