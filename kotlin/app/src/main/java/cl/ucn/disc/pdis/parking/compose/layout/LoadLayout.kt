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

package cl.ucn.disc.pdis.parking.compose.layout

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.tag
import androidx.ui.foundation.Box
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.drawBackground
import androidx.ui.layout.*
import androidx.ui.material.Scaffold
import androidx.ui.material.ScaffoldState
import androidx.ui.res.imageResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontStyle
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import cl.ucn.disc.pdis.parking.R
import cl.ucn.disc.pdis.parking.compose.Navigator
import cl.ucn.disc.pdis.parking.compose.navigateTo
import cl.ucn.disc.pdis.parking.values.green
import cl.ucn.disc.pdis.parking.values.lightGreen

/**
 * Layout class - Loading view that can be see it before the app runs.
 */
class LoadLayout {

    /**
     * Reference corresponding to the constraint layout tags.
     */
    private val SURFACE_TAG = "surface"
    private val TEXT_FROM_TAG = "textFrom"
    private val TEXT_COMPANY_TAG = "textCompany"
    private val IMAGE_TAG = "imageLogo"

    /**
     * Layout setup.
     */
    @Composable
    fun layout(initLaunch: Boolean, scaffoldState: ScaffoldState) {

        // Implements basic material design (visual layout structure)
        Scaffold(
            scaffoldState = scaffoldState,
            bodyContent = {
                // Set the layout positions
                ConstraintLayout(constraintSet = constraintSet()) {
                    // Combines common layout and draw logic
                    Box(Modifier.tag(SURFACE_TAG) +
                            Modifier.drawBackground(lightGreen()) + Modifier.fillMaxSize())

                    // Text
                    Text("from", Modifier.tag(TEXT_FROM_TAG), green())

                    // Text
                    Text("UCN", Modifier.tag(TEXT_COMPANY_TAG) + Modifier.padding(4.dp),
                        color = green(),
                        style = TextStyle(fontSize = 20.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontStyle = FontStyle.Normal
                        )
                    )

                    // Image
                    val image = imageResource(R.drawable.ucn)
                    Image(image, Modifier.tag(IMAGE_TAG) + Modifier.size(145.dp))

                    // Return to MainView
                    if(initLaunch) {
                        navigateTo(Navigator.MainView)
                    }
                }
            }
        )
    }

    /**
     * Constraints used to layout the subclasses.
     */
    @Composable
    fun constraintSet(): ConstraintSet {
        return ConstraintSet {

            // Define the constraints to be imposed by its tag
            tag(SURFACE_TAG).apply {
                left constrainTo parent.left
                top constrainTo parent.top
                right constrainTo parent.right
                bottom constrainTo parent.bottom
            }
            tag(IMAGE_TAG).apply {
                left constrainTo parent.left
                top constrainTo parent.top
                right constrainTo parent.right
                bottom constrainTo parent.bottom
                width to wrap
                height to wrap
            }
            tag(TEXT_COMPANY_TAG).apply {
                bottom constrainTo parent.bottom
                right constrainTo parent.right
                left constrainTo parent.left
            }
            tag(TEXT_FROM_TAG).apply {
                bottom constrainTo tag(TEXT_COMPANY_TAG).top
                right constrainTo tag(TEXT_COMPANY_TAG).right
                left constrainTo tag(TEXT_COMPANY_TAG).left
            }
        }
    }
}
