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

package cl.ucn.disc.pdis.scrapper;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

/**
 * Scrapper Class.
 */
@Slf4j
public class Scrapper {

  /**
   * Database setup.
   */
  private static final String dbUrl = "jdbc:sqlite:directorio.db";

  /**
   * DirectorioUCN.
   */
  private static DirectorioUCN directorioUCN;

  /**
   * NombreRutFirma.
   */
  private static NombreRutFirma nombreRutFirma;

  /**
   * Main method.
   */
  public static void main(String[] args) throws IOException {

    Dao<Contact, Integer> dao = null;

    // Database connection.
    try (final ConnectionSource connectionSource = new JdbcConnectionSource(dbUrl)) {
      // Starts the connection.
      log.debug("Database connection open ...");

      // Creates the DAO.
      dao = DaoManager.createDao(connectionSource, Contact.class);

      // Creates the tables.
      TableUtils.createTableIfNotExists(connectionSource, Contact.class);

    } catch (SQLException e) {
      log.error("Database connection error = {}", e);
    }

    // Gets the id.
    int lastId = getLastId(dao);

    // Gets the contact's info by each ID and then creates it.
    for (int id = lastId; id <= 30000; id++) {
      log.debug("Testing contact's id = {} ...", id);

      // Contact's info.
      Contact contact = getData(id);

      if (contact != null) {
        try {
          // Adds the row in the database.
          dao.create(contact);

        } catch (SQLException e) {
          log.debug("Duplicated entry");
        }
      }

      // Delay.
      sleep();
    }
  }

  /**
   * Searches for the last id inside the database and starts scrapping from it.
   *
   * @param dao The DAO.
   * @return The ID.
   */
  private static int getLastId(Dao<Contact, Integer> dao) {
    int lastId = 0;

    try {
      assert dao != null;

      // Gets the max id-value from the database.
      String strId = dao
          .queryRaw("SELECT MAX(id) FROM contactos")
          .getFirstResult()[0];

      // Parses into an int.
      lastId = Integer.parseInt(strId);

    } catch (NumberFormatException | SQLException e) {
      log.error("Error getting last inserted id = {}", e);
    }

    return lastId;
  }

  private static Contact getData(int id) {
    Contact contact = null;

    try {
      // Gets the info form the UCN website.
      directorioUCN = DirectorioUCN.scrapper(id);
      String name = directorioUCN.getName();
      String position = directorioUCN.getPosition();
      String unit = directorioUCN.getUnit();
      String email = directorioUCN.getEmail();
      String phone = directorioUCN.getPhone();
      String office = directorioUCN.getOffice();
      String workplaceAddress = directorioUCN.getAddress();
      String workplaceCity = directorioUCN.getCity();

      // Gets the info from the NombreRutFirma website.
      nombreRutFirma = NombreRutFirma.scrapper(name);
      String rut = nombreRutFirma.getRut();
      String gender = nombreRutFirma.getGender();
      String address = nombreRutFirma.getAddress();
      String city = nombreRutFirma.getCity();

      Contact aux = new Contact(id, name, rut, gender, address, city, position, unit, email,
          phone, office, workplaceAddress, workplaceCity);

      // Exceptions.
      contact = aux.throwingExceptions(aux);

    } catch (NullPointerException | IOException e) {
      log.error("Error in getting the contact = {}", e);
    }

    return contact;
  }

  /**
   * Set the delay.
   */
  private static void sleep() {
    // Random class.
    Random random = new Random();

    try {
      // Delay.
      int delay = 1000 + random.nextInt(500);
      Thread.sleep(delay);
      log.debug("Delay = {}", delay);

    } catch (InterruptedException e) {
      log.error("Delay was interrupted = {}", e);
    }
  }
}
