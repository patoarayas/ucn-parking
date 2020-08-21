package cl.ucn.disc.pdis.parking

import android.content.OperationApplicationException
import cl.ucn.disc.pdis.parking.zeroice.model.ContratosPrx
import cl.ucn.disc.pdis.parking.zeroice.model.SistemaPrx
import com.zeroc.Ice.*
import org.slf4j.LoggerFactory

/**
 * Zeroc-Ice connection class.
 */
class ZerocIce() {

    /**
     * Logger.
     */
    private val log = LoggerFactory.getLogger(ZerocIce::class.java)

    /**
     * Communicator.
     */
    private var communicator: Communicator? = null

    /**
     * Connections.
     */
    private val CONNECTION = "tcp -z -t 15000 -p 3000"
    lateinit var contratos: ContratosPrx
    lateinit var sistema: SistemaPrx

    /**
     * Singleton instance.
     */
    fun getInstance(): ZerocIce {
        return ZerocIce()
    }

    /**
     * Start connection.
     * https://doc.zeroc.com/ice/3.6/properties-and-configuration/setting-properties
     */
    fun start() {
        if(communicator != null) {
            log.warn("Initialized.")
            return
        }

        var x = Util.createProperties()
        // Property setup for ice
        val properties = Util.createProperties()
        properties.setProperty("Ice.Package.model", "cl.ucn.disc.pdis.parking.zeroice")

        // Initializes and creates the communicator
        val initialization = InitializationData()
        initialization.properties = properties
        communicator = Util.initialize(initialization)

        // Connections
        sistema = SistemaPrx.checkedCast(communicator!!.stringToProxy("Sistema:$CONNECTION"))
        contratos = ContratosPrx.checkedCast(communicator!!.stringToProxy("Contratos:$CONNECTION"))
        log.debug("Connection started ...")
    }

    /**
     * Stop connection.
     */
    fun stop() {
        if(communicator == null) {
            log.warn("Stopped.")
            return
        }

        communicator!!.destroy()
        log.debug("Connection stopped.")
    }
}
