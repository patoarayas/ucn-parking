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

package cl.ucn.disc.pdis.parking.compose.view.tab

import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.text.style.TextAlign
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import cl.ucn.disc.pdis.parking.R
import cl.ucn.disc.pdis.parking.compose.HomeNavigator
import cl.ucn.disc.pdis.parking.compose.Navigator
import cl.ucn.disc.pdis.parking.compose.layout.tab.AccessLayout
import cl.ucn.disc.pdis.parking.compose.navigateTo
import cl.ucn.disc.pdis.parking.values.green
import cl.ucn.disc.pdis.parking.values.lightGray
import cl.ucn.disc.pdis.parking.zeroice.model.Vehiculo

/**
 * Register vehicle's access class.
 */
class AccessView {

    /**
     * View setup.
     */
    @Composable
    fun view(vehicle: Vehiculo) {
        //
        Scaffold(
            topAppBar = { accessToolbar(vehicle) },
            bodyContent = { AccessLayout().layout(vehicle) },
            bottomAppBar = {}
        )
    }

    /**
     * Toolbar setup.
     */
    @Composable
    fun accessToolbar(vehicle: Vehiculo) {

        Column {
            TopAppBar(
                backgroundColor = lightGray(),
                navigationIcon = {
                    IconButton(onClick = { navigateTo(Navigator.MainView)}) {
                        Icon(vectorResource(id = R.drawable.back_button),
                            modifier = Modifier.padding(start = 8.dp), tint = Color.White
                        )
                    }
                },
                title = {
                    Text(text = vehicle.patente, color = Color.White,
                        style = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Center)
                    )
                }
            )
        }
    }
}
