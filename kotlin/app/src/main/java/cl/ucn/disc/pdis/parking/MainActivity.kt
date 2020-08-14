package cl.ucn.disc.pdis.parking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.Composable
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import androidx.ui.tooling.preview.Preview
import cl.ucn.disc.pdis.parking.compose.AppNavigation
import cl.ucn.disc.pdis.parking.compose.Navigator
import cl.ucn.disc.pdis.parking.compose.view.LoadView
import cl.ucn.disc.pdis.parking.compose.view.MainView
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
     * Zeroc-Ice instance call.
     */
    private val zeroIce = ZerocIce().getInstance()

    /**
     * Scope.
     * CoroutineScope created for UI components.
     */
    private val scope = MainScope()

    /**
     * Main method.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the UI
        setContent {
            Template()
        }
    }

    /**
     * UI View.
     */
    @Composable
    fun Template() {
        // Allows switching between layouts with a crossfade animation
        Crossfade(AppNavigation.currentView) { screen ->
            when(screen) {
                is Navigator.LoadView -> LoadView(scope)
                is Navigator.MainView -> MainView()
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
     * Preview UI View.
     */
    @Composable
    @Preview
    fun PreviewTemplate() {
        Template()
    }
}
