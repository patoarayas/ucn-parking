package cl.ucn.disc.pdis.parking;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.InitializationData;
import com.zeroc.Ice.Properties;
import com.zeroc.Ice.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.ucn.disc.pdis.parking.zeroice.model.ContratosPrx;
import cl.ucn.disc.pdis.parking.zeroice.model.SistemaPrx;

import static com.zeroc.Ice.Util.createProperties;

public class ZeroIce {

    // Logger
    private static final Logger log = LoggerFactory.getLogger(ZeroIce.class);

    // Singleton instance
    private static final ZeroIce ZERO_ICE = new ZeroIce();

    // Communicator
    private Communicator communicator;

    // ContratosImpl
    ContratosPrx contratosPrx;

    // SistemaImpl
    SistemaPrx sistemaPrx;

    /**
     * Empty constructor.
     */
    public ZeroIce(){

    }

    /**
     * Get singleton instance
     * @return Instance
     */
    public static ZeroIce getInstance(){
        return ZERO_ICE;
    }

    /**
     * @param args to use as source.
     * @return the {@link InitializationData}.
     */
    private static InitializationData getInitializationData(String[] args) {


        // Properties
        final Properties properties = createProperties(args);
        properties.setProperty("Ice.Package.model", "cl.ucn.disc.pdis.parking.zeroice");
        //properties.setProperty("Ice.Plugin.Slf4jLogger.java", "cl.ucn.disc.pdis.fivet.zeroice.Slf4jLoggerPluginFactory");

        InitializationData initializationData = new InitializationData();
        initializationData.properties = properties;

        return initializationData;
    }

    /**
     * Start communication
     */
    public void start(){
        if(this.communicator != null){
            log.warn("Communicator was already initialized");
            return;
        }
        //String[] args = {"1","2"};
        //this.communicator = Util.initialize(getInitializationData(args));

        this.communicator = Util.initialize();

        this.contratosPrx = ContratosPrx.checkedCast(this.communicator.stringToProxy("Contratos:tcp -z -t 15000 -p 3000"));
        this.sistemaPrx = SistemaPrx.checkedCast(this.communicator.stringToProxy("Sistema:tcp -z -t 15000 -p 3000"));
        log.debug("Communication started");
    }

    /**
     * Stop communication
     */
    public void stop(){
        if(this.communicator == null){
            log.warn("Communicator already stopped");
            return;
        }
        this.contratosPrx = null;
        this.communicator.destroy();
        log.debug("Communication stopped");
    }
}
