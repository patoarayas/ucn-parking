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

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Scrapper Class.
 */
public class Scrapper {

  /**
   * Logger.
   */
  private static Logger log = LoggerFactory.getLogger(Scrapper.class);

  /**
   * Website info.
   */
  private static final String URL = "http://online.ucn.cl/directoriotelefonicoemail/fichaGenerica/?cod=";

  /**
   * Main method.
   */
  public static void main(String[] args) {

    // Setup DB.
    String dbUrl = "jdbc:sqlite:directorio.db";
    Dao<Contact, Integer> contactDao = null;

    // Starts the connection with the DB.
    try (final ConnectionSource connectionSource = new JdbcConnectionSource(dbUrl)) {
      log.debug("DB connection open");

      // Creates the DAO.
      contactDao = DaoManager.createDao(connectionSource, Contact.class);

      // Creates the tables.
      TableUtils.createTableIfNotExists(connectionSource, Contact.class);

    } catch (SQLException | IOException e) {
      log.error("Error creating a DB connection: {}", e);
    }

    // Searches for the last id inside the DB and starts scrapping from it.
    int lastId = 0;
    try {
      assert contactDao != null;

      // Gets the max id-value from the DB.
      String strId = contactDao
          .queryRaw("select max(id) from contactos").getFirstResult()[0];

      try {
        // Parses into an int.
        lastId = Integer.parseInt(strId);

      } catch (Exception e) {
        log.error("Error parsing id. Empty db?: {}", e.getMessage());
      }
      log.info("Starting scrapping from ID: {}", lastId);

    } catch (SQLException e) {
      log.error("Error getting last inserted id: {}", e);
    }

    // Gets the contact's info by each ID and then creates it.
    int maxId = 30000;
    for (int id = lastId; id <= maxId; id++) {
      log.info("Getting contact's id: {}", id);

      // Contact's info.
      Contact contact = getContactInfo(id);

      if (contact != null) {
        try {
          contactDao.create(contact);

        } catch (SQLException e) {
          log.debug("Duplicated entry.");
        }
      }

      try {
        // Random class.
        Random random = new Random();

        // Set a delay.
        int delay = 1000 + random.nextInt(500);
        Thread.sleep(delay);
        log.info("Delay: {}", delay);

      } catch (InterruptedException e) {
        log.debug("Delay was interrupted: {}", e.getMessage());
      }
    }
  }

  /**
   * Gets the contact's info from UCN phone directory website.
   *
   * @param id The id to search for.
   * @return A Contact with the information or null if there isn't any.
   */
  private static Contact getContactInfo(Integer id) {
    Contact newContact = null;

    try {
      // Connection with the website.
      Document doc = Jsoup.connect(URL + id).get();

      // Exceptions.
      newContact = throwingExceptions(id, doc);

    } catch (IOException e) {
      log.error("Error retrieving contact info: {}", e);
    }

    return newContact;
  }

  /**
   * Exceptions from the contacts directory.
   *
   * @param id The id to search for.
   * @param document Jsoup functionality.
   * @return A Contact with the information or null if there isn't any.
   */
  private static Contact throwingExceptions(Integer id, Document document) {

    String aux = "None";

    // Gets the contact's information.
    String name = document.getElementById("lblNombre").text();
    String position = document.getElementById("lblCargo").text();
    String unit = document.getElementById("lblUnidad").text();
    String email = document.getElementById("lblEmail").text();
    String phone = document.getElementById("lblTelefono").text();
    String office = document.getElementById("lblOficina").text();

    // Takes this data and divide it into address and city.
    String data = document.getElementById("lblDireccion").text();
    String address;
    String city;

    // If the contact's name exists, then instantiates it.
    if (!name.isEmpty()) {

      if (position.isEmpty()) {
        position = aux;
      }

      if (email.isEmpty()) {
        email = aux;
      }

      if (phone.isEmpty()) {
        phone = aux;
      }

      if (office.isEmpty()) {
        office = aux;
      }

      if (!data.isEmpty()) {
        address = data.substring(0, data.indexOf(","));
        city = data.substring(data.indexOf(",") + 2);

      } else {
        address = aux;
        city = aux;
      }

      // Return the contact.
      return new Contact(id, name, position, unit, email, phone, office, address, city);

    } else {
      return null;
    }
  }
}
