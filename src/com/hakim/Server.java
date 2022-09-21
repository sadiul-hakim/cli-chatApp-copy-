package com.hakim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Hakim
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(2222);
        System.out.println("Server started");

        while (true) {
            try ( Socket socket = server.accept()) {
                System.out.println("Client connected");

                PrintWriter out;
                try ( BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()))) {
                    out = new PrintWriter(
                            socket.getOutputStream(),true);

                    while (true) {
                        String msg = in.readLine();
                        if(msg.equals("exit")) break;
                        
                        System.out.println("Message rechived");
                        System.out.println(msg);
                    }
                    out.println("HAKIM");
                    out.println("exit");
                    System.out.println("Message send");
                    out.flush();
                }
                out.close();
            }
        }
    }
}
