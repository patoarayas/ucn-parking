/*
 * MIT License
 *
 * Copyright (c) 2020 Patricio Araya, David Canto, Ariel Vejar
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

["cs:namespace:Parking.ZeroIce","java:package:cl.ucn.disc.pdis.parking.zeroice"]
module model {

    /**
     * Enums
     */
     enum Genero {MASCULINO, FEMENINO, OTRO}
     enum Rol {ACADEMICO,ESTUDIANTE,FUNCIONARIO}
     enum Porteria {SUR, MANCILLA, SANGRA}

    /**
     * Clase Persona
     */
     ["cs:property"]
     class Persona {

        /** Rut */
        string rut;
        /** Nombre */
        string nombre;
        /** Genero */
        Genero genero;
        /** Email */
        string email;
        /** Telefono */
        string fono;
        /** Telefono movil **/
        string movil;
        /** Unidad académica */
        string unidadAcademica;
        /** Rol dentron de la universidad */
        Rol rol;
     }

    /**
     *  Vehiculo
     */
     ["cs:property"]
     class Vehiculo {

         /** Rut dueño */
         string rut;
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
         /** Color **/
         string color;
     }

     /**
      * Registro de acceso
      */
     ["cs:property"]
     class Acceso {

        /** Id */
        int uid;
        /** Timestamp*/
        string horaEntrada;
        /** Patente vehicular */
        string patente;
        /** Puerta de acceso (Porteria) */
        Porteria porteria;
     }

    /**
      * Exceptions
      */
     exception PersonaException {
        string msg;
     }
     exception VehicleException {
        string msg;
     }

    /**
     * Sequences
     */
     sequence<Persona> Personas;
     sequence<Vehiculo> Vehiculos;
     sequence<Acceso> Accesos;

    /**
     * Operaciones del sistema
     */
     interface Contratos {

        /**
         * Registra un acceso a la universidad.
         * @param patente La patente del vehiculo.
         * @param porteria La portería por la que se realiza el acceso
         * @return Acceso Los datos del acceso
         */
        Acceso registrarAcceso(string patente, Porteria porteria)
             throws VehicleException;

        /**
         * Registrar una persona en el sistema.
         * @param persona Usuario a ser registrado
         * @throws PersonaException
         */
        void registrarPersona(Persona persona)
            throws PersonaException;

        /**
         * Registrar una persona en el sistema.
         * @param persona Usuario a ser registrado
         * @throws VehicleException
         */
        void registrarVehiculo(Vehiculo vehiculo)
            throws VehicleException;

        /**
         * Busca una persona en el sistema por su rut.
         * @param rut rut de la persona
         * @return Persona La persona encontrada
         * @throws PersonaException si no se encuentra la persona
         */
        Persona findPersonaByRut(string rut)
            throws PersonaException;

        /**
         * Busca un vehiculo en el sisteama por su patente.
         * @param patente Patente del vehiculo
         * @return Vehiculo El vehiculo encontrado
         * @throws VehicleException Si no se encuentra el vehiculo
         */
        Vehiculo findVehiculoByPatente(string patente)
            throws VehicleException;
     }

     interface Sistema {

        /**
         * Retorna la información de las personas registradas en el servidor
         * @return Lista de Persona
         */
        Personas getPersonas();

        /**
         * Retorna la información de los vehiculos en el servidor
         * @return Lista de Vehiculos
         */
        Vehiculos getVehiculos();

        /**
         * Retorna los registros de accesos
         * @return Lista de Accesos
         */
         Accesos getAccesos();

        /**
         * @return the diference in time between client and server.
         */
        long getDelay(long clientTime);
        }
}
