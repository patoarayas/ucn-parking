package cl.ucn.disc.pdis.parking.compose.layout

import androidx.compose.Composable
import androidx.ui.animation.Crossfade
import androidx.ui.material.Scaffold
import androidx.ui.material.ScaffoldState
import cl.ucn.disc.pdis.parking.compose.HomeNavigation
import cl.ucn.disc.pdis.parking.compose.HomeNavigator
import cl.ucn.disc.pdis.parking.compose.view.main.AccessView
import cl.ucn.disc.pdis.parking.compose.view.main.AddView
import cl.ucn.disc.pdis.parking.compose.view.main.VehiclesView

@Composable
fun MainLayout(scaffoldState: ScaffoldState) {

    Scaffold(
        scaffoldState = scaffoldState,
        bodyContent = {
            Crossfade(current = HomeNavigation.currentView) { screen ->
                when (screen) {
                    is HomeNavigator.AddView -> AddView()
                    is HomeNavigator.AccessView -> AccessView()
                    is HomeNavigator.VehiclesView -> VehiclesView()
                }
            }
        }
    )
}
