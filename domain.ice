/*
 * MIT License
 *
 * Copyright (c) 2020 Patricio Araya, David canto, Ariel Vejar
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

["cs:namespace:Parking.ZeroIce"]
module model {

     /**
     * Clase Persona
     */
     ["cs:property"]
     class Persona{

        /** Codigo identificador*/
        int uid;
        /** Nombre */
        string nombre;
        /** Rut */
        string rut;
        /** Sexo */
        string genero;
        /** Email */
        string email;
        /** Telefono */
        string fono;
     }

     /**
     *  Vehiculo
     */
     ["cs:property"]
     class Vehiculo{

         /** Patente Vehicular*/
         string patente;
         /** Marca del vehiculo**/
         string marca;
         /** Modelo del vehiculo **/
         string modelo;
         /** Año del vehiculo**/
         int anio;
         /** Observaciónes **/
         string observacion;
         /** Logo TODO: Verificar atributos del logo o separar en entidad aparte**/
         string logo;

     }

     /**
     * Registro de acceso
     */
     ["cs:property"]
     class Acceso {

        /** Id */
        int uid;
        /** Timestamp*/
        string hora;
        /** Patente vehicular */
        string patente;
        /** Puerta de acceso (Porteria) */
        string porteria;
     }


     // Sequences
     sequence<Persona> Personas;
     sequence<Vehiculo> Vehiculos;
     sequence<Acceso> Accesos;
     /**
     * Operaciones del sistema
     */
     interface Sistema {

        /**
        * Crea una persona en el sistema
        * @param Persona: Persona a crear
        * @return Persona creada
        */
        Persona createPersona(Persona persona);

        /**
        * Crea un vehiculo en el sistema
        * @param Vehiculo: vehiculo a crear
        * @return Vehiculo creado
        */
        Vehiculo createVehiculo(Vehiculo vehiculo);

        /**
        * Elimina una persona del sistema
        * @param rut: rut de la persona a eliminar
        * @return bool: eliminación exitosa o no
        */
        bool deletePersona(string rut);

        /**
        * Elimina un vehiculo del sistema
        * @param patente: patente del vehiculo a eliminar
        * @return bool: eliminación exitosa o no
        */
        bool deleteVehiculo(string patente);

        /**
        * Actualiza la información de una persona en el sistema
        * @param Persona: persona actualizada
        * @return bool: actualizacion exitosa o no
        */
        bool updatePersona(Persona persona);

        /**
        * Actualiza la información de un vehiculo
        * @param Vehiculo: Vehiculo actualizado
        * @return bool: actualizacion exitosa o no
        */
        bool updateVehiculo(Vehiculo vehiculo);

        /**
        * Mostrar personas registradas en el sistema
        * @return Sequence con las personas registrados en el sistema
        */
        Personas showPersonas();

        /**
        * Mostrar vehiculos registrados en el sistema
        * @return Sequence con los vehiculos registrados en el sistema
        */
        Vehiculos showVehiculos();

        /**
        * Mostrar registro de accesos en el sistema
        * @return Sequence con los registros de acceso.
        */
        Accesos showAccesos();

        /**
        * Envia datos del registro.
        * @param patente: patente del vehiculo a eliminar
        * @return bool: registro exitosa o no.
        */
        bool registrarAcceso(string timestamp, string porteria, string patente);
        }
}