package cl.ucn.disc.pdis.parking.compose.view.tab

import android.content.Context
import android.widget.Toast
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import cl.ucn.disc.pdis.parking.ZerocIce
import cl.ucn.disc.pdis.parking.compose.Navigator
import cl.ucn.disc.pdis.parking.compose.navigateTo
import cl.ucn.disc.pdis.parking.values.green
import cl.ucn.disc.pdis.parking.values.lightGray
import cl.ucn.disc.pdis.parking.values.lightGreen
import cl.ucn.disc.pdis.parking.zeroice.model.VehicleException
import cl.ucn.disc.pdis.parking.zeroice.model.Vehiculo
import org.slf4j.LoggerFactory

/**
 * Register vehicle's access tab
 */
class AccessView {

    /**
     * Logger.
     */
    private val log = LoggerFactory.getLogger(AccessView::class.java)

    /**
     * Zeroc-Ice instance call.
     */
    private val zeroIce = ZerocIce().getInstance()

    /**
     * UI view.
     */
    @Composable
    fun view() {

        val patent = state { TextFieldValue(text = "") }

        Column {
            Row(Modifier.drawBackground(lightGreen())) {
                Text("Patente: ", style = MaterialTheme.typography.body1, color = Color.Gray)

                Surface(
                    Modifier.padding(12.dp), shape = RoundedCornerShape(8.dp),
                    border = Border(size = Dp.Hairline, color = lightGray())
                ) {
                    TextField(patent.value,
                        onValueChange = { value -> patent.value = value },
                        modifier = Modifier.padding(14.dp) + Modifier.fillMaxWidth())
                }
            }

            // Button
            Button(modifier = Modifier.padding(12.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = green(),
                onClick = {
                    // Zeroc-Ice call
                    //var v = call(patent.toString())
                    var x = Vehiculo()
                    navigateTo(Navigator.Access(x))
                }
            ) {
                Text("Aceptar", Modifier.padding(10.dp),
                    style = MaterialTheme.typography.body1, color = Color.White)
            }
        }
    }

    /**
     * Button call.
     */
    private fun call(patent: String): Vehiculo {
        lateinit var vehicle: Vehiculo

        zeroIce.start()
        try {
            vehicle = zeroIce.contratos.findVehiculoByPatente(patent)
        }catch(e: VehicleException) {
            log.error("Error", e)
        }
        zeroIce.stop()

        return vehicle
    }
}
