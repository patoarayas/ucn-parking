using System;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Parking.ZeroIce.model;

namespace backend
{
    class Program
    {
        /// <summary>
        /// Main method. Creates Host
        /// </summary>
        /// <param name="args"> cli args</param>
        static void Main(string[] args)
        {
            CreateHostBuilder(args).Build().Run();
        }


        /// <summary>
        /// Build the Host
        /// </summary>
        /// <param name="args">cli args</param>
        /// <returns>IHostBuilder</returns>
        public static IHostBuilder CreateHostBuilder(string[] args) =>
            Host
                .CreateDefaultBuilder(args)
                // Environment
                .UseEnvironment("Development")
                // Logging configuration
                .ConfigureLogging(logging =>
                {
                    logging.ClearProviders();
                    logging.AddConsole(options =>
                    {
                        options.IncludeScopes = true;
                        options.TimestampFormat = "[yyyyMMdd.HHmmss.fff] ";
                        options.DisableColors = false;
                    });
                    logging.SetMinimumLevel(LogLevel.Trace);
                })
                // Close app with ctrl+C
                .UseConsoleLifetime()
                // Services inside DI
                .ConfigureServices((hostContext, services) =>
                {
                    // UCN parking service
                    services.AddHostedService<ParkingService>();
                    // Logging service
                    services.AddLogging();
                    // Host options config
                    services.Configure<HostOptions>(option =>
                    {
                        
                        option.ShutdownTimeout = System.TimeSpan.FromSeconds(15);
                    });
                    // Add Sistema interface
                    services.AddSingleton<SistemaDisp_, SistemaImpl>();
                });
    }
}