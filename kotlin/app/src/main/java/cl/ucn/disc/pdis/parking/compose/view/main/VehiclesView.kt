package cl.ucn.disc.pdis.parking.compose.view.main

import androidx.compose.Composable
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.livedata.observeAsState
import androidx.ui.material.Divider
import androidx.ui.res.imageResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import cl.ucn.disc.pdis.parking.R
import cl.ucn.disc.pdis.parking.repository.repository
import cl.ucn.disc.pdis.parking.values.lightGray
import cl.ucn.disc.pdis.parking.values.lightGreen
import cl.ucn.disc.pdis.parking.zeroice.model.Vehiculo

/**
 * Vehiculo's view.
 */
@Composable
fun VehiclesView() {

    /* TODO: Make it work the Zeroc-Ice connection
    // Repository access
    val vehicleRepository = repository.current
    val state = vehicleRepository.getVehiculos().observeAsState(initial = arrayListOf())
    */

    // Testing
    val array = arrayListOf("One", "Two", "Three")

    // Scrolling list that only composes and lays out the currently visible items
    AdapterList(data = array, modifier = Modifier.drawBackground(lightGreen())) {
        VehiclesLayout(it)
        Divider(color = lightGray())
    }
}

/**
 * Layout setup.
 */
@Composable
fun VehiclesLayout(vehicle: String) {

    val image= imageResource(R.drawable.ucn)

    Box(backgroundColor = lightGreen(), modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.padding(all = 10.dp)) {
            Row(Modifier.weight(0.5f, true)) {
                Box(Modifier.fillMaxWidth().fillMaxHeight()) {
                    Image(image, contentScale = ContentScale.Fit)
                }
            }
            Spacer(modifier = Modifier.preferredSize(12.dp))

            Column(modifier = Modifier.weight(3.0f, true)) {
                //VehicleRut(vehicle)
                //Spacer(modifier = Modifier.preferredSize(2.dp))
                //VehicleData(vehicle.patente)
            }
        }
    }
}

@Composable
fun VehicleRut(vehicle: Vehiculo) {
    Text(
        text = vehicle.rut,
        style = TextStyle(
            fontSize = 18.sp,
            color = Color.White
        )
    )
}

@Composable
fun VehicleData(name: String) {
    Text(
        text = name,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color(3, 183, 144)
        )
    )
}
