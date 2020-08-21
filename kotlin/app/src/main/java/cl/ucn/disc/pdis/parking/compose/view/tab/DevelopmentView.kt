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

package cl.ucn.disc.pdis.parking.compose.view.tab

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.layout.RowScope.gravity
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.res.imageResource
import androidx.ui.unit.dp
import cl.ucn.disc.pdis.parking.R
import cl.ucn.disc.pdis.parking.compose.layout.tab.AccessLayout
import cl.ucn.disc.pdis.parking.values.green
import cl.ucn.disc.pdis.parking.values.lightGreen

/**
 * Development tab view.
 */
class DevelopmentView {

    /**
     * View setup
     */
    @Composable
    fun view() {

        // Initial Column
        Column(Modifier.drawBackground(lightGreen()) + Modifier.fillMaxHeight().fillMaxWidth()) {

            Spacer(modifier = Modifier.preferredSize(25.dp))

            Column(Modifier.gravity(align = Alignment.CenterVertically)) {
                val image= imageResource(R.drawable.ucn)
                Image(image, Modifier.size(105.dp))

                Row(Modifier.gravity(align = Alignment.CenterHorizontally)) {
                    Text("UCN", style = MaterialTheme.typography.body1, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.preferredSize(25.dp))

            Column(Modifier.gravity(align = Alignment.CenterVertically)) {
                Row(Modifier.gravity(align = Alignment.CenterHorizontally)) {
                    Text("DEVELOPERS", style = MaterialTheme.typography.h5, color = Color.White)
                }
                Row(Modifier.gravity(align = Alignment.CenterHorizontally)) {
                    Text("Patricio Araya", style = MaterialTheme.typography.body1, color = green())
                }
                Row(Modifier.gravity(align = Alignment.CenterHorizontally)) {
                    Text("David Canto", style = MaterialTheme.typography.body1, color = green())
                }
                Row(Modifier.gravity(align = Alignment.CenterHorizontally)) {
                    Text("Ariel Vejar", style = MaterialTheme.typography.body1, color = green())
                }
            }

            Spacer(modifier = Modifier.preferredSize(25.dp))

            Column(Modifier.gravity(align = Alignment.CenterVertically)) {
                Row(Modifier.gravity(align = Alignment.CenterHorizontally)) {
                    Text("SUBJECT", style = MaterialTheme.typography.h5, color = Color.White)
                }
                Row(Modifier.gravity(align = Alignment.CenterHorizontally)) {
                    Text(
                        text = "Proyecto Desarrollo e Integracion de Soluciones",
                        style = MaterialTheme.typography.body1,
                        color = green())
                }
            }
        }
    }
}
