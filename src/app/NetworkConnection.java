package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hakim
 */
public class NetworkConnection {
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;

    public NetworkConnection(Socket socket) throws IOException {
        this.socket = socket;
        out=new PrintWriter(socket.getOutputStream());
        in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public NetworkConnection(String ip,int port) throws IOException {
        this.socket = new Socket(ip,port);
        out=new PrintWriter(socket.getOutputStream());
        in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public Socket getSocket() {
        return socket;
    }
    
    public void write(String msg){
        out.println(msg);
        out.flush();
    }
    
    public String read(){
        String msg;
        try {
            msg= in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(NetworkConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return msg;
    }
    
}
