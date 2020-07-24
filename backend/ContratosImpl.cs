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
        public override Acceso registrarAcceso(string patente, Porteria porteria, Current current = null)
        {
            throw new System.NotImplementedException();
        }

        public override void registrarUsuario(Persona persona, Vehiculo vehiculo, Current current = null)
        {
            throw new System.NotImplementedException();
        }
    }
}