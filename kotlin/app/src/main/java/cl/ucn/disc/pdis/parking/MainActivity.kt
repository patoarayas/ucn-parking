package cl.ucn.disc.pdis.parking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.animation.Crossfade
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.res.imageResource
import androidx.ui.text.style.TextOverflow
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.slf4j.LoggerFactory

/**
 * Main Activity Class.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Logger.
     */
    private val log = LoggerFactory.getLogger(MainActivity::class.java)

    /**
     * Zeroc-Ice instance call.
     */
    private val zeroIce = ZerocIce().getInstance()

    /**
     * Main method.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the UI
        setContent {
            // Set a Material design to the view
            MaterialTheme {
                Template()
            }
        }
    }

    @Composable
    @Preview
    fun PreviewTemplate() {
        MaterialTheme {
            Template()
        }
    }

    /**
     * UI View.
     */
    @Composable
    fun Template() {
        // Image
        val image = imageResource(R.drawable.ucn)

        // Text typography
        val typography = MaterialTheme.typography

        // Set the text into a column
        Column(modifier = Modifier.padding(15.dp)) {
            // Image setup
            val img = Modifier
                // Height image setup
                .preferredHeightIn(maxHeight = 140.dp)
                .fillMaxWidth()
            Image(image, modifier = img, contentScale = ContentScale.Crop)

            // Text
            Text("Register Vehicle ...", style = typography.h6, color = Color.White,
                maxLines = 2, overflow = TextOverflow.Ellipsis)
            Text("Please fill the next form", style = typography.body2, color = Color.White)

            // Space
            Spacer(Modifier.preferredHeight(6.dp))

            // Form
            val rut = state { TextFieldValue(text = "") }
            //val patent = state { TextFieldValue(text = "") }

            Text("Rut:", style = typography.body2)
            Surface(modifier = Modifier.padding(10.dp), shape = RoundedCornerShape(10.dp)) {
                TextField(value = rut.value,
                    onValueChange = {value -> rut.value = value})
            }
        }
    }
}
