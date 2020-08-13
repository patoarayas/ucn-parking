/*
 * The MIT License (MIT)
 * Copyright (c) 2020 Patricio Araya, David Canto, Ariel Vejar
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

import cl.ucn.disc.pdis.scrapper.Contact;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ScrapperTestNRF {

  /**
   * Logger.
   */
  final Logger log = LoggerFactory.getLogger(ScrapperTestNRF.class);

  /**
   * URL
   */
  final String URL = "https://www.nombrerutyfirma.com/buscar";

  /**
   * Test-A (No null values applied)
   */
  @Test
  public void scrapperA() throws IOException {

    log.debug("Scrapper testing A ...");

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

    Assertions.assertEquals(cod, contact.getCodigo());
    Assertions.assertEquals(name, contact.getName());
    Assertions.assertEquals(email, contact.getEmail());
    Assertions.assertNotNull(contact.getRut());
    Assertions.assertNotNull(contact.getGender());

    log.debug("DATA = {}", contact.toString());
    log.debug("Done.");
  }

  /**
   * Test-B (Null values applied).
   */
  @Test
  public void scrapperB() throws IOException {

    log.debug("Scrapper testing B ...");

    final Integer cod = 238;
    final String name = "Jaime Patricio Gonzalez Gonzalez";
    final String position = "Jefe de Oficina Area de Mar";
    final String unit = "Facultad de Ciencias del Mar";
    final String email = "pg@ucn.cl";
    final String phone = "Fono (51) 2209740";
    final String office = "EDIF. S2 /";
    final String address = "Larrondo N° 1281, Coquimbo - Chile";

    final Contact contact = getData(
        new Contact(cod, name, position, unit, email, phone, office, address)
    );

    Assertions.assertEquals(cod, contact.getCodigo());
    Assertions.assertEquals(name, contact.getName());
    Assertions.assertEquals(email, contact.getEmail());
    Assertions.assertNull(contact.getRut());
    Assertions.assertNull(contact.getGender());

    log.debug("DATA = {}", contact.toString());
    log.debug("Done.");
  }

  /**
   * Data obtained from NRF website.
   */
  private Contact getData(Contact contact) throws IOException {

    Document document = Jsoup.connect(URL)
        .data("term", contact.getName())
        .referrer("https://www.nombrerutyfirma.com")
        .post();

    final Element table = document.select("table").get(0);
    final Elements rows = table.select("tbody").select("tr");

    if (rows.size() == 1) {
      Elements cols = rows.first().select("td");
      contact.setRut(cols.get(1).text());
      contact.setGender(cols.get(2).text());
    }

    return contact;
  }
}
