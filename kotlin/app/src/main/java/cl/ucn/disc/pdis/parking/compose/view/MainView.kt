package cl.ucn.disc.pdis.parking.compose.view

import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.remember
import androidx.compose.state
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.material.*
import androidx.ui.unit.dp
import cl.ucn.disc.pdis.parking.compose.HomeNavigator
import cl.ucn.disc.pdis.parking.compose.layout.MainLayout
import cl.ucn.disc.pdis.parking.compose.navigateTo
import cl.ucn.disc.pdis.parking.values.green
import cl.ucn.disc.pdis.parking.values.lightGray

/**
 * Main View.
 */
@Composable
fun MainView(scaffoldState: ScaffoldState = remember { ScaffoldState() }) {

    // Implements basic material design (visual layout structure)
    Scaffold(
        scaffoldState = scaffoldState,
        topAppBar = { MainToolbar() },
        bodyContent = { MainLayout(scaffoldState = scaffoldState) }
    )
}

/**
 * Toolbar setup.
 */
@Composable
fun MainToolbar() {

    // Tabs that's can be selected in the main view.
    val tabs = listOf("Add Vehicle", "Vehicles", "Access")

    // Beginning state (starts at 'Access' tab)
    val clickedState: MutableState<Int> = state { 1 }

    // Setup
    Column {
        TopAppBar(
            backgroundColor = lightGray(),
            elevation = 0.dp,
            title = { Text("UCN Access", color = Color.White, style = MaterialTheme.typography.h5) }
        )
        TabRow(
            items = tabs,
            backgroundColor = lightGray(),
            selectedIndex = clickedState.value,
            indicatorContainer = {
                TabRow.IndicatorContainer(tabPositions = it, selectedIndex = clickedState.value) {
                    Divider(thickness = 2.dp, color = green())
                }
            }
        ) { index, text ->
            Tab(text = { Text(text, style = MaterialTheme.typography.h6) },
                activeColor = green(), inactiveColor = Color.Gray,
                selected = clickedState.value == index,
                onSelected = { clickedState.value = index
                    when (index) {
                        0 -> navigateTo(HomeNavigator.AddView)
                        1 -> navigateTo(HomeNavigator.VehiclesView)
                        2 -> navigateTo(HomeNavigator.AccessView)
                    }
                }
            )
        }
    }
}
