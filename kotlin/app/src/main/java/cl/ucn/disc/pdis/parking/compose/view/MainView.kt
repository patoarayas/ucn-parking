/*
 * MIT License
 *
 * Copyright (c) 2020 Patricio Araya, David Canto, Ariel Vejar
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package cl.ucn.disc.pdis.parking.compose.view

import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.remember
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.padding
import androidx.ui.material.*
import androidx.ui.unit.dp
import cl.ucn.disc.pdis.parking.compose.HomeNavigator
import cl.ucn.disc.pdis.parking.compose.layout.MainLayout
import cl.ucn.disc.pdis.parking.compose.navigateTo
import cl.ucn.disc.pdis.parking.values.green
import cl.ucn.disc.pdis.parking.values.lightGray

/**
 * Main view.
 */
class MainView {

    /**
     * Tabs that's can be selected in the main view.
     */
    private val tabs = listOf("Development", "Vehicles", "Access")

    /**
     * Main view setup.
     */
    @Composable
    fun view(scaffoldState: ScaffoldState = remember { ScaffoldState() }) {

        // Implements basic material design (visual layout structure)
        Scaffold(
            scaffoldState = scaffoldState,
            topAppBar = { mainToolbar() },
            bodyContent = { MainLayout().layout(scaffoldState = scaffoldState) }
        )
    }

    /**
     * Toolbar setup.
     */
    @Composable
    fun mainToolbar() {

        // Beginning state (starts at 'Access' tab)
        val clickedState: MutableState<Int> = state { 1 }

        // Setup
        Column {
            TopAppBar(
                backgroundColor = lightGray(),
                elevation = 0.dp,
                title = { Text("UCN Vehicles Access", color = Color.Gray, style = MaterialTheme.typography.h5) }
            )
            TabRow(
                items = tabs,
                backgroundColor = lightGray(),
                selectedIndex = clickedState.value,
                indicatorContainer = {
                    TabRow.IndicatorContainer(tabPositions = it, selectedIndex = clickedState.value) {
                        Divider(thickness = 2.dp, color = green()) }
                }
            ) { index, text ->
                Tab(text = { Text(text, Modifier.padding(10.dp) , style = MaterialTheme.typography.body1) },
                    activeColor = green(), inactiveColor = Color.Gray,
                    selected = clickedState.value == index,
                    onSelected = { clickedState.value = index
                        // Tab navigation index
                        when (index) {
                            0 -> navigateTo(HomeNavigator.DevelopmentView)
                            1 -> navigateTo(HomeNavigator.VehiclesView)
                            2 -> navigateTo(HomeNavigator.AccessView)
                        }
                    }
                )
            }
        }
    }
}
