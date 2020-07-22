import cl.ucn.disc.pdis.scrapper.Contact;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ScrapperTestUCN {

  /**
   * Logger.
   */
  final Logger log = LoggerFactory.getLogger(ScrapperTestUCN.class);

  /**
   * URL.
   */
  final String URL = "http://online.ucn.cl/directoriotelefonicoemail/fichaGenerica/?cod=";

  /**
   * Test.
   */
  @Test
  public void scrapper() throws IOException {

    log.debug("Scrapper testing ...");

    final Integer cod = 142;
    final String name = "Carlos Roberto Pon Soto";
    final String email = "cpon@ucn.cl";

    Contact contact = getData(cod);

    Assertions.assertEquals(cod, contact.getCod());
    Assertions.assertEquals(name, contact.getName());
    Assertions.assertEquals(email, contact.getEmail());

    log.debug("Contact = {}", contact.toString());
  }

  /**
   * Data obtained from UCN website.
   */
  private Contact getData(Integer id) throws IOException {

    Document document = Jsoup.connect(URL + id).get();

    final String[] elements = {"lblNombre", "lblCargo", "lblUnidad", "lblEmail",
        "lblTelefono", "lblOficina", "lblDireccion"};

    String[] data = new String[elements.length];

    for (int i = 0; i < elements.length; i++) {
      data[i] = document.getElementById(elements[i]).text();
    }

    return new Contact(id, data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
  }
}
