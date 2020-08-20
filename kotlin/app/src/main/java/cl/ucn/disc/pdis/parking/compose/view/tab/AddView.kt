package cl.ucn.disc.pdis.parking.compose.view.tab

import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import cl.ucn.disc.pdis.parking.ZerocIce
import cl.ucn.disc.pdis.parking.values.green
import cl.ucn.disc.pdis.parking.values.lightGray
import cl.ucn.disc.pdis.parking.values.lightGreen
import cl.ucn.disc.pdis.parking.zeroice.model.Vehiculo
import com.zeroc.Ice.ConnectionRefusedException
import org.apache.commons.lang3.math.NumberUtils.toInt

/**
 * Add vehicles tab view.
 */
@Composable
fun AddView() {

    // Input
    val rut = state { TextFieldValue(text = "") }
    val patent = state { TextFieldValue(text = "") }
    val brand = state { TextFieldValue(text = "") }
    val model = state { TextFieldValue(text = "") }
    val year = state { TextFieldValue(text = "") }
    val obs = state { TextFieldValue(text = "") }
    val color = state { TextFieldValue(text = "") }

    // Scroller
    VerticalScroller(modifier = Modifier.drawBackground(lightGreen()) +
            Modifier.fillMaxHeight().fillMaxWidth()) {
        EditTextField("Rut:", rut.value, onValueChange = { value -> rut.value = value })
        EditTextField("Patente:", patent.value, onValueChange = { value -> patent.value = value })
        EditTextField("Marca:", brand.value, onValueChange = { value -> brand.value = value })
        EditTextField("Model:", model.value, onValueChange = { value -> model.value = value })
        EditTextField("Año:", year.value, onValueChange = { value -> year.value = value })
        EditTextField("Observacion:", obs.value, onValueChange = { value -> obs.value = value })
        EditTextField("Color:", color.value, onValueChange = { value -> color.value = value })

        Row(horizontalArrangement = Arrangement.End) {
            PressButton(rut.toString(), patent.toString(), brand.toString(), model.toString(),
                toInt(year.toString()), obs.toString(), color.toString())
        }
    }
}

/**
 * Form.
 */
@Composable
fun EditTextField(data: String, value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit) {

    Text(data, style = MaterialTheme.typography.body1, color = Color.Gray)
    Surface(Modifier.padding(12.dp), shape = RoundedCornerShape(8.dp),
        border = Border(size = Dp.Hairline, color = lightGray())
    ) {
        TextField(value, onValueChange, Modifier.padding(14.dp) + Modifier.fillMaxWidth())
    }
}

@Composable
fun PressButton(rut: String, patent: String, brand: String, model: String, year: Int,
                obs: String, color: String) {

    // Adds a new vehiculo
    var vehicle = Vehiculo(rut, patent, brand, model, year, obs, color)

    // Button
    Button(modifier = Modifier.padding(12.dp), shape = RoundedCornerShape(8.dp), backgroundColor = green(),
        onClick = {
            // Zeroc-Ice call
            ZerocIce().getInstance().start()
            ZerocIce().getInstance().contratos.registrarVehiculo(vehicle)
            ZerocIce().getInstance().stop()

            /*
            try {
                ZerocIce().getInstance().start()
                ZerocIce().getInstance().contratos.registrarVehiculo(vehicle)
                ZerocIce().getInstance().stop()
            }catch (e: ConnectionRefusedException) {
                println(e)
            }
            */
        }
    ) {
        Text("Agregar", Modifier.padding(10.dp),
            style = MaterialTheme.typography.body1, color = Color.White)
    }
}
