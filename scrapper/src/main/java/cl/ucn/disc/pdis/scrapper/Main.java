package cl.ucn.disc.pdis.scrapper;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

/**
 * Main class.
 */
@Slf4j
public class Main {

  /**
   * Database.
   */
  private static final String dbUrl = "jdbc:sqlite:directorio.db";

  /**
   * Max id (40000).
   */
  private static final int maxID = 40000;

  /**
   * Main method.
   */
  public static void main(String[] args) throws IOException {

    // Database Access Object.
    Dao<Contact, Integer> dao = null;

    // Database connection.
    try (final ConnectionSource connectionSource = new JdbcConnectionSource(dbUrl)) {
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
    for (int id = lastId; id <= maxID; id++) {
      log.debug("Testing contact's id = {} ...", id);

      // Contact's info.
      Contact contact = Scrapper.getData(id);

      if (contact != null) {
        try {
          dao.create(contact);

        } catch (SQLException e) {
          log.debug("Duplicated entry = {}", e);
        }
      }

      // Delay.
      sleep();
    }
  }

  /**
   * Searches for the last id inside the database and starts scrapping from it.
   *
   * @param dao - The DAO that handles the class from the database.
   * @return - The id.
   */
  private static int getLastId(Dao<Contact, Integer> dao) {
    int id = 0;

    try {
      assert dao != null;

      // Gets the max id-value from the database.
      String strId = dao
          .queryRaw("SELECT MAX(cod) FROM contactos")
          .getFirstResult()[0];

      // Parses into an int.
      id = Integer.parseInt(strId);

    } catch (NumberFormatException | SQLException e) {
      log.error("Error getting last inserted id: ", e.getMessage());
    }

    return id;
  }

  /**
   * Set a random delay.
   */
  private static void sleep() {
    // Random class.
    Random random = new Random();

    try {
      // Delay.
      int delay = 1000 + random.nextInt(500);
      Thread.sleep(delay);
      log.info("Delay: {}", delay);

    } catch (InterruptedException e) {
      log.debug("Delay was interrupted: {}", e.getMessage());
    }
  }
}
