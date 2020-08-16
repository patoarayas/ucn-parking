package cl.ucn.disc.pdis.parking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.Composable
import androidx.compose.Providers
import androidx.compose.state
import androidx.ui.animation.Crossfade
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.layout.RowScope.weight
import androidx.ui.material.Surface
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import cl.ucn.disc.pdis.parking.compose.AppNavigation
import cl.ucn.disc.pdis.parking.compose.Navigator
import cl.ucn.disc.pdis.parking.compose.view.LoadView
import cl.ucn.disc.pdis.parking.compose.view.MainView
import cl.ucn.disc.pdis.parking.repository.VehicleRepository
import cl.ucn.disc.pdis.parking.repository.repository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
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
     * Scope - CoroutineScope created for UI components.
     */
    private val scope = MainScope()

    /**
     * Main method.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the UI
        setContent {
            Providers(repository provides VehicleRepository()) {
                Template()
                //Test()
            }
        }
    }

    /*
    /**
     * Test
     */
    @Composable
    fun Test() {
        val image= imageResource(R.drawable.ucn)

        zeroIce.start()
        Box(backgroundColor = lightGreen(), modifier = Modifier.fillMaxWidth()) {
            Row(Modifier.padding(all = 10.dp)) {
                Row(Modifier.weight(0.5f, true)) {
                    Box(Modifier.fillMaxWidth().fillMaxHeight()) {
                        Image(image, contentScale = ContentScale.Fit)
                    }
                }
                Spacer(modifier = Modifier.preferredSize(12.dp))

                Column(modifier = Modifier.weight(3.0f, true)) {
                    var vehicles = zeroIce.sistema!!.vehiculos
                    for(i in vehicles) {
                        VehicleData(i.rut)
                        Spacer(modifier = Modifier.preferredSize(2.dp))
                        VehicleData(i.patente)
                    }
                }
            }
        }
        zeroIce.stop()
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
    */

    /**
     * UI view.
     */
    @Composable
    fun Template() {
        // Allows switching between layouts with a crossfade animation
        Crossfade(AppNavigation.currentView) { view ->
            when(view) {
                is Navigator.LoadView -> LoadView(scope)
                is Navigator.MainView -> MainView()
                //
            }
        }
    }

    /**
     * Destroy scope.
     */
    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    /**
     * Preview UI view.
     */
    @Composable
    @Preview
    fun PreviewTemplate() {
        Template()
    }
}
