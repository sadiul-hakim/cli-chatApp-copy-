package com.hakim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Hakim
 */
public class Client1 {

    public static void main(String[] args) throws IOException {
        System.out.println("Client started");
        try (Socket socket = new Socket("127.0.0.1", 2222)) {
            System.out.println("Clien connected");
            PrintWriter out;
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()))) {
                out = new PrintWriter(
                        socket.getOutputStream(),true);
                
                
                 Scanner input=new Scanner(System.in);
                String msg=input.nextLine();
                 
                out.println(msg);
                out.println("exit");
                out.flush();
                
                System.out.println("Message send");
               
                while (true) {
                    String res = in.readLine();
                    
                    if (res.equals("exit")) {
                        break;
                    }
                    System.out.println("Message received");
                    System.out.println(res);
                }
            }
            out.close();
            
//        Scanner input = new Scanner(System.in);
//
//        String message = input.nextLine();
//
//        out.write(message);
//
//        String response = String.valueOf(in.readLine());
//        System.out.println("From server : " + response);
        }
    }
}
