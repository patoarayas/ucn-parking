package cl.ucn.disc.pdis.scrapper;

import lombok.Builder;
import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Scrapper class from UCN's website.
 */
@Builder
public class DirectorioUCN {

  private static final String
      URL = "http://online.ucn.cl/directoriotelefonicoemail/fichaGenerica/?cod=";

  /**
   * Name.
   */
  @Getter
  private String name;

  /**
   * Position.
   */
  @Getter
  private String position;

  /**
   * Unit.
   **/
  @Getter
  private String unit;

  /**
   * E-Mail.
   */
  @Getter
  private String email;

  /**
   * Phone.
   */
  @Getter
  private String phone;

  /**
   * Office.
   */
  @Getter
  private String office;

  /**
   * Workplace Address.
   */
  @Getter
  private String address;

  /**
   * Workplace City.
   */
  @Getter
  private String city;

  /**
   * Gets the contact's info from UCN phone directory website.
   *
   * @param id The id to search for.
   * @return A DirectorioUCN object.
   */
  public static DirectorioUCN scrapper(Integer id) throws IOException {

    // Connection with the website.
    Document document = Jsoup.connect(URL + id).get();

    // Gets the contact's information.
    String name = document.getElementById("lblNombre").text();
    String position = document.getElementById("lblCargo").text();
    String unit = document.getElementById("lblUnidad").text();
    String email = document.getElementById("lblEmail").text();
    String phone = document.getElementById("lblTelefono").text();
    String office = document.getElementById("lblOficina").text();

    // This data represent the address and city.
    String data = document.getElementById("lblDireccion").text();

    return new DirectorioUCN(name,position,unit,email,phone,office,data,data);
  }
}
