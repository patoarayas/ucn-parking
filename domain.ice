  
/*
 * MIT License
 *
 * Copyright (c) 2020 Patricio Araya González <patricio.araya@alumnos.ucn.cl>
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

// https://doc.zeroc.com/ice/3.7/language-mappings/java-mapping/client-side-slice-to-java-mapping/customizing-the-java-mapping
["java:package:cl.ucn.disc.pdis.fivet.zeroice", "cs:namespace:Fivet.ZeroIce"]
module model {

     /**
     * Contact
     */
     ["cs:property"]
     class Persona{
         
        /** Codigo identificador*/
        //int cod;
        /** Nombre */
        string name;
        /** Rut */
        string rut;
        /** Sexo */
        //string gender;
        /** Position */
        //string position;
        /** Unit */
        //string unit;
        /** Email */
        //string email;
        /** Telefono */
        //String phone
        /** Oficina */
        //String office;
        /** Dirección */
        //String address;
        /** Ciudad*/
        //String city;

     }

     /**
     *  Vehiculo
     */
     ["cs:property"]
     class Vehiculo{

         /** Identificador numérico */
         //int uid;
         /** Patente Vehicular*/
         string patent;

     }

     /**
     * Registro de acceso
     */
     ["cs:property"]
     class Acceso{

        /** Id */
        //int uid;
        /** Timestamp*/
        string timestamp;
        /** Patente vehicular */
        string patent;
        /** Puerta de acceso (Porteria) */
        string accessGate;

     }

     /**
     * Cruds del sistema
     */
     interface Cruds {

         /**
        * Crea una persona en el sistema
        * @param Persona: Persona a crear
        * @return Persona creada
        */
        Persona createPersona(Persona);
        
         /**
        * Crea un vehiculo en el sistema
        * @param Vehiculo: vehiculo a crear
        * @return Vehiculo creado
        */
        Vehiculo createVehiculo(Vehiculo);

         /**
        * Crea un vehiculo en el sistema
        * @param Vehiculo: vehiculo a crear
        * @return Vehiculo creado
        */

         /**
        * Elimina una persona del sistema
        * @param rut: rut de la persona a eliminar
        * @return bool: eliminación exitosa o no
        */
        bool deletePersona(rut);

         /**
        * Elimina un vehiculo del sistema
        * @param patente: patente del vehiculo a eliminar
        * @return bool: eliminación exitosa o no
        */
        bool deleteVehiculo(patente);

         /**
        * Actualiza una persona en el sistema
        * @param Persona: persona actualizada
        * @return bool: actualizacion exitosa o no
        */
        bool updatePersona(Persona);

         /**
        * Actualiza un vehiculo
        * @param Vehiculo: vehiculo actualizado
        * @return bool: actualizacion exitosa o no
        */
        updateVehiculo(Vehiculo);

         /**
        * Mostrar personas registradas en el sistema
        * @param
        * @return
        */
        showPersonas();

         /**
        * Mostrar vehiculos registrados en el sistema
        * @param
        * @return
        */
        showVehiculos();

         /**
        * Mostrar registro de accesos en el sistema
        * @param
        * @return
        */
        showAccesos();

     }

     interface Operacion {

          /**
        * Recibe datos en el backend para guardarlos en el movil
        * @param
        * @return
        */
        getDatos() // Recibe los datos en el backend para guardarlos en el movil

          /**
        * Envia datos del registro
        * @param patente: patente del vehiculo a eliminar
        * @return bool: eliminación exitosa o no
        */
        registrarAcceso(timestamp, portería, patente) // Envia los datos del registro
     }
}