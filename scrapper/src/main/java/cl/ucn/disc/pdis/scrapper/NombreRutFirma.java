package cl.ucn.disc.pdis.scrapper;

import lombok.Builder;
import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Scrapper class from NombreRutyFirma's website.
 */
@Builder
public class NombreRutFirma {

  private static final String URL = "https://www.nombrerutyfirma.com/buscar";

  /**
   * Name.
   */
  @Getter
  private String name;

  /**
   * Rut.
   */
  @Getter
  private String rut;

  /**
   * Gender.
   */
  @Getter
  private String gender;

  /**
   * Address.
   */
  @Getter
  private String address;

  /**
   * City.
   */
  @Getter
  private String city;

  /**
   * Scrapper method.
   *
   * @param term The String to search.
   * @return A NombreRutFirma object.
   * @throws IOException
   */
  public static NombreRutFirma scrapper(String term) throws IOException {

    NombreRutFirma nombreRutFirma = null;

    Document document = Jsoup.connect(URL)
        .data("term", term)
        .referrer("https://www.nombrerutyfirma.com")
        .post();

    final Element table = document.select("table").get(0);
    final Elements rows = table.select("tbody").select("tr");

    for (final Element row : rows) {
      Elements cols = row.select("td");

      final String name = cols.get(0).text();
      final String rut = cols.get(1).text();
      final String gender = cols.get(2).text();
      final String address = cols.get(3).text();
      final String city = cols.get(4).text();

      nombreRutFirma = new NombreRutFirma(name, rut, gender, address, city);
    }

    return nombreRutFirma;
  }
}
