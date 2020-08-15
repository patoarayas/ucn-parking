package cl.ucn.disc.pdis.parking.compose

import androidx.compose.Model

/**
 * Sealed class.
 * Shows the initial views.
 * https://devexperto.com/sealed-classes-kotlin/
 */
sealed class Navigator {
    object LoadView : Navigator()
    object MainView : Navigator()
}

/**
 * Sealed class.
 * Shows the view to interact with.
 * https://devexperto.com/sealed-classes-kotlin/
 */
sealed class HomeNavigator {
    object AddView : HomeNavigator()
    object VehiclesView : HomeNavigator()
    object AccessView : HomeNavigator()
}

/**
 * Moves from one view to another.
 */
@Model
object AppNavigation {
    var currentView: Navigator = Navigator.LoadView
}
fun navigateTo(destination: Navigator) {
    AppNavigation.currentView = destination
}

/**
 * Moves from one view to another.
 */
@Model
object HomeNavigation {
    var currentView: HomeNavigator = HomeNavigator.VehiclesView
}
fun navigateTo(destination: HomeNavigator) {
    HomeNavigation.currentView = destination
}
