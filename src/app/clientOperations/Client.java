package app.clientOperations;

import app.NetworkConnection;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hakim
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("127.0.0.1",2222);
        System.out.println("Client started");
        
        NetworkConnection nc=new NetworkConnection(socket);//client nc
        
        Scanner input=new Scanner(System.in);
        System.out.println("Enter your username : ");
        String name=input.nextLine();
        nc.write(name);
        
        Thread reader=new Thread(new Reader(nc));
        Thread Writer=new Thread(new Writer(nc));
        
        reader.start();
        Writer.start();
        
        try {
            reader.join();
            Writer.join();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
