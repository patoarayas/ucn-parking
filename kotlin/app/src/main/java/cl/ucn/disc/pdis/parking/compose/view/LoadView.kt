package cl.ucn.disc.pdis.parking.compose.view

import androidx.compose.Composable
import androidx.compose.remember
import androidx.lifecycle.MutableLiveData
import androidx.ui.livedata.observeAsState
import androidx.ui.material.Scaffold
import androidx.ui.material.ScaffoldState
import cl.ucn.disc.pdis.parking.compose.layout.LoadLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Initial view.
 */
@Composable
fun LoadView(scope: CoroutineScope, scaffoldState: ScaffoldState = remember { ScaffoldState() }) {

    // LiveData to State
    val launch = MutableLiveData(false)
    val viewState = launch.observeAsState(false)

    // Implements basic material design (visual layout structure)
    Scaffold(
        scaffoldState = scaffoldState,
        bodyContent = { viewState.value?.let {
                initLaunch -> LoadLayout(initLaunch, scaffoldState = scaffoldState) }
        }
    )
    launchInitialView(scope, launch)
}

/**
 * Initial view delay.
 */
fun launchInitialView(scope: CoroutineScope, launch: MutableLiveData<Boolean>) {
    scope.launch {
        delay(2000)
        launch.postValue(true)
    }
}
