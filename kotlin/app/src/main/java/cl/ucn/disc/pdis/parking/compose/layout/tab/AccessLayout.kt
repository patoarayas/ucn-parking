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

package cl.ucn.disc.pdis.parking.compose.layout.tab

import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.drawBackground
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.AlertDialog
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.RadioButton
import androidx.ui.res.imageResource
import androidx.ui.unit.dp
import cl.ucn.disc.pdis.parking.R
import cl.ucn.disc.pdis.parking.ZerocIce
import cl.ucn.disc.pdis.parking.compose.Navigator
import cl.ucn.disc.pdis.parking.compose.navigateTo
import cl.ucn.disc.pdis.parking.compose.view.tab.CheckAccessView
import cl.ucn.disc.pdis.parking.values.green
import cl.ucn.disc.pdis.parking.values.lightGreen
import cl.ucn.disc.pdis.parking.zeroice.model.Porteria
import cl.ucn.disc.pdis.parking.zeroice.model.VehicleException
import cl.ucn.disc.pdis.parking.zeroice.model.Vehiculo
import org.slf4j.LoggerFactory

/**
 * Layout class from tab.
 */
class AccessLayout {

    /**
     * Logger.
     */
    private val log = LoggerFactory.getLogger(CheckAccessView::class.java)

    /**
     * Zeroc-Ice instance call.
     */
    private val zeroIce = ZerocIce().getInstance()

    /**
     * Layout setup.
     */
    @Composable
    fun layout(vehicle: Vehiculo) {

        // Dialog message
        val dialog = state { false }

        // Main column
        Column(Modifier.drawBackground(lightGreen()) + Modifier.fillMaxHeight().fillMaxWidth()) {

            // First row
            Row {
                val image= imageResource(R.drawable.ucn)
                Image(image, Modifier.size(65.dp))
                Spacer(modifier = Modifier.preferredSize(30.dp))

                Column(Modifier.gravity(align = Alignment.CenterVertically)) {
                    Text("Registar Acceso:", style = MaterialTheme.typography.h5, color = Color.White)
                }
            }
            Spacer(modifier = Modifier.preferredSize(10.dp))

            // Second row
            Row {
                Text("Patente: ", style = MaterialTheme.typography.body1, color = Color.White)
                Text(vehicle.patente, style = MaterialTheme.typography.body2, color = green())

                Spacer(modifier = Modifier.preferredSize(10.dp))

                Text("Vehiculo: ", style = MaterialTheme.typography.body1, color = Color.White)
                Text("${vehicle.marca}, ${vehicle.modelo}",
                    style = MaterialTheme.typography.body2, color = green())
            }
            // Third row
            Row {
                Text("Rut: ", style = MaterialTheme.typography.body1, color = Color.White)
                Text(vehicle.rut, style = MaterialTheme.typography.body2, color = green())
            }
            Spacer(modifier = Modifier.preferredSize(20.dp))

            // RadioButton
            Text("Porteria:", style = MaterialTheme.typography.body1, color = Color.White)
            Spacer(modifier = Modifier.preferredSize(15.dp))

            // Radiobutton method
            val selected = radioButton()
            Spacer(modifier = Modifier.preferredSize(35.dp))

            // Button row
            Row(Modifier.gravity(align = Alignment.CenterHorizontally)) {
                Button(modifier = Modifier.padding(12.dp), shape = RoundedCornerShape(8.dp),
                    backgroundColor = green(),
                    onClick = {
                        dialog.value = true

                        zeroIce.start()
                        try {
                            zeroIce.contratos.registrarAcceso(vehicle.patente, Porteria.valueOf(selected))
                        }catch(e: VehicleException) {
                            log.error("Error ...", e)
                        }
                        zeroIce.stop()
                    }
                ) {
                    Text("Aceptar", Modifier.padding(10.dp),
                        style = MaterialTheme.typography.body1, color = Color.White)
                }
            }

            if(dialog.value)
                dialogMessage(dialog, vehicle.patente)
        }
    }

    /**
     * RadioButton setup.
     */
    @Composable
    fun radioButton(): Int {
        val array = listOf("Sur", "Mancilla", "Sangra")
        val (selectedOption, onOptionSelected) = state { array[1] }

        // Column
        Column { array.forEach { text ->
            Row {
                RadioButton(selected = (text == selectedOption),
                    onSelect = { onOptionSelected(text) }, color = green())

                Text(text = text, color = Color.White,
                    style = MaterialTheme.typography.body2.merge(),
                    modifier = Modifier.padding(start = 16.dp))
            } }
        }

        // Return the index selection
        return array.indexOf(selectedOption)
    }

    /**
     * Popup message.
     */
    @Composable
    fun dialogMessage(dialog: MutableState<Boolean>, patent: String) {
        AlertDialog(onCloseRequest = {},
            title = {Text("Request accepted", style = MaterialTheme.typography.body1)},
            text = {Text("Patente '$patent' registered", style = MaterialTheme.typography.body2)},
            confirmButton = {
                Button(backgroundColor = green(),
                    onClick = {
                        dialog.value = false
                        navigateTo(Navigator.MainView)
                    }){
                    Text("Aceptar", color = Color.White)
                }
            }
        )
    }
}
