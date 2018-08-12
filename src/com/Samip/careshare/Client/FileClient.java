package com.Samip.careshare.Client;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileClient {


    private Socket s;

    public FileClient(String host, String file,long Size) {
        for (int i = 1024; i <= 65535; i++) {
            try {
                s = new Socket(host, i);
                sendFile(file, Size);
                System.out.println(i);
                break;
            }
             catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void sendFile(String file,long Size) throws IOException {

        FileInputStream fis = new FileInputStream(file);
        int index = file.lastIndexOf("/");
        String namef = file.substring(index + 1);
        DataOutputStream name = new DataOutputStream(s.getOutputStream());
        name.writeChars(namef+'\n');
        DataOutputStream sid = new DataOutputStream(s.getOutputStream());
        name.writeInt((int) Size);
        byte[] buffer = new byte[4096];
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        while (fis.read(buffer) > 0) {
            name.write(buffer);
        }
        name.close();
        sid.close();
        fis.close();
        dos.close();
        s.close();
    }
}
