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

@Composable
fun MainView(scaffoldState: ScaffoldState = remember { ScaffoldState() }) {

    Scaffold(
        scaffoldState = scaffoldState,
        topAppBar = { MainTopBar() },
        bodyContent = { MainLayout(scaffoldState = scaffoldState) }
    )
}

@Composable
fun MainTopBar() {

    val clickedState: MutableState<Int> = state { 1 }

    Column {
        TopAppBar(
            backgroundColor = lightGray(),
            elevation = 0.dp,
            title = { Text("Vehicles Access", color = Color.White, style = MaterialTheme.typography.h5) }
        )
        TabRow(
            items = listOf("Add Vehicle", "Vehicles", "Access"),
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
