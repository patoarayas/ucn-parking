package cl.ucn.disc.pdis.parking.compose.view.tab

import androidx.compose.Composable
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Image
import androidx.ui.layout.*
import androidx.ui.res.imageResource
import androidx.ui.unit.dp
import cl.ucn.disc.pdis.parking.R
import cl.ucn.disc.pdis.parking.zeroice.model.Vehiculo

@Composable
fun Access(vehicle: Vehiculo) {

    val image= imageResource(R.drawable.ucn)

    Row(modifier = androidx.ui.core.Modifier.padding(10.dp)) {
        Row(modifier = Modifier.weight(0.5f, true)) {
            Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                Image(image, contentScale = ContentScale.Fit)
            }
        }
    }
}
