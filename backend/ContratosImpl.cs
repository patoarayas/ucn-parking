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

using System;
using Ice;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Parking.ZeroIce.model;

namespace backend
{
    /// <summary>
    /// Implements Contratos interface from Zero Ice.
    /// </summary>
    public class ContratosImpl : ContratosDisp_
    {
        /// <summary>
        /// Logger
        /// </summary>
        private readonly ILogger<SistemaImpl> _logger;

        /// <summary>
        /// IServiceScope, provider of DbContext.
        /// </summary>
        private readonly IServiceScopeFactory _serviceScopeFactory;

        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="logger">Logger DI</param>
        public ContratosImpl(ILogger<SistemaImpl> logger, IServiceScopeFactory serviceScopeFactory)
        {
            _logger = logger;
            _logger.LogDebug("Building ContratosImpl");

            // Database
            _serviceScopeFactory = serviceScopeFactory;
            _logger.LogDebug("Connecting to DB");

            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                pc.Database.EnsureCreated();
                pc.SaveChanges();
            }

            _logger.LogDebug("Done");
        }

        /// <summary>
        /// Register an access to the the UCN
        /// </summary>
        /// <param name="patente">patente of the vehicle</param>
        /// <param name="porteria">Access gate</param>
        /// <param name="current">.</param>
        /// <returns>The access</returns>
        /// <exception cref="VehicleNotFoundException">If the vehicle is not in the db</exception>
        public override Acceso registrarAcceso(string patente, Porteria porteria, Current current = null)
        {
            _logger.LogDebug("RegistrarAcceso request received.");

            // Check if the patente exist in db
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();

                if (pc.Vehiculos.Find(patente) == null)
                {
                    _logger.LogError("Invalid vehicle: " + patente);
                    throw new VehicleException("Vehicle not found on db");
                }

                // Vehicle found, create access
                var ac = new Acceso
                {
                    patente = patente,
                    porteria = porteria,
                    // dd/mm/yy hh:mm:ss
                    horaEntrada = DateTime.Now.ToString("G")
                };

                pc.Accesos.Add(ac);
                _logger.LogInformation("New access: "+ac.patente +" at: "+ac.porteria);
                return ac;
            }
        }

        /// <summary>
        /// Register a new Person in the db
        /// </summary>
        /// <param name="persona">Persona data</param>
        /// <param name="current">.</param>
        public override void registrarPersona(Persona persona, Current current = null)
        {
            _logger.LogDebug("RegistrarPersona request received.");
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();

                // Check if already is a persona with the same rut in db
                if (pc.Personas.Find(persona.rut) != null)
                {
                    _logger.LogDebug("Persona already on DB");
                    throw new PersonaException("Rut is already registered on the db.");
                }

                pc.Personas.Add(persona);
                pc.SaveChanges();
            }
        }

        /// <summary>
        /// Register a new vehicle in the db
        /// </summary>
        /// <param name="vehiculo">Vehicle data</param>
        /// <param name="current">.</param>
        public override void registrarVehiculo(Vehiculo vehiculo, Current current = null)
        {
            _logger.LogDebug("RegistrarVehiculo request received.");
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();

                // Check if already is a vehcile with the same patente in the db
                if (pc.Personas.Find(vehiculo.patente) != null)
                {
                    _logger.LogDebug("Vehicle's patente already on DB");
                    throw new VehicleException("This vehicle patente is already registered on the db.");
                }

                pc.Vehiculos.Add(vehiculo);
                pc.SaveChanges();
            }
        }

        /// <summary>
        /// Find a Persona in the db by its rut.
        /// </summary>
        /// <param name="rut">Persona's rut</param>
        /// <param name="current">.</param>
        /// <returns>A Persona</returns>
        /// <exception cref="PersonaException">If the Persona wasn't found</exception>
        public override Persona findPersonaByRut(string rut, Current current = null)
        {
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                _logger.LogDebug("Searching for Persona with rut: " + rut);
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                var persona = pc.Personas.Find(rut);

                if (persona != null)
                {
                    _logger.LogDebug("Persona found!");
                    return persona;
                }

                else
                {
                    _logger.LogDebug("Persona not found!");
                    throw new PersonaException("Persona not found");
                }
            }
        }

        /// <summary>
        /// Find a Vehiculo in the db by its patente.
        /// </summary>
        /// <param name="patente">Vehiculo's patente</param>
        /// <param name="current">.</param>
        /// <returns>A Vehiculo</returns>
        /// <exception cref="VehicleException">If the Vehiculo wasn't found</exception>
        public override Vehiculo findVehiculoByPatente(string patente, Current current = null)
        {
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                _logger.LogDebug("Searching for Vehiculo with patente: " + patente);
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                var vehiculo = pc.Vehiculos.Find(patente);

                if (vehiculo != null)
                {
                    _logger.LogDebug("Vehiculo found!");
                    return vehiculo;
                }

                else
                {
                    _logger.LogDebug("Vehiculo not found!");
                    throw new VehicleException("Vehiculo not found");
                }
            }
        }
    }
}