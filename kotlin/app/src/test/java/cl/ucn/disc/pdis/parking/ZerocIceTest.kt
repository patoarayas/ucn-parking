package cl.ucn.disc.pdis.parking

import cl.ucn.disc.pdis.parking.zeroice.model.VehicleException
import cl.ucn.disc.pdis.parking.zeroice.model.Vehiculo
import org.junit.Test
import org.slf4j.LoggerFactory

/**
 * Connections testing class.
 */
class ZerocIceTest {

    /**
     * Logger.
     */
    private val log = LoggerFactory.getLogger(ZerocIceTest::class.java)

    /**
     * Zeroc-Ice instance call.
     */
    private val zeroIce = ZerocIce().getInstance()

    /**
     * Delay test.
     */
    @Test
    fun getDelayTest() {
        zeroIce.start()

        var time = System.currentTimeMillis()
        var delay = zeroIce.sistema.getDelay(time)

        log.debug("Delay: {} milliseconds", delay)
        zeroIce.stop()
    }

    /**
     * Add Vehiculo test.
     */
    @Test
    fun addVehiculo() {
        var rut = "18.585.574-0"
        var patent= "FZPC33"
        var brand = "Suzuki"
        var model = "Samurai"
        var year = 2019
        var observation = "Two doors"
        var color = "Black"

        var vehiculo = Vehiculo(rut, patent, brand, model, year, observation, color)

        zeroIce.start()
        zeroIce.contratos.registrarVehiculo(vehiculo)

        log.debug("{} added", vehiculo)
        zeroIce.stop()
    }

    /**
     * Get vehiculo's test.
     */
    @Test
    fun getVehiculos() {
        zeroIce.start()
        var vehicles = zeroIce.sistema.vehiculos
        zeroIce.stop()

        try {
            for(i in vehicles) {
                log.debug("Vehiculo's: {}", i)
            }
        }catch(e: VehicleException) {
            log.error("Error ...", e)
        }
    }
    @Test
    fun getVehiculos2() {
        zeroIce.start()
        var vehicles = zeroIce.sistema.vehiculos
        zeroIce.stop()

        for(i in arrayOf(vehicles)) {
            log.debug("Vehicles: {}", arrayOf(i))
        }
    }

    /**
     * Find Vehiculo's patente test.
     */
    @Test
    fun findPatent() {
        var patent = "FZPC33"

        zeroIce.start()
        try {
            var vehicle = zeroIce.contratos.findVehiculoByPatente(patent)
            log.debug("Founded: {}", vehicle)

        }catch(e: VehicleException) {
            log.error("Error ...", e)
        }
        zeroIce.stop()
    }
}
