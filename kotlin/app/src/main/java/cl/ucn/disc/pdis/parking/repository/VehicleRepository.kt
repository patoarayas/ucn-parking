package cl.ucn.disc.pdis.parking.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.ui.graphics.vectormath.length
import cl.ucn.disc.pdis.parking.ZerocIce
import cl.ucn.disc.pdis.parking.zeroice.model.Vehiculo
import com.zeroc.Ice.CommunicatorDestroyedException
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
     * Array
     */
    private val vehiculos = mutableListOf<Vehiculo>()

    /**
     * Gets the vehiculo's data from the remote server.
     */
    fun getVehiculos(): LiveData<List<Vehiculo>> = MutableLiveData(vehiculos.apply {

        try {
            zeroIce.start()
            var vehicles = zeroIce.sistema.vehiculos
            addAll(vehicles)
            zeroIce.stop()
        }catch (e: CommunicatorDestroyedException) {
            log.error("Error ...", e)
        }
    })
}
