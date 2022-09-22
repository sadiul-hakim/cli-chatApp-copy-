package app.clientOperations;

import app.NetworkConnection;
import java.util.Scanner;

/**
 *
 * @author Hakim
 */
public class Writer implements Runnable {

    private NetworkConnection nc;

    public Writer(NetworkConnection nc) {
        this.nc = nc;
    }

    @Override
    public void run() {
        while (true) {
            Scanner input = new Scanner(System.in);

            String msg = input.nextLine();
            nc.write(msg);
        }
    }

}
