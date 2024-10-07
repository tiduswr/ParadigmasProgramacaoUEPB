package com.tiduswr.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import com.tiduswr.model.CardsReader;
import com.tiduswr.view.TripleTriadUI;

public class GameController {
    
    public void createWindow(){
        var cards = CardsReader.readCardsFromCSV(); // Carrega cartas

        try{
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    try {
                        new TripleTriadUI(cards);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch(InterruptedException | InvocationTargetException e){
            throw new RuntimeException(e);
        }
    }
}
