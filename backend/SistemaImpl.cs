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
using System.Collections.Generic;
using System.Linq;
using Ice;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Parking.ZeroIce.model;

namespace backend
{
    /// <summary>
    /// Implements Sistema interface from Zero Ice.
    /// </summary>
    public class SistemaImpl : SistemaDisp_
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
        public SistemaImpl(ILogger<SistemaImpl> logger, IServiceScopeFactory serviceScopeFactory)
        {
            _logger = logger;
            _logger.LogDebug("Building SistemaImpl");

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
        /// Return a list with the Personas on the DB.
        /// </summary>
        /// <param name="current">.</param>
        /// <returns>A Personas Array</returns>
        public override Persona[] getPersonas(Current current = null)
        {
            _logger.LogDebug("GetPersonas request received.");
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                return pc.Personas.ToArray();
            }
        }

        /// <summary>
        /// Returns a list with all vehicles on the DB.
        /// </summary>
        /// <param name="current">.</param>
        /// <returns>A Vehiculo Array.</returns>
        public override Vehiculo[] getVehiculos(Current current = null)
        {
            _logger.LogDebug("GetVehiculos request received.");
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                return pc.Vehiculos.ToArray();
            }
        }

        /// <summary>
        /// Returns a list with all the Accesos on the DB.
        /// </summary>
        /// <param name="current">.</param>
        /// <returns>An Accesos Array</returns>
        public override Acceso[] getAccesos(Current current = null)
        {
            _logger.LogDebug("GetPersonas request received.");
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                foreach (var a in pc.Accesos)
                {
                    _logger.LogDebug(a.patente);
                }
                return pc.Accesos.ToArray();
            }
        }

        /// <summary>
        /// Get delay between server and client (for testing)
        /// </summary>
        /// <param name="clientTime"> Client time in ms</param>
        /// <param name="current">.</param>
        /// <returns>Delay</returns>
        public override long getDelay(long clientTime, Current current = null)
        {
            var serverTime = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds();
            _logger.LogDebug("GetDelay request received. Server time:"+serverTime);
            return serverTime - clientTime;
        }
    }
}
