package com.tiduswr.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.tiduswr.model.CardData;
import com.tiduswr.model.Player;
import com.tiduswr.model.SoundServices;

import lombok.Getter;

@Getter
public class TripleTriadUI extends JFrame {

    private Board board;
    private GameLog gameLog;
    private PlayerCards p1, p2;
    private final int glW, glH, plW, plH, spW, spH;
    private final Dimension SCREEN_SIZE = new Dimension(800, 700);
    private final SoundServices soundServices;

    public TripleTriadUI(List<CardData> cards, SoundServices soundServices) throws IOException {
        this.soundServices = soundServices;

        // Tocador de fitas :)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                soundServices.getSoundService("main-theme").close();
            }
        });

        // Configurações de janela
        setTitle("Triple Triad");
        setSize(SCREEN_SIZE);

        plW = (int) (getWidth() * 0.17); // Largura do painel de cartas
        plH = getHeight(); // Altura do painel de cartas
        glW = getWidth(); // Largura total do log
        glH = (int) (getHeight() * 0.12); // Altura do log
        spW = getWidth(); // Largura do Scopre Player
        spH = (int) (getHeight() * 0.04); // Largura do Scopre Player

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout()); // Espaçamento ajustado

        // Instancia do gamelog para mensagens
        gameLog = new GameLog();
        gameLog.setPreferredSize(new Dimension(glW, glH));
        gameLog.setBorder(BorderFactory.createTitledBorder("Log de Jogadas"));

        // Wrapper para o campo
        JPanel boardWrapper = new JPanel(new BorderLayout());
        board = new Board("/back.png", cards);
        boardWrapper.add(board, BorderLayout.CENTER);
        boardWrapper.setBorder(BorderFactory.createTitledBorder("Campo"));

        // Pode ser visto outra forma de pegar esses dados
        List<CardData> cardsP1 = new ArrayList<>();
        List<CardData> cardsP2 = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            cardsP1.add(cards.get(random.nextInt(cards.size())));
            cardsP2.add(cards.get(random.nextInt(cards.size())));
        }
        Player jose = new Player("José", cardsP1, Color.decode("#08C2FF"));
        Player maria = new Player("Maria", cardsP2, Color.decode("#C96868"));
        p1 = new PlayerCards(this, jose, plW, plH);
        p2 = new PlayerCards(this, maria, plW, plH);
        p2.setCardsActive(true); // Você pode inativar uma mão de um jogador assim
        p2.processAllPlayerCardData((indice, carta) -> {
            carta.setFlipped(indice%2==0); // Como você pode esconder cartas da mão do usuário
        });

        // Painel de Pontuação
        JPanel scorePanel = new ScorePanel(p1.getPlayer(), p2.getPlayer());
        scorePanel.setPreferredSize(new Dimension(spW, spH)); // Ajusta a altura do painel de pontuação

        // Painel central contendo o board e o game log
        JPanel centerPanel = new JPanel(new BorderLayout(0,0));
        centerPanel.add(scorePanel, BorderLayout.NORTH);
        centerPanel.add(boardWrapper, BorderLayout.CENTER);
        centerPanel.add(gameLog, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
        add(p1, BorderLayout.WEST);
        add(p2, BorderLayout.EAST);

        // Configurações de janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        soundServices
            .getSoundService("main-theme")
            .playThenLoop("theme-loop.wav");
        setVisible(true);
    }
}
