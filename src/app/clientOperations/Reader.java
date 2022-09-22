package app.clientOperations;

import app.NetworkConnection;

/**
 *
 * @author Hakim
 */
public class Reader implements Runnable {

    private NetworkConnection nc;

    public Reader(NetworkConnection nc) {
        this.nc = nc;
    }

    @Override
    public void run() {
        while (true) {
            String msg = nc.read();
            System.out.println("Received : " + msg);
        }
    }

}
