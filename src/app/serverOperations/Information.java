package app.serverOperations;

import app.NetworkConnection;

/**
 *
 * @author Hakim
 */
public class Information {
    private String name;
    private NetworkConnection ncConnection;

    public Information(String name, NetworkConnection ncConnection) {
        this.name = name;
        this.ncConnection = ncConnection;
    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public NetworkConnection getNcConnection() {
        return ncConnection;
    }

//    public void setNcConnection(NetworkConnection ncConnection) {
//        this.ncConnection = ncConnection;
//    }

    @Override
    public String toString() {
        return "Information{" + "name=" + name + ", ncConnection=" + ncConnection + '}';
    }
    
    
    
    
}
