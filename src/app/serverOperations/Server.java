package app.serverOperations;

import app.NetworkConnection;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 *
 * @author Hakim
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(2222);
        System.out.println("Server started at 2222");
        HashMap<String,Information> clientList=new HashMap<>();
        
        while(true){
            Socket socket=server.accept();//server socket
            NetworkConnection nc=new NetworkConnection(socket);
            
            new Thread(new CreateConnection(clientList, nc)).start();
        }
    }
    
}
