package com.vyache.truckemulator;
import lombok.extern.slf4j.Slf4j;

import java.net.*;
import java.io.*;

@Slf4j
public class Sender {
    private  static final int    serverPort = 50001;
    private  static final String localhost  = "127.0.0.1";
    static Socket socket;
    static InetAddress ipAddress;

    public static void connectToHub() {
        try {

            ipAddress = InetAddress.getByName(localhost);
            socket = new Socket(ipAddress, serverPort);
            log.info("Welcome to Client side. " +
                    "Connecting to the server" +
                    "(IP address " + localhost +
                    ", port " + serverPort + ")");
            log.info(
                    "The connection is established.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static void sendToHub(String ar) {
        try{
            try {
                log.info(
                        "LocalPort = " +
                                socket.getLocalPort() +
                                " InetAddress.HostAddress = " +
                                socket.getInetAddress()
                                        .getHostAddress() +
                                " ReceiveBufferSize (SO_RCVBUF) = "
                                + socket.getReceiveBufferSize());
                // Create in and out streams
                InputStream  sin  = socket.getInputStream();
                OutputStream sout = socket.getOutputStream();
                DataInputStream  in ;
                DataOutputStream out;
                in  = new DataInputStream (sin );
                out = new DataOutputStream(sout);
                //Start sending
                out.writeUTF(ar);
                // End sending
                out.flush();
                // Answer from server
                String answer = in.readUTF();
                log.info("Answer from server is "+answer);

            } catch (Exception e) {
                e.printStackTrace();
                //wait and connect again
                Thread.sleep(1000);
                connectToHub();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

