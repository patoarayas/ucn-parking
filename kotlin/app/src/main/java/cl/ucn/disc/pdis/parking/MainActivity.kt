package cl.ucn.disc.pdis.parking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import androidx.compose.Composable
import androidx.compose.Providers
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import androidx.ui.tooling.preview.Preview
import cl.ucn.disc.pdis.parking.compose.AppNavigation
import cl.ucn.disc.pdis.parking.compose.Navigator
import cl.ucn.disc.pdis.parking.compose.view.LoadView
import cl.ucn.disc.pdis.parking.compose.view.MainView
import cl.ucn.disc.pdis.parking.compose.view.tab.AccessView
import cl.ucn.disc.pdis.parking.repository.VehicleRepository
import cl.ucn.disc.pdis.parking.repository.repository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * Main Activity Class.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Scope - CoroutineScope created for UI components.
     */
    private val scope = MainScope()

    /**
     * Main method.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Find a better way
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build())

        // Set the UI
        setContent {
            Providers(repository provides VehicleRepository()) {
                Template()
            }
        }
    }

    /**
     * UI view.
     */
    @Composable
    fun Template() {
        // Allows switching between layouts with a crossfade animation
        Crossfade(AppNavigation.currentView) { view ->
            when(view) {
                is Navigator.LoadView -> LoadView().view(scope)
                is Navigator.MainView -> MainView().view()
                is Navigator.Access -> AccessView().view(view.vehicle)
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
