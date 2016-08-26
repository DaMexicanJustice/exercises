/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketexercises.echoserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Patrick Johansen
 */
public class EchoServer {

    static int port = 8080;
    static String ip = "localhost";
    
    public static void handleClient(Socket s) throws IOException {
        Scanner input = new Scanner(s.getInputStream());
        PrintWriter writer = new PrintWriter(s.getOutputStream(), true);
        String message = input.nextLine();
        
        /*  How to connect to the server using telnet
        
            The application runs using the command-prompt/shell. 
                ->Run this file
                    ->Open the command prompt/shell (as admin)
                        -> if telnet is enabled: telnet localhost 8080
                        -> else: dism /online /Enable-Feature /FeatureName:TelnetClient 
                            -> After connecting: type:
                                ->UPPER#somemessage
                                ->LOWER#somemessage
                                ->TRANSLATE#somemessage
                                ->REVERSE#somemessage
                                    -> close when done.
        */
        
        while (!message.equals("STOP")) {
            message = input.nextLine();
            if (message.startsWith("UPPER#")) {
                writer.println(message.substring(6).toUpperCase());
            }
            if (message.startsWith("LOWER#")) {
                writer.println(message.substring(6).toLowerCase());
            }
            
            if (message.startsWith("REVERSE#")) {
                StringBuilder sb = new StringBuilder(message.substring(8));
                writer.println(sb.reverse());
            }
            
            if (message.startsWith("TRANSLATE#")) {
                String word = message.substring(10, message.length()).toLowerCase();
                switch(word) {
                    case "hund":
                        writer.println("dog");
                        break;
                    case "bygning":
                        writer.println("building");
                        break;
                    case "mad":
                        writer.println("food");
                        break;
                    case "beregning":
                        writer.println("calculation");
                        break;
                    default:
                        writer.println("#NOT_FOUND");
                }
            
            }
        }

        input.close();
        writer.close();
        s.close();
    }
    public static void main(String[] args) throws IOException {
       if (args.length == 2) {
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(ip, port));
        System.out.println("Server Started Listening on " + port + "bound to " + ip);
        while (true) {
            Socket socket = ss.accept();
            handleClient(socket);
            System.out.println("new Client connected");
        }
    }
    }
    

