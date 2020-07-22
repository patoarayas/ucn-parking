/*
 * The MIT License (MIT)
 * Copyright (c) 2020 Patricio Araya, David Canto, Ariel Vejar
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the “Software”), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

import cl.ucn.disc.pdis.scrapper.Contact;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Testing Class.
 */
public class ContactTest {

  /**
   * Logger.
   */
  final Logger log = LoggerFactory.getLogger(ContactTest.class);

  /**
   * Test.
   */
  @Test
  public void testConstructor() {

    log.debug("Constructor testing ...");

    final Integer cod = 25773;
    final String name = "Diego Patricio Urrutia Astorga";
    final String position = "Academico";
    final String unit = "Facultad de Ingeniería y Ciencias Geológicas";
    final String email = "durrutia@ucn.cl";
    final String phone = "Fono (55) 2355163";
    final String office = "Pab. Y-1";
    final String address = "Avenida Angamos 0610, Antofagasta - Chile";

    final Contact contact = getData(
        new Contact(cod, name, position, unit, email, phone, office, address)
    );

    Assertions.assertEquals(cod, contact.getCod());
    Assertions.assertEquals(name, contact.getName());
    Assertions.assertEquals(email, contact.getEmail());
    Assertions.assertEquals("13.014.491-8", contact.getRut());
    Assertions.assertEquals("VAR", contact.getGender());

    log.debug("DATA = {}", contact.toString());
    log.debug("Done.");
  }

  /**
   * Test.
   */
  public Contact getData(Contact contact) {

    final String rut = "13.014.491-8";
    final String gender = "VAR";

    contact.setRut(rut);
    contact.setGender(gender);

    return contact;
  }
}
