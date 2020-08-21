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
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.livedata.observeAsState
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.res.imageResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import cl.ucn.disc.pdis.parking.R
import cl.ucn.disc.pdis.parking.repository.repository
import cl.ucn.disc.pdis.parking.values.green
import cl.ucn.disc.pdis.parking.values.lightGray
import cl.ucn.disc.pdis.parking.values.lightGreen
import cl.ucn.disc.pdis.parking.zeroice.model.Vehiculo

/**
 * Vehicles tab view.
 */
class VehiclesView {

    /**
     * View setup.
     */
    @Composable
    fun view() {

        // Repository access
        val vehicleRepository = repository.current
        val state = vehicleRepository.getVehiculos().observeAsState(initial = arrayListOf())

        // Scrolling list that only composes and lays out the currently visible items
        AdapterList(data = state.value, modifier = Modifier.drawBackground(lightGreen())) {
            vehiclesLayout(it)
            Divider(color = lightGray())
        }
    }

    /**
     * Layout setup.
     */
    @Composable
    fun vehiclesLayout(vehicle: Vehiculo) {

        // Image
        val image= imageResource(R.drawable.ucn)

        // Vehicle's info box
        Box(backgroundColor = lightGreen(), modifier = Modifier.fillMaxWidth()) {
            Row(Modifier.padding(all = 10.dp)) {
                Column(Modifier.gravity(align = Alignment.CenterVertically)) {
                    Image(image, Modifier.size(85.dp))
                }
                Spacer(modifier = Modifier.preferredSize(30.dp))

                Column(Modifier.gravity(align = Alignment.CenterVertically)) {
                    Row {
                        Text("Rut: ", color = green(), style = MaterialTheme.typography.body1)
                        vehicleData(vehicle.rut)
                    }
                    Row {
                        Text("Patente: ", color = green(), style = MaterialTheme.typography.body1)
                        vehicleData(vehicle.patente)
                    }
                    Row {
                        Text("Marca: ", color = green(), style = MaterialTheme.typography.body1)
                        vehicleData(vehicle.marca)
                    }
                    Row {
                        Text("Modelo: ", color = green(), style = MaterialTheme.typography.body1)
                        vehicleData(vehicle.modelo)
                    }
                    Row {
                        Text("Año: ", color = green(), style = MaterialTheme.typography.body1)
                        vehicleData(vehicle.anio.toString())
                    }
                    Row {
                        Text("Observacion: ", color = green(), style = MaterialTheme.typography.body1)
                        vehicleData(vehicle.observacion)
                    }
                    Row {
                        Text("Color: ", color = green(), style = MaterialTheme.typography.body1)
                        vehicleData(vehicle.color)
                    }
                }
            }
        }
    }

    /**
     * Vehicle's data.
     */
    @Composable
    fun vehicleData(name: String) {
        Text(text = name,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White
            )
        )
    }
}
