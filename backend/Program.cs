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