package cl.ucn.disc.pdis.parking;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;

public class ZeroIceTest {
    // Logger
    private static final Logger log = LoggerFactory.getLogger(ZeroIceTest.class);

    // Zero Ice instance
    private static final ZeroIce zeroIce = ZeroIce.getInstance();


    @Test
    public void getDelayTest(){

        zeroIce.start();
        long clientTime = System.currentTimeMillis();
        long delay = zeroIce.sistemaPrx.getDelay(clientTime);
        log.debug("Delay of: "+String.valueOf(delay)+" milliseconds");
        zeroIce.stop();

    }
}
