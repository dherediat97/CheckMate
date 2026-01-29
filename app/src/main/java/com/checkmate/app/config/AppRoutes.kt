package com.checkmate.app.config

enum class Screen{
    HOME,
    CHECK_TICKET,
}

sealed class NavigationItem(val route: String) {
    data object Home : NavigationItem(Screen.HOME.name)
    data object TicketScreen : NavigationItem(Screen.CHECK_TICKET.name)
}