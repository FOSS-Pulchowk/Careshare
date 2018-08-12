package com.Samip.careshare.server;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;


public class FileServer extends Thread {
    controller receiver = new controller();
    private ServerSocket ss;
    private static int ports=1024;
    public FileServer() {
        for (ports=1024;ports<=65535;ports++) {
            try {
                ss = new ServerSocket(ports);
                System.out.println(ports);
                break;
            } catch (IOException e) {
                if(ports==65535)
                    JOptionPane.showMessageDialog(new JFrame(),"Coudn't connect!!");
                continue;

            }
        }
    }

    public void run() {
        while (true) {
            try {
                Socket clientSock = ss.accept();
                saveFile(clientSock);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getPorts() {
        return ports;
    }

    private void saveFile(Socket clientSock) throws IOException {
        DataInputStream dis = new DataInputStream(clientSock.getInputStream());
        String Name = "";
        try {
             while(true) {
                 char a = dis.readChar();

                 if(a=='\n')
                     break;
                Name = Name + a;

            }
        }catch (EOFException e){
            System.out.println(e.getMessage());
        }
        Logger Info = Logger.getAnonymousLogger();
        Info.info(Name);
        FileOutputStream fos = new FileOutputStream(Name);
        byte[] buffer = new byte[4096];
        int filesize = dis.readInt(); // Send file size in separate msg
        int totalRead =0;
        int read = 0;
        int remaining = filesize;
        while((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
             totalRead += read;
            remaining -= read;
            receiver.seeProgress(totalRead,filesize);
            System.out.println("read " + totalRead + " bytes.");
            fos.write(buffer, 0, read);
                    }

        fos.close();
        dis.close();
    }
    public void sclose() throws IOException {
        ss.close();
    }

}

