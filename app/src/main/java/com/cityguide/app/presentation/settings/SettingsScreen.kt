package com.cityguide.app.presentation.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SettingsScreen(
    onLogoutSuccess: () -> Unit
) {

    val viewModel: SettingsViewModel = viewModel()

    SettingsContent(

        onClearCache = {
            viewModel.clearCache()
        },

        onLogout = {
            viewModel.logout()
            onLogoutSuccess()
        }

    )
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {

    SettingsContent(
        onClearCache = {},
        onLogout = {}
    )

}