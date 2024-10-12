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

/**
 * Classe principal da interface gráfica do jogo Triple Triad.
 * Esta classe estende JFrame e configura todos os componentes da interface do jogo, incluindo o tabuleiro, as cartas dos jogadores e o log de jogadas.
 */
@Getter
public class TripleTriadUI extends JFrame {

    /** Tabuleiro do jogo */
    private Board board; 

    /** Log de jogadas */
    private GameLog gameLog; 

    /** Mãos dos jogadores */
    private PlayerCards p1, p2; 

    /** Dimensões dos componentes */
    private final int glW, glH, plW, plH, spW, spH; 

    /** Tamanho da tela */
    private final Dimension SCREEN_SIZE = new Dimension(800, 700); 

    /** Serviços de som */
    private final SoundServices soundServices; 


    /**
     * Construtor da classe TripleTriadUI.
     * Inicializa a interface do jogo, configura a janela e instancia os componentes principais.
     *
     * @param cards Lista de dados das cartas que podem ser usadas no jogo.
     * @param soundServices Serviços de som para tocar as músicas e efeitos sonoros.
     * @throws IOException Se houver um erro ao carregar os recursos de som.
     */
    public TripleTriadUI(List<CardData> cards, SoundServices soundServices) throws IOException {
        this.soundServices = soundServices;

        // Adiciona um ouvinte para o fechamento da janela
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                soundServices.getSoundService("main-theme").close();
            }
        });

        // Configurações da janela
        setTitle("Triple Triad");
        setSize(SCREEN_SIZE);

        // Cálculo das dimensões dos componentes
        plW = (int) (getWidth() * 0.17); // Largura do painel de cartas
        plH = getHeight(); // Altura do painel de cartas
        glW = getWidth(); // Largura total do log
        glH = (int) (getHeight() * 0.12); // Altura do log
        spW = getWidth(); // Largura do painel de pontuação
        spH = (int) (getHeight() * 0.04); // Altura do painel de pontuação

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout()); // Layout principal

        // Instancia do log de jogadas
        gameLog = new GameLog();
        gameLog.setPreferredSize(new Dimension(glW, glH));
        gameLog.setBorder(BorderFactory.createTitledBorder("Log de Jogadas"));

        // Wrapper para o campo do jogo
        JPanel boardWrapper = new JPanel(new BorderLayout());
        board = new Board("/back.png", cards); // Instancia o tabuleiro
        boardWrapper.add(board, BorderLayout.CENTER);
        boardWrapper.setBorder(BorderFactory.createTitledBorder("Campo"));

        // Seleção aleatória de cartas para os jogadores
        List<CardData> cardsP1 = new ArrayList<>();
        List<CardData> cardsP2 = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            cardsP1.add(cards.get(random.nextInt(cards.size())));
            cardsP2.add(cards.get(random.nextInt(cards.size())));
        }
        Player jose = new Player("José", cardsP1, Color.decode("#08C2FF")); // Jogador 1
        Player maria = new Player("Maria", cardsP2, Color.decode("#C96868")); // Jogador 2
        p1 = new PlayerCards(this, jose, plW, plH);
        p2 = new PlayerCards(this, maria, plW, plH);
        p2.setCardsActive(true); // Ativa as cartas do jogador 2
        p2.processAllPlayerCardData((indice, carta) -> {
            carta.setFlipped(indice % 2 == 0); // Esconde cartas do jogador 2
        });

        // Painel de pontuação
        JPanel scorePanel = new ScorePanel(p1.getPlayer(), p2.getPlayer());
        scorePanel.setPreferredSize(new Dimension(spW, spH)); // Ajusta a altura do painel de pontuação

        // Painel central que contém o tabuleiro e o log de jogadas
        JPanel centerPanel = new JPanel(new BorderLayout(0, 0));
        centerPanel.add(scorePanel, BorderLayout.NORTH);
        centerPanel.add(boardWrapper, BorderLayout.CENTER);
        centerPanel.add(gameLog, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
        add(p1, BorderLayout.WEST); // Adiciona a mão do jogador 1
        add(p2, BorderLayout.EAST); // Adiciona a mão do jogador 2

        // Configurações finais da janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        soundServices.getSoundService("main-theme").playThenLoop("theme-loop.wav"); // Toca a música principal
        setVisible(true); // Torna a janela visível
    }
}