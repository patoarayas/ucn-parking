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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cl.ucn.disc.pdis.parking.zeroice.model.Persona;


/**
 * Repository for Personas
 */
public class PersonasRepository {

  // Logger
  private static final Logger log = LoggerFactory.getLogger(PersonasRepository.class);

  // Instance
  private static PersonasRepository PERSONAS_REPOSITORY = new PersonasRepository();

  // List of personas
  private List<Persona> personasList;

  /**
   * Empty constructor
   */
  private PersonasRepository(){
    // Empty constructor
  }

  public static PersonasRepository getInstance(){
    if(PERSONAS_REPOSITORY == null){
      PERSONAS_REPOSITORY = new PersonasRepository();
    }
    PERSONAS_REPOSITORY.getPersonas();
    return PERSONAS_REPOSITORY;
  }

  /**
   * Get personas from the backend.
   */
  private void getPersonas(){
    ZeroIce zeroIce = ZeroIce.getInstance();
    zeroIce.start();

    personasList = new ArrayList<Persona>();
    personasList.addAll(Arrays.asList(zeroIce.sistemaPrx.getPersonas()));

    zeroIce.stop();

  }

  /**
   * Fetch a Persona by its rut
   * @param rut: Persona's rut
   * @return Persona or null
   */
  public Persona getPersonaByRut(String rut){
    for (Persona p: personasList) {
      if(p.rut.equals(rut)){
        return p;
      }
    }
    return null;
  }



}
