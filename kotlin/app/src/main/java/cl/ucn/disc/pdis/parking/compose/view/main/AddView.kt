package cl.ucn.disc.pdis.parking.compose.view.main

import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import cl.ucn.disc.pdis.parking.values.lightGray
import cl.ucn.disc.pdis.parking.values.lightGreen

@Composable
fun AddView() {

    // Scroller
    VerticalScroller {
        // Input
        val text = state { TextFieldValue(text = "") }

        Text("Rut", style = MaterialTheme.typography.body1)
        Row(verticalGravity = Alignment.CenterVertically) {
            Row(Modifier.weight(1.8f, true)) {
                EditTextField(text.value, onValueChange = { value -> text.value = value })
            }
        }
    }
}

@Composable
fun EditTextField(value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit) {
    Surface(Modifier.padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        border = Border(size = Dp.Hairline, color = Color.LightGray)
    ) {
        TextField(value = value, onValueChange = onValueChange,
            modifier = Modifier.padding(16.dp)
                    + Modifier.wrapContentHeight(align = Alignment.CenterVertically)
                    + Modifier.fillMaxWidth()
        )
    }
}
