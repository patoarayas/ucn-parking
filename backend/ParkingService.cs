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

using System.Threading;
using System.Threading.Tasks;
using Ice;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Parking.ZeroIce.model;

namespace backend
{
    /// <summary>
    /// ParkingService.
    /// </summary>
    public class ParkingService : IHostedService
    {
        /// <summary>
        /// Logger
        /// </summary>
        private readonly ILogger<ParkingService> _logger;

        /// <summary>
        /// Port to use by Ice
        /// </summary>
        private readonly int _port = 3000;

        /// <summary>
        /// Communicator
        /// </summary>
        private readonly Communicator _communicator;

        /// <summary>
        /// Sistema
        /// </summary>
        private readonly SistemaDisp_ _sistema;

        /// <summary>
        /// Contratos
        /// </summary>
        private readonly ContratosDisp_ _contratos;

        /// <summary>
        /// Constructor.
        /// </summary>
        /// <param name="logger">Logger via DI</param>
        /// <param name="sistema">SistemaDisp_ via DI</param>
        public ParkingService(ILogger<ParkingService> logger, SistemaDisp_ sistema, ContratosDisp_ contratos)
        {
            _logger = logger;
            _sistema = sistema;
            _contratos = contratos;
            _communicator = BuildCommunicator();
        }

        /// <summary>
        /// Triggered when the app host is ready to start the service
        /// </summary>
        public Task StartAsync(CancellationToken cancellationToken)
        {
            _logger.LogInformation("ParkingService started.");

            // Ice adapter
            var adapter = _communicator.createObjectAdapterWithEndpoints(
                "Parking",
                "tcp -z -t 15000 -p " + _port
            );

            // Register in the adapter
            adapter.add(_sistema, Util.stringToIdentity("Sistema"));
            adapter.add(_contratos, Util.stringToIdentity("Contratos"));
            adapter.activate();

            return Task.CompletedTask;
        }

        /// <summary>
        /// Triggered when the app host tries to stop the service.
        /// </summary>
        public Task StopAsync(CancellationToken cancellationToken)
        {
            _logger.LogInformation("ParkingService stopped.");

            // Stop communicator
            _communicator.shutdown();
            _logger.LogInformation("Communicator stopped");

            return Task.CompletedTask;
        }

        /// <summary>
        /// Build Ice Communicator
        /// </summary>
        /// <returns>Communicator</returns>
        private Communicator BuildCommunicator()
        {
            _logger.LogDebug("Initializing communicator");

            // ZeroC properties
            Properties properties = Util.createProperties();
            InitializationData initializationData = new InitializationData();
            initializationData.properties = properties;

            return Ice.Util.initialize(initializationData);
        }
    }
}