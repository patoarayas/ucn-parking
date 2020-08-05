/*
 * Copyright (c) 2020. Patricio Araya, David Canto, Ariel Vejar.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 *
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
  public ZeroIce() {

  }

  /**
   * Get singleton instance
   *
   * @return Instance
   */
  public static ZeroIce getInstance() {
    return ZERO_ICE;
  }


  /**
   * Start communication
   */
  public void start() {
    if (this.communicator != null) {
      log.warn("Communicator was already initialized");
      return;
    }

    this.communicator = Util.initialize();

    this.contratosPrx = ContratosPrx.checkedCast(this.communicator.stringToProxy("Contratos:tcp -z -t 15000 -p 3000"));
    this.sistemaPrx = SistemaPrx.checkedCast(this.communicator.stringToProxy("Sistema:tcp -z -t 15000 -p 3000"));
    log.debug("Communication started");
  }

  /**
   * Stop communication
   */
  public void stop() {
    if (this.communicator == null) {
      log.warn("Communicator already stopped");
      return;
    }
    this.contratosPrx = null;
    this.communicator.destroy();
    log.debug("Communication stopped");
  }
}
