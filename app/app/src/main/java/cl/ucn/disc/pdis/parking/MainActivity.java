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
import android.view.View;
import android.widget.EditText;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.ucn.disc.pdis.parking.zeroice.model.VehicleException;
import cl.ucn.disc.pdis.parking.zeroice.model.Vehiculo;

public class MainActivity extends AppCompatActivity {

  private static final Logger log = LoggerFactory.getLogger(MainActivity.class);
  public static final String EXTRA_PATENTE = "cl.ucn.disc.pdis.parking.MESSAGE";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void checkPatente(View view){
    log.debug("Check patent clicked");
    Intent intent = new Intent(this,DisplayInfoActivity.class);
    EditText editTextPatente = (EditText) findViewById(R.id.editTextPatente);
    String patente = editTextPatente.getText().toString();

    ZeroIce zeroIce = ZeroIce.getInstance();
    zeroIce.start();
    try {
      zeroIce.contratosPrx.findVehiculoByPatente(patente);
      log.debug("Vehicle found!");
    } catch (VehicleException ve){
      // Vehicle not found
      log.debug("Vehicle not found");
    } finally {
      zeroIce.stop();
    }

    intent.putExtra(EXTRA_PATENTE, patente);
    startActivity(intent);

  }

}