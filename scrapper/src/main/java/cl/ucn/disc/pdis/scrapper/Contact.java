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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Contact class (ORMLite its added here to build the database).
 */
@DatabaseTable(tableName = "contactos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {

  /**
   * Numeric Cod (Not correlative). Cod provided by UCN's website.
   */
  @DatabaseField(id = true)
  @Getter
  @Setter
  private Integer cod;

  /**
   * Contact's Name.
   */
  @DatabaseField
  @Getter
  @Setter
  private String name;

  /**
   * Contact's Rut.
   */
  @DatabaseField
  @Getter
  @Setter
  private String rut;

  /**
   * Contact's Gender.
   */
  @DatabaseField
  @Getter
  @Setter
  private String gender;

  /**
   * Contact's Position.
   */
  @DatabaseField
  @Getter
  @Setter
  private String position;

  /**
   * Contact's Unit.
   **/
  @DatabaseField
  @Getter
  @Setter
  private String unit;

  /**
   * Contact's E-Mail.
   */
  @DatabaseField
  @Getter
  @Setter
  private String email;

  /**
   * Contact's Phone.
   */
  @DatabaseField
  @Getter
  @Setter
  private String phone;

  /**
   * Contact's Office.
   */
  @DatabaseField
  @Getter
  @Setter
  private String office;

  /**
   * Contact's Address (workplace or university campus).
   */
  @DatabaseField
  @Getter
  @Setter
  private String address;

  /**
   * Contact's City.
   */
  @DatabaseField
  @Getter
  @Setter
  private String city;

  /**
   * toString method via reflection.
   *
   * @return - Object's attributes.
   */
  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
