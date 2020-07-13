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

package cl.ucn.disc.pdis.scrapper;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Scrapper Class.
 */
@Slf4j
public class Scrapper {

  /**
   * URL.
   */
  private static String URL;

  /**
   * Document.
   */
  private static Document document;

  /**
   * Gets the contact's info.
   *
   * @param id - The id.
   * @return - The contact data.
   */
  public static Contact getData(int id) throws IOException {
    return scrapperUCN(id);
  }

  /**
   * Gets the contact's info from the 'UCN' website.
   *
   * @param id - The id to search for.
   * @return - A Contact object.
   */
  private static Contact scrapperUCN(Integer id) throws IOException {
    Contact contact = null;

    // URL.
    URL = "http://online.ucn.cl/directoriotelefonicoemail/fichaGenerica/?cod=";

    // Website connection.
    document = Jsoup.connect(URL + id).get();

    // Element's values.
    String[] elements = {"lblNombre", "lblCargo", "lblUnidad", "lblEmail",
        "lblTelefono", "lblOficina", "lblDireccion"};

    // Gets the contact's information.
    String[] data = new String[elements.length];
    for(int i = 0; i < elements.length; i++) {
      data[i] = document.getElementById(elements[i]).text();
    }

    if (!data[0].isEmpty()) {

      contact = scrapperNRF(
          new Contact(
              id,
              data[0],      // Name
              null,     // Rut
              null,  // Gender
              data[1],  // Position
              data[2],  // Unit
              data[3],  // Email
              data[4],  // Phone
              data[5],  // Office
              data[6],  // Address
              null  // City
          )
      );

      log.debug(contact.toString());
    }

    // Exceptions.
    return exceptions(contact);
  }

  /**
   * Gets the contact's rut and gender from the 'nombrerutyfirma' website.
   *
   * @param contact - The name to search for.
   * @return - The contact with the information appended.
   */
  private static Contact scrapperNRF(Contact contact) throws IOException {

    // URL.
    URL = "https://www.nombrerutyfirma.com/buscar";

    document = Jsoup.connect(URL)
        .data("term", contact.getName())
        .referrer("https://www.nombrerutyfirma.com")
        .post();

    final Element table = document.select("table").get(0);
    final Elements rows = table.select("tbody").select("tr");

    log.debug("[RUT] - Matches founded {}: ", rows.size());

    // Only fill if 1 match is found.
    if (rows.size() == 1) {
      Elements cols = rows.first().select("td");
      contact.setRut(cols.get(1).text());
      contact.setGender(cols.get(2).text());
    }

    if (rows.size() > 1) {
      log.warn("[RUT] - Several matches found.");

    } else if (rows.size() == 0) {
      log.warn("[RUT] - No matches found");
    }

    return contact;
  }

  /**
   * Exceptions.
   *
   * @param contact - The contact object.
   * @return - The new contact object.
   */
  private static Contact exceptions(Contact contact) {
    if(contact != null) {

      if (contact.getGender() != null) {
        if (contact.getGender().equals("VAR")) {
          contact.setGender("MASCULINO");

        } else if (contact.getGender().equals("MUJ")) {
          contact.setGender("FEMENINO");
        }
      }

      if (contact.getPosition().isEmpty()) {
        contact.setPosition(null);
      }

      if (contact.getUnit().isEmpty()) {
        contact.setUnit(null);
      }

      if (contact.getEmail().isEmpty()) {
        contact.setEmail(null);
      }

      if (contact.getPhone().isEmpty()) {
        contact.setPhone(null);
      }

      if (contact.getOffice().isEmpty()) {
        contact.setOffice(null);
      }

      if (!contact.getAddress().isEmpty()) {
        contact.setAddress(contact.getAddress().split("[-]")[0].trim());
        contact.setCity(contact.getAddress().split("[,-]")[1].trim());

      } else {
        contact.setAddress(null);
      }
    }

    return contact;
  }
}
