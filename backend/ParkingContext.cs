using System.Reflection;

using Microsoft.EntityFrameworkCore;
using Parking.ZeroIce.model;

namespace backend
{
    /// <summary>
    /// Connection to parking-ucn db.
    /// </summary>
    public class ParkingContext : DbContext
    {
        public DbSet<Persona> Personas { get; set; }
        public DbSet<Vehiculo> Vehiculos { get; set; }
        public DbSet<Acceso> Accesos { get; set; }

        /// <summary>
        /// Configuration
        /// </summary>
        /// <param name="optionsBuilder">optionsBuilder</param>
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            // Use Sqlite
            optionsBuilder.UseSqlite("Data Source=parking.db", options =>
            {
                options.MigrationsAssembly(Assembly.GetExecutingAssembly().FullName);

            });
            base.OnConfiguring(optionsBuilder);
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            
            
            // Model Persona
            modelBuilder.Entity<Persona>(p =>
            {
                p.HasKey(p => p.rut);
                //TODO: Complete
            });

            // Model Vehiculo
            modelBuilder.Entity<Vehiculo>(v =>
            {
                v.HasKey(v => v.patente);
                //TODO: Complete
            });
            // Model Acceso
            modelBuilder.Entity<Acceso>(a =>
            {
                a.HasKey(a => a.uid);
                //TODO: Complete
            });
            
            base.OnModelCreating(modelBuilder);
        }
    }
}