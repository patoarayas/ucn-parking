/*
 * MIT License
 *
 * Copyright (c) 2020 Patricio Araya, David Canto, Ariel Vejar
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
 * Load view.
 */
class LoadView {

    /**
     * Initial view setup.
     */
    @Composable
    fun view(scope: CoroutineScope, scaffoldState: ScaffoldState = remember { ScaffoldState() }) {

        // LiveData to State
        val launch = MutableLiveData(false)
        val viewState = launch.observeAsState(false)

        // Implements basic material design (visual layout structure)
        Scaffold(
            scaffoldState = scaffoldState,
            bodyContent = {
                viewState.value?.let {
                        initLaunch -> LoadLayout().layout(initLaunch, scaffoldState = scaffoldState)
                }
            }
        )
        // Delay
        launchInitialView(scope, launch)
    }

    /**
     * View delay.
     */
    private fun launchInitialView(scope: CoroutineScope, launch: MutableLiveData<Boolean>) {
        scope.launch {
            delay(2000)
            launch.postValue(true)
        }
    }
}
