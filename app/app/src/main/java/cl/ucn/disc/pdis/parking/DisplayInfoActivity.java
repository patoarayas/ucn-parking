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
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.ucn.disc.pdis.parking.zeroice.model.Persona;
import cl.ucn.disc.pdis.parking.zeroice.model.PersonaException;
import cl.ucn.disc.pdis.parking.zeroice.model.VehicleException;
import cl.ucn.disc.pdis.parking.zeroice.model.Vehiculo;

public class DisplayInfoActivity extends AppCompatActivity {

    // Logger
    private static final Logger log = LoggerFactory.getLogger(DisplayInfoActivity.class);
    // Zero Ice
    private final ZeroIce zeroIce = ZeroIce.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);

        Intent intent = getIntent();
        String patente = intent.getStringExtra(MainActivity.EXTRA_PATENTE);

        // Todo: refactor
        Vehiculo vehiculo = getVehiculo(patente);
        Persona persona = getPersona(vehiculo.rut);

        TextView patente_field = findViewById(R.id.patente_field);
        patente_field.setText(patente);
    }

    private Vehiculo getVehiculo(String patente){

        zeroIce.start();
        try {
            return zeroIce.contratosPrx.findVehiculoByPatente(patente);
        } catch (VehicleException ve){
            log.error("Error retrieving vehicle.",ve);
        }
        zeroIce.stop();
        return null;
    }

    private Persona getPersona(String rut){

        zeroIce.start();
        try {
            return zeroIce.contratosPrx.findPersonaByRut(rut);
        } catch (PersonaException pe){
            log.error("Error retrieving persona.",pe);
        }
        zeroIce.stop();
        return null;
    }

}