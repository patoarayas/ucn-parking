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
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Model Class Contact.
 * Represents the contact information of a academic or employee.
 * This class is used by ORMLite to build the DB.
 */
@DatabaseTable(tableName = "contactos")
public class Contact {

  /**
   * Numeric id. Uses the one provided by UCN's web directory (Not correlative).
   */
  @DatabaseField(id = true)
  private Integer cod;

  /**
   * Contact's Name.
   */
  @DatabaseField
  private String name;

  /**
   * Contact's Position.
   */
  @DatabaseField
  private String position;

  /**
   * Contact's Unit.
   **/
  @DatabaseField
  private String unit;

  /**
   * Contact's E-Mail.
   */
  @DatabaseField
  private String email;

  /**
   * Contact's Phone.
   */
  @DatabaseField
  private String phone;

  /**
   * Contact's Office.
   */
  @DatabaseField
  private String office;

  /**
   * Contact's Address (workplace or university campus).
   */
  @DatabaseField
  private String address;

  /**
   * Contact's City.
   */
  @DatabaseField
  private String city;

  /**
   * ORMlite constructor.
   */
  public Contact() {
    // ORM lite needs an no-arg constructor.
  }

  /**
   * Constructor.
   */
  public Contact(Integer cod, String name, String position, String unit, String email,
                 String phone, String office, String address, String city) {
    this.cod = cod;
    this.name = name;
    this.position = position;
    this.unit = unit;
    this.email = email;
    this.phone = phone;
    this.office = office;
    this.address = address;
    this.city = city;
  }

  /**
   * Exceptions from the contacts directory.
   *
   * @param contact The contacts info.
   * @return The new contacts.
   */
  public Contact throwingExceptions(Contact contact) {
    if(!contact.name.isEmpty()) {

      if(contact.position.isEmpty()) { position = null; }

      if(contact.unit.isEmpty()) { unit = null; }

      if(contact.email.isEmpty()) { email = null; }

      if(contact.phone.isEmpty()) { phone = null; }

      if(contact.office.isEmpty()) { office = null; }

      if(!contact.address.isEmpty() && !contact.city.isEmpty()) {
        address = address.substring(0, contact.address.indexOf(","));
        city = city.substring(contact.city.indexOf(",") + 2);

      } else {
        address = null;
        city = null;
      }

      return contact;
    }
    return null;
  }

  /**
   * Determines the fields to append.
   *
   * @return
   */
  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
