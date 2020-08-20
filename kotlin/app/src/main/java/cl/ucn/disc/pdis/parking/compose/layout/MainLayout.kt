package cl.ucn.disc.pdis.parking.compose.layout

import androidx.compose.Composable
import androidx.ui.animation.Crossfade
import androidx.ui.material.Scaffold
import androidx.ui.material.ScaffoldState
import cl.ucn.disc.pdis.parking.compose.HomeNavigation
import cl.ucn.disc.pdis.parking.compose.HomeNavigator
import cl.ucn.disc.pdis.parking.compose.view.tab.AccessView
import cl.ucn.disc.pdis.parking.compose.view.tab.AddView
import cl.ucn.disc.pdis.parking.compose.view.tab.VehiclesView

/**
 * Main view to navigates.
 */
@Composable
fun MainLayout(scaffoldState: ScaffoldState) {

    // Implements basic material design (visual layout structure)
    Scaffold(
        scaffoldState = scaffoldState,
        bodyContent = {
            // Allows switching between layouts with a crossfade animation
            Crossfade(current = HomeNavigation.currentView) { view ->
                when (view) {
                    is HomeNavigator.AddView -> AddView()
                    is HomeNavigator.VehiclesView -> VehiclesView()
                    is HomeNavigator.AccessView -> AccessView().view()
                }
            }
        }
    )
}
