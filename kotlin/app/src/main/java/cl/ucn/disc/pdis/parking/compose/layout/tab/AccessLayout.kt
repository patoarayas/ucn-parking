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
import androidx.compose.Model
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.TextFieldValue
import androidx.ui.foundation.drawBackground
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.RadioButton
import androidx.ui.material.RadioGroup
import androidx.ui.res.imageResource
import androidx.ui.text.TextStyle
import androidx.ui.unit.dp
import cl.ucn.disc.pdis.parking.R
import cl.ucn.disc.pdis.parking.ZerocIce
import cl.ucn.disc.pdis.parking.compose.view.tab.CheckAccessView
import cl.ucn.disc.pdis.parking.values.green
import cl.ucn.disc.pdis.parking.values.lightGreen
import cl.ucn.disc.pdis.parking.zeroice.model.VehicleException
import cl.ucn.disc.pdis.parking.zeroice.model.Vehiculo
import com.zeroc.Ice.CommunicatorDestroyedException
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
     * State.
     */
    @Model
    class RadioState(var option: String? = null)

    /**
     * Layout setup.
     */
    @Composable
    fun layout(vehicle: Vehiculo) {

        RadioGroup {

        }
        // Input data
        val radio = state { RadioState() }

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
                Text(vehicle.patente, style = MaterialTheme.typography.body2, color = Color.White)

                Spacer(modifier = Modifier.preferredSize(10.dp))

                Text("Vehiculo: ", style = MaterialTheme.typography.body1, color = Color.White)
                Text("${vehicle.marca}, ${vehicle.modelo}",
                    style = MaterialTheme.typography.body2, color = Color.White)
            }
            // Third row
            Row {
                Text("Rut: ", style = MaterialTheme.typography.body1, color = Color.White)
                Text(vehicle.rut, style = MaterialTheme.typography.body2, color = Color.White)
            }
            Spacer(modifier = Modifier.preferredSize(10.dp))

            // RadioButton method
            Text("Porteria:", style = MaterialTheme.typography.body1, color = Color.White)
            radioButton(state = RadioState())
            Spacer(modifier = Modifier.preferredSize(35.dp))

            // Button row
            Row(Modifier.gravity(align = Alignment.CenterHorizontally)) {
                // Button
                Button(modifier = Modifier.padding(12.dp), shape = RoundedCornerShape(8.dp),
                    backgroundColor = green(),
                    onClick = {
                        log.debug("CHOOSE = {}", RadioState().option)
                        //zeroIce.start()
                        //zeroIce.contratos.registrarAcceso(vehicle.patente, RadioState().option)
                        //zeroIce.stop()
                    }
                ) {
                    Text("Aceptar", Modifier.padding(10.dp),
                        style = MaterialTheme.typography.body1, color = Color.White)
                }
            }
        }
    }

    /**
     * RadioButton setup.
     */
    @Composable
    fun radioButton(state: RadioState) {
        val array = listOf("Sur", "Mancilla", "Sangra")
        RadioGroup(
            options = array,
            radioColor = green(),
            textStyle = TextStyle(color = Color.White),
            selectedOption = state.option ?: array[0],
            onSelectedChange = {state.option = it}
        )
    }
}
