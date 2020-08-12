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
            });

            // Model Vehiculo
            modelBuilder.Entity<Vehiculo>(v =>
            {
                v.HasKey(v => v.patente);
            });

            // Model Acceso
            modelBuilder.Entity<Acceso>(a =>
            {
                a.HasKey(a => a.uid);
                a.Property(a => a.uid).ValueGeneratedOnAdd();
            });

            // Test entities
            modelBuilder.Entity<Persona>().HasData(
                new Persona()
                {
                    rut = "test_rut",
                    nombre = "test_nombre",
                    genero = Genero.OTRO,
                    email = "test_email",
                    fono = "test_fono",
                    movil = "test_movil",
                    rol = Rol.ACADEMICO,
                    unidadAcademica = "test_unidad_academica"
                });

            modelBuilder.Entity<Vehiculo>().HasData(
                new Vehiculo()
                {
                    anio = 1998,
                    color = "test_color",
                    marca = "test_marca",
                    modelo = "test_modelo",
                    observacion = "test_observacion",
                    patente = "test_patente",
                    rut = "test_rut"
                });

            modelBuilder.Entity<Acceso>().HasData(
                new Acceso()
                {
                    horaEntrada = "test_horaEntrada",
                    patente = "test_patente",
                    porteria = Porteria.CERRO,
                    uid = 1
                });

            base.OnModelCreating(modelBuilder);
        }
    }
}
