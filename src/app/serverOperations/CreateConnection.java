
package app.serverOperations;

import app.NetworkConnection;
import java.util.HashMap;

/**
 *
 * @author Hakim
 */
public class CreateConnection implements Runnable{
    HashMap<String, Information> clientList;
    NetworkConnection nc;//server nc

    public CreateConnection(HashMap<String, Information> clientList, NetworkConnection nc) {
        this.clientList = clientList;
        this.nc = nc;
    }

    @Override
    public void run() {
        String name=nc.read();
        
        System.out.println("User "+name+" Connected.");
        
        clientList.put(name, new Information(name, nc));
        System.out.println("HashMap updated "+clientList);
        
        new Thread(new ReaderWriterServer(name, clientList, nc)).start();
        
    }
    
    
}
