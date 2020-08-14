package cl.ucn.disc.pdis.parking.compose.view.main

import androidx.compose.Composable
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.res.imageResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import cl.ucn.disc.pdis.parking.R
import cl.ucn.disc.pdis.parking.ZerocIce
import cl.ucn.disc.pdis.parking.values.lightGray
import cl.ucn.disc.pdis.parking.values.lightGreen

/**
 * Zeroc-Ice instance call.
 */
private val zeroIce = ZerocIce().getInstance()

@Composable
fun VehiclesView() {
    // Test
    var array = arrayListOf("TestOne", "TestTwo", "TestThree")

    AdapterList(
        data = array,
        modifier = Modifier.drawBackground(lightGreen())
    ) {
        VehiclesLayout(it)
        Divider(color = lightGray())
    }
}

@Composable
fun VehiclesLayout(it: String) {
    // Image
    val image= imageResource(R.drawable.ucn)

    Box(backgroundColor = lightGreen(), modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.padding(10.dp)) {
            Row(Modifier.weight(0.5f, true)) {
                Box(Modifier.fillMaxWidth().fillMaxHeight()) {
                    Image(image, contentScale = ContentScale.Fit)
                }
            }
            Spacer(modifier = Modifier.preferredSize(12.dp))
            Column(modifier = Modifier.weight(3.0f, true)) {
                Test(it)
                Spacer(modifier = Modifier.preferredSize(2.dp))
            }
        }
    }
}

@Composable
fun Test(test: String) {
    Text(
        text = test,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White
        )
    )
}
