package com.vyache.truckemulator;
import lombok.extern.slf4j.Slf4j;

import java.net.*;
import java.io.*;
/**
 * @author Vyacheslav Kirillov
 * @create 2022.10.25 20:42
 **/
@Slf4j
public class Sender {
    private  static final int    serverPort = 50001;
    private  static final String localhost  = "127.0.0.1";

    static Socket socket = null;
    static InetAddress ipAddress;

    static {
        try {
            ipAddress = InetAddress.getByName(localhost);
            socket = new Socket(ipAddress, serverPort);
            log.info("Welcome to Client side" +
                    "Connecting to the server" +
                    "(IP address " + localhost +
                    ", port " + serverPort + ")");
            log.info(
                    "The connection is established.");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ;
    public static void sendTE(String ar){
       // Socket socket = null;
        try{
            try {

               /* InetAddress ipAddress;
                ipAddress = InetAddress.getByName(localhost);
                socket = new Socket(ipAddress, serverPort);*/

              /*  log.info("Welcome to Client side" +
                        "Connecting to the server" +
                        "(IP address " + localhost +
                        ", port " + serverPort + ")");*/
                //InetAddress ipAddress;
              //  ipAddress
                log.info(
                        "LocalPort = " +
                                socket.getLocalPort() +
                                " InetAddress.HostAddress = " +
                                socket.getInetAddress()
                                        .getHostAddress() +
                                " ReceiveBufferSize (SO_RCVBUF) = "
                                + socket.getReceiveBufferSize());

                // Получаем входной и выходной потоки
                // сокета для обмена сообщениями с сервером
                InputStream  sin  = socket.getInputStream();
                OutputStream sout = socket.getOutputStream();

                DataInputStream  in ;
                DataOutputStream out;
                in  = new DataInputStream (sin );
                out = new DataOutputStream(sout);

                //Отправляем напрямую
                out.writeUTF(ar);
                // Завершаем поток
                out.flush();
                // Ждем ответа от сервера
                String answer = in.readUTF();
                log.info("Answer from server is "+answer);

                // Создаем поток для чтения с клавиатуры.
               /* InputStreamReader isr;
                isr = new InputStreamReader(System.in);
                BufferedReader keyboard;
                keyboard = new BufferedReader(isr);
                String line = null;
                System.out.println(
                        "Type in something and press enter");
                System.out.println();
                while (true) {
                    // Пользователь должен ввести строку
                    // и нажать Enter
                    line = keyboard.readLine();
                    // Отсылаем строку серверу
                    out.writeUTF(line);
                    // Завершаем поток
                    out.flush();
                    // Ждем ответа от сервера
                    line = in.readUTF();
                    if (line.endsWith("quit"))
                        break;
                    else {
                        System.out.println(
                                "The server sent me this line :\n\t"
                                        + line);
                    }
                }*/


            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (socket == null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

