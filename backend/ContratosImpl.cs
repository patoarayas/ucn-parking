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
            
            // Db
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
            // Check if the patente exist on db
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                if (pc.Vehiculos.Find(patente) == null)
                {
                    _logger.LogError("Invalid vehicle: "+patente);
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
                _logger.LogInformation("New access: ",ac.ToString());
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
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                
                // TODO: Validation, if not throws PersonaException
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
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                
                // TODO: Validation, if not throws VehicleException
                pc.Vehiculos.Add(vehiculo);
                pc.SaveChanges();
            }
        }
    }
}