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
        /// Constructor.
        /// </summary>
        /// <param name="logger">Logger via DI</param>
        /// <param name="sistema">SistemaDisp_ via DI</param>
        public ParkingService(ILogger<ParkingService> logger, SistemaDisp_ sistema)
        {
            _logger = logger;
            _sistema = sistema;
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
                "Sistema",
                "tcp -z -t 15000 -p " + _port);
            
            // Register in the adapter
            adapter.add(_sistema, Util.stringToIdentity("Sistema"));
            
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