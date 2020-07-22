using System;
using Ice;
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
        /// Constructor
        /// </summary>
        /// <param name="logger">Logger DI</param>
        public SistemaImpl(ILogger<SistemaImpl> logger)
        {
            _logger = logger;
            _logger.LogDebug("Building SistemaImpl");
        }
        
        
        /// <summary>
        /// Get delay between server and client
        /// </summary>
        /// <param name="clientTime"> Client time in ms</param>
        /// <param name="current">.</param>
        /// <returns>Delay</returns>
        public override long getDelay(long clientTime, Current current = null)
        {
            return DateTimeOffset.UtcNow.ToUnixTimeMilliseconds() - clientTime;
        }
    }
}