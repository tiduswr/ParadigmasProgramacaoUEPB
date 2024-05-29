package com.tiduswr;

import java.io.IOException;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new TripleTriadUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
