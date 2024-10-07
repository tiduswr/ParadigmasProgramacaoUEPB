package com.tiduswr.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import com.tiduswr.model.CardsReader;
import com.tiduswr.model.SoundService;
import com.tiduswr.view.TripleTriadUI;

public class GameController {
    
    public void createWindow(){
        var cards = CardsReader.readCardsFromCSV(); // Carrega cartas
        var soundService = new SoundService("theme.wav"); // Carrega musica tema

        try{
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    try {
                        new TripleTriadUI(cards, soundService);
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
