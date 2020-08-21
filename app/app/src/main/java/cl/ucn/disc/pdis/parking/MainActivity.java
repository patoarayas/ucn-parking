/*
 * Copyright (c) 2020. Patricio Araya, David Canto, Ariel Vejar.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 *
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.pdis.parking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.ucn.disc.pdis.parking.zeroice.model.VehicleException;
import cl.ucn.disc.pdis.parking.zeroice.model.Vehiculo;

public class MainActivity extends AppCompatActivity {


  /**
   * FIXME: Provisional workaround. Should implement networking outside ui thread.
   * This method allows to do networking on UI Thread.
   */
  public void enableStrictMode() {
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);
  }

  // Logger
  private static final Logger log = LoggerFactory.getLogger(MainActivity.class);

  // EXTRA_MESSAGE
  public static final String EXTRA_PATENTE = "cl.ucn.disc.pdis.parking.MESSAGE";

  /**
   * On create activity.
   *
   * @param savedInstanceState .
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    enableStrictMode();
  }

  /**
   * Takes the submitted patente and check if it exist on the backend.
   * If the patente is found takes the user to the next activity, otherwise show a toast
   * with an error message.
   *
   * @param view Send button.
   */
  public void checkPatente(View view) {


    Intent intent = new Intent(this, DisplayInfoActivity.class);
    EditText editTextPatente = (EditText) findViewById(R.id.editTextPatente);
    String patente = editTextPatente.getText().toString();

    // Initialize ICE
    ZeroIce zeroIce = ZeroIce.getInstance();
    zeroIce.start();

    try {
      // Try too find vehicle
      zeroIce.contratosPrx.findVehiculoByPatente(patente);
      log.debug("Vehicle found!");
      // Save patente on message to the other activity
      intent.putExtra(EXTRA_PATENTE, patente);
      // Start new activity
      startActivity(intent);
    } catch (VehicleException ve) {
      // Vehicle not found
      log.debug("Vehicle not found");
      // Show a toast to the user
      Toast.makeText(getApplicationContext(), "No se encontró la patente ingresada", Toast.LENGTH_SHORT).show();
    } finally {
      zeroIce.stop();
    }

  }

}