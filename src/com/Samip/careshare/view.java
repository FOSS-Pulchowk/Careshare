package com.Samip.careshare;

import com.Samip.careshare.Client.UserInterFace;
import com.Samip.careshare.server.View;
import com.Samip.careshare.server.controller;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class view {
    private JButton SENDButton;
    private JButton RECIEVEButton;
    private JPanel Welcome;

    public view() {
        SENDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserInterFace Samip;
                Samip = new UserInterFace();
                JFrame newFrame = new JFrame();
                newFrame.setSize(500,700);
                newFrame.add(Samip.getPanel());
                newFrame.setVisible(true);
            }
        });
        RECIEVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller starting = new controller();
                starting.getstarted();
            }
        });
    }

    public JPanel getWelcome() {
        return Welcome;
    }


}
