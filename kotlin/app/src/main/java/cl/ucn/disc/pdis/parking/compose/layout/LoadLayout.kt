package cl.ucn.disc.pdis.parking.compose.layout

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.tag
import androidx.ui.foundation.Box
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.drawBackground
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Divider
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
 * Reference corresponding to the constraint layout tags.
 */
private const val SURFACE_TAG = "surface"
private const val TEXT_FROM_TAG = "textFrom"
private const val TEXT_COMPANY_TAG = "textCompany"
private const val IMAGE_TAG = "imageLogo"

/**
 * Layout setup.
 */
@Composable
fun LoadLayout(initLaunch: Boolean, scaffoldState: ScaffoldState) {

    // Constraints used to layout the subclasses
    val constraintSet = ConstraintSet {

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

    // Implements basic material design (visual layout structure)
    Scaffold(
        scaffoldState = scaffoldState,
        bodyContent = {
            // Set the layout positions
            ConstraintLayout(constraintSet = constraintSet) {

                // Combines common layout and draw logic
                Box(Modifier.fillMaxSize() +
                        Modifier.drawBackground(lightGreen()) +
                        Modifier.tag(SURFACE_TAG))

                // Text
                Text("from", Modifier.tag(TEXT_FROM_TAG), color = green())

                // Divider (line)
                //Divider(color = Color.Transparent, thickness = 8.dp)

                // Text
                Text("UCN",
                    Modifier.tag(TEXT_COMPANY_TAG) + Modifier.padding(4.dp),
                    color = green(),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Normal)
                )

                // Image
                val image = imageResource(R.drawable.ucn)
                Image(image, Modifier.tag(IMAGE_TAG) + Modifier.size(145.dp))

                // Return to MainView
                if (initLaunch) {
                    navigateTo(Navigator.MainView)
                }
            }
        }
    )
}


