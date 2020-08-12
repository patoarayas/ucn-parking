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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.ucn.disc.pdis.parking.zeroice.model.Persona;
import cl.ucn.disc.pdis.parking.zeroice.model.PersonaException;
import cl.ucn.disc.pdis.parking.zeroice.model.Porteria;
import cl.ucn.disc.pdis.parking.zeroice.model.VehicleException;
import cl.ucn.disc.pdis.parking.zeroice.model.Vehiculo;

public class DisplayInfoActivity extends AppCompatActivity {

  // ICE instance
  private final static ZeroIce zeroIce = ZeroIce.getInstance();
  // Logger
  private static final Logger log = LoggerFactory.getLogger(DisplayInfoActivity.class);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_info);

    Intent intent = getIntent();
    String patente = intent.getStringExtra(MainActivity.EXTRA_PATENTE);

    // Get data
    loadData(patente);

    // Fill radio buttons

    RadioButton rb0 = findViewById(R.id.porteria_0);
    RadioButton rb1 = findViewById(R.id.porteria_1);
    RadioButton rb2 = findViewById(R.id.porteria_2);
    rb0.setText(Porteria.valueOf(0).toString());
    rb1.setText(Porteria.valueOf(1).toString());
    rb2.setText(Porteria.valueOf(2).toString());


  }

  /**
   * Call zeroIce and retrieve data of Persona and Vehicle.
   *
   * @param patente The patente of vehiculo
   */
  private void loadData(String patente) {

    // Start communication.
    zeroIce.start();

    try {
      // Get vehicle data
      Vehiculo vehiculo = zeroIce.contratosPrx.findVehiculoByPatente(patente);
      fillVehiculoView(vehiculo);
      try {
        // Get persona data
        Persona persona = zeroIce.contratosPrx.findPersonaByRut(vehiculo.rut);
        fillPersonaView(persona);

      } catch (PersonaException pe) {
        log.error("Error retrieving Persona.", pe);
      }
    } catch (VehicleException ve) {
      log.error("Error retrieving vehicle.", ve);
    }
    // Stop ICE
    zeroIce.stop();
  }

  /**
   * Register an access. Listen to onclick event on button.
   *
   * @param view The button
   */
  public void register(View view) {

    // Get selected porteria.
    RadioGroup groupPorteria = findViewById(R.id.porteria_group);
    Porteria porteria;

    switch (groupPorteria.getCheckedRadioButtonId()) {
      case R.id.porteria_0:
        porteria = Porteria.valueOf(0);
        break;
      case R.id.porteria_1:
        porteria = Porteria.valueOf(1);
        break;
      case R.id.porteria_2:
        porteria = Porteria.valueOf(2);
        break;
      default:
        porteria = Porteria.valueOf(0);
        break;

    }

    // Start connection
    zeroIce.start();
    // Try to register
    try {
      zeroIce.contratosPrx.registrarAcceso(MainActivity.EXTRA_PATENTE, porteria);
      Toast.makeText(getApplicationContext(), "Acceso registrado", Toast.LENGTH_SHORT).show();
    } catch (VehicleException ve) {
      log.error("Vehicle not found!", ve);
      Toast.makeText(getApplicationContext(), "Error al registrar el acceso.", Toast.LENGTH_SHORT).show();
    } finally {
      zeroIce.stop();
    }


  }

  /**
   * Fill the layout with the data of Vehiculo.
   *
   * @param v The vehicle.
   */
  private void fillVehiculoView(Vehiculo v) {

    // Load the textView
    TextView patente = findViewById(R.id.patente_field);
    TextView marca = findViewById(R.id.marca_field);
    TextView modelo = findViewById(R.id.modelo_field);
    TextView anio = findViewById(R.id.anio_field);
    TextView color = findViewById(R.id.color_field);
    TextView observaciones = findViewById(R.id.observaciones_field);

    // Fill the textView
    patente.setText(v.patente);
    marca.setText(v.marca);
    modelo.setText(v.modelo);
    anio.setText(v.anio);
    color.setText(v.color);
    observaciones.setText(v.observacion);
  }

  /**
   * Fill the layout with the data of Persona
   *
   * @param p The person.
   */
  private void fillPersonaView(Persona p) {

    // Load the textView
    TextView rut = findViewById(R.id.rut_field);
    TextView nombre = findViewById(R.id.nombre_field);
    TextView genero = findViewById(R.id.genero_field);
    TextView correo = findViewById(R.id.correo_field);
    TextView fono = findViewById(R.id.fono_field);
    TextView movil = findViewById(R.id.movil_field);
    TextView unidad = findViewById(R.id.unidad_field);
    TextView rol = findViewById(R.id.rol_field);

    // Fill the textView
    rut.setText(p.rut);
    nombre.setText(p.nombre);
    genero.setText(p.genero.toString());
    correo.setText(p.email);
    fono.setText(p.fono);
    movil.setText(p.movil);
    unidad.setText(p.unidadAcademica);
    rol.setText(p.rol.toString());

  }

}