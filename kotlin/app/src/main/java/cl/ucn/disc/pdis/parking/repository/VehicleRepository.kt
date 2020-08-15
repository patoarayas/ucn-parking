package cl.ucn.disc.pdis.parking.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.ucn.disc.pdis.parking.ZerocIce
import cl.ucn.disc.pdis.parking.zeroice.model.Vehiculo
import org.slf4j.LoggerFactory

/**
 * Repository class.
 */
class VehicleRepository {

    /**
     * Logger.
     */
    private val log = LoggerFactory.getLogger(VehicleRepository::class.java)

    /**
     * Zeroc-Ice instance call.
     */
    private val zeroIce = ZerocIce().getInstance()

    /**
     * MutableList.
     */
    private lateinit var vehicles: MutableList<Vehiculo>

    /**
     * Gets the vehiculo's data from the remote server.
     */
    fun getVehiculos(): LiveData<List<Vehiculo>> {
        zeroIce.start()
        vehicles = zeroIce.sistema.vehiculos.toMutableList()
        zeroIce.stop()

        log.debug("Vehicles: {}", vehicles)
        return MutableLiveData(vehicles)
    }
}
