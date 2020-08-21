package cl.ucn.disc.pdis.parking.compose.view.tab

import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.*
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
import com.zeroc.Ice.CommunicatorDestroyedException
import org.slf4j.LoggerFactory

/**
 * Check vehicle's patent class.
 */
class CheckAccessView {

    /**
     * Logger.
     */
    private val log = LoggerFactory.getLogger(CheckAccessView::class.java)

    /**
     * Zeroc-Ice instance call.
     */
    private val zeroIce = ZerocIce().getInstance()

    /**
     * View setup.
     */
    @Composable
    fun view() {

        // Input data
        val patent = state { TextFieldValue(text = "") }

        // Dialog message
        val dialog = state { false }

        // Column
        Column(Modifier.drawBackground(lightGreen()) + Modifier.fillMaxHeight().fillMaxWidth()) {

            Spacer(modifier = Modifier.preferredSize(2.dp))
            Text("Patente: ", style = MaterialTheme.typography.h6, color = Color.White)

            Row {
                Surface(
                    Modifier.padding(12.dp), shape = RoundedCornerShape(8.dp),
                    border = Border(size = Dp.Hairline, color = lightGray())
                ) {
                    TextField(patent.value,
                        onValueChange = { value -> patent.value = value },
                        modifier = Modifier.padding(14.dp) + Modifier.fillMaxWidth())
                }
            }

            Row(Modifier.gravity(align = Alignment.CenterHorizontally)) {
                // Button
                Button(modifier = Modifier.padding(12.dp), shape = RoundedCornerShape(8.dp),
                    backgroundColor = green(),
                    onClick = {

                        var vehicle: Vehiculo? = null

                        if(patent.value.text.isNotEmpty()) {
                            try {
                                // Zero-Ice call
                                vehicle = call(patent.value.text)

                                if(vehicle != null) {
                                    navigateTo(Navigator.Access(vehicle))
                                }
                            }catch(e: KotlinNullPointerException){
                                log.error("Error ...", e)
                            }

                            if(vehicle == null) {
                                dialog.value = true
                            }
                        }
                    }
                ) {
                    Text("Aceptar", Modifier.padding(10.dp),
                        style = MaterialTheme.typography.body1, color = Color.White)
                }
            }

            if(dialog.value)
                dialogMessage(dialog, patent.value.text)
        }
    }

    /**
     * Button call.
     */
    private fun call(patent: String): Vehiculo {
        var vehicle: Vehiculo? = null

        zeroIce.start()
        try {
            vehicle = zeroIce.contratos.findVehiculoByPatente(patent)

        }catch(e: VehicleException) {
            log.error("Error ...", e)
        }catch(e: CommunicatorDestroyedException) {
            log.error("Error ...", e)
        }
        zeroIce.stop()

        return vehicle!!
    }

    /**
     * Popup message.
     */
    @Composable
    fun dialogMessage(dialog: MutableState<Boolean>, patent: String) {
        AlertDialog(onCloseRequest = {},
            title = {Text("Error Message", style = MaterialTheme.typography.body1)},
            text = {Text("Patente '$patent' not founded", style = MaterialTheme.typography.body2)},
            confirmButton = {
                Button(onClick = {dialog.value = false}, backgroundColor = green()){
                    Text("Aceptar", color = Color.White)
                }
            }
        )
    }
}
