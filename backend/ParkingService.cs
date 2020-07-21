using System.Threading;
using System.Threading.Tasks;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;

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
        /// Constructor.
        /// </summary>
        /// <param name="logger">Logger via DI</param>
        public ParkingService(ILogger<ParkingService> logger)
        {
            _logger = logger;
        }

        /// <summary>
        /// Triggered when the app host is ready to start the service
        /// </summary>
        public Task StartAsync(CancellationToken cancellationToken)
        {
            _logger.LogInformation("ParkingService started.");
            return Task.CompletedTask;
        }

        /// <summary>
        /// Triggered when the app host tries to stop the service.
        /// </summary>
        public Task StopAsync(CancellationToken cancellationToken)
        {
            _logger.LogInformation("ParkingService stopped.");
            return Task.CompletedTask;
        }
    }
}