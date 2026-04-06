package com.al4apps.travelers2.shared_model

data class EntryDialogUiState(
    val isVisible: Boolean,
    // Name / contact
    val nameText: String,
    val isNameValid: Boolean,
    val selectedContactId: String?,
    val selectedContactName: String?,
    // Amount
    val amountText: String,
    val isAmountValid: Boolean,
    val currencySymbol: String,
    // Type
    val isMinus: Boolean,
    // Date
    val dateText: String,
    val isDatePickerVisible: Boolean,
    // Comment
    val commentText: String,
    // Buttons
    val isSaveEnabled: Boolean,
    val isCancelEnabled: Boolean
) {
    companion object {
        val EMPTY = EntryDialogUiState(
            isVisible = false,
            nameText = "",
            isNameValid = false,
            selectedContactId = null,
            selectedContactName = null,
            amountText = "",
            isAmountValid = false,
            currencySymbol = "",
            isMinus = false,
            dateText = "",
            isDatePickerVisible = false,
            commentText = "",
            isSaveEnabled = false,
            isCancelEnabled = true
        )
    }
}
