package com.Samip.careshare;
import javax.swing.*;

public class model {
    public static void main(String[] args){
        view welcomeview = new view();
        JFrame WelFrame = new JFrame();
        WelFrame.setSize(500,700);
        WelFrame.add(welcomeview.getWelcome());
        WelFrame.setVisible(true);
    }
}
