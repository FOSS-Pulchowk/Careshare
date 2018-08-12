package com.Samip.careshare.server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;


public class controller {
   static View samipView = new View();
    public void getstarted(){
        JButton Start = samipView.getStartButton();
        JFrame mainFrame = new JFrame();
        mainFrame.add(samipView.getPanel1());
        mainFrame.setSize(500,500);
        mainFrame.setVisible(true);
        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileServer fs = new FileServer();
                JLabel set = samipView.getText();
                if (!set.getText().toString().equals("Recieving From client")) {
                    fs.start();
                    set.setText("Recieving From client");
                    Start.setText("Stop");
                }
                else{
                    try {
                        fs.sclose();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Start.setText("Start");
                    set.setText("WELCOME");
                }

                }


        });
    }
    public void seeProgress(int done,int total){
        JProgressBar activated = samipView.getProgressBar1();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                               activated.setValue( done/total *100);
            }
        });
    }
    }
