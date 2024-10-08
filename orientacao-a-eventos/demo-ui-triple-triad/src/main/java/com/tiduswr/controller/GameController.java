package com.tiduswr.controller;

import java.io.IOException;

import javax.swing.SwingUtilities;

import com.tiduswr.model.CardsReader;
import com.tiduswr.model.SoundService;
import com.tiduswr.view.TripleTriadUI;

public class GameController {
    
    public void createWindow(){
        var cards = CardsReader.readCardsFromCSV(); // Carrega cartas
        var themeSongService = new SoundService("theme.wav", 1, 0.5f); // Carrega musica tema
        var selectionSongService = new SoundService("selection.wav", 10); // Carrega efeito de seleção

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new TripleTriadUI(cards, themeSongService, selectionSongService);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
