package cl.ucn.disc.pdis.parking

import androidx.lifecycle.MutableLiveData
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
    fun getDelay() {
        zeroIce.start()

        val time = System.currentTimeMillis()
        val delay = zeroIce.sistema.getDelay(time)

        log.debug("Delay: {} milliseconds", delay)
        zeroIce.stop()
    }

    /**
     * Add Vehiculo test.
     */
    @Test
    fun addVehiculo() {
        val rut = "18.585.574-0"
        val patent= "FZPC33"
        val brand = "Suzuki"
        val model = "Samurai"
        val year = 2019
        val observation = "Two doors"
        val color = "Black"

        val vehiculo = Vehiculo(rut, patent, brand, model, year, observation, color)

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
        val vehicles = zeroIce.sistema.vehiculos
        zeroIce.stop()

        try {
            for(i in vehicles) {
                log.debug("Vehiculo's: {}", i)
            }
        }catch(e: VehicleException) {
            log.error("Error ...", e)
        }
    }

    /**
     * Find Vehiculo's patente test.
     */
    @Test
    fun findPatente() {
        val patent = "FZPC33"
        var vehicle: Vehiculo? = null

        zeroIce.start()
        try {
            vehicle = zeroIce.contratos.findVehiculoByPatente(patent)
        }catch(e: VehicleException) {
            log.error("Error ...", e)
        }
        zeroIce.stop()

        log.debug("Founded: {}", vehicle)
    }

    /**
     * MutableLiveData Test.
     */
    @Test
    fun getVehiculosMLD() {
        zeroIce.start()
        val vehicles = zeroIce.sistema.vehiculos.toMutableList()
        zeroIce.stop()

        log.debug("Vehicles: {}", MutableLiveData(vehicles).value)
    }
}
