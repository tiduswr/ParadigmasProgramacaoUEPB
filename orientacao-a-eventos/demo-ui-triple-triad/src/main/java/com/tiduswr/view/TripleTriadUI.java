package com.tiduswr.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.tiduswr.controller.SoundController;
import com.tiduswr.model.CardData;
import com.tiduswr.model.Player;

public class TripleTriadUI extends JFrame {
    
    private Board board;
    private GameLog gameLog;
    private PlayerCards p1, p2;
    private final int glW, glH, plW, plH;
    private final Dimension SCREEN_SIZE = new Dimension(800, 700);
    private final SoundController soundController;

    public TripleTriadUI(List<CardData> cards) throws IOException {
        // Toador de fitas :)
        soundController = new SoundController("theme.wav");
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                soundController.stop();
            }
        });

        // Configurações de janela
        setTitle("Triple Triad");
        setSize(SCREEN_SIZE);

        // Configuração do tamanho do Painel de cartas dos jogadores e do Log (precisa estar depois de setSize(...))
        plW = (int) (getWidth() * 0.17);
        plH = getHeight();
        glW = getWidth();
        glH = (int) (getHeight() * 0.14);

        // Configurações de janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        var layout = new BorderLayout();
        layout.setVgap(10);
        layout.setHgap(10);
        setLayout(layout);
        
        //Instancia do gamelog para mensagens
        gameLog = new GameLog();

        // Wrapper para o campo
        JPanel boardWrapper = new JPanel(new BorderLayout());
        board = new Board("/back.png", cards);
        board.addCardAddedListener( e -> {
            gameLog.addLogMessage("A carta '" + e.getCard().getInfo().getName() + "' foi inserida!");
        });
        board.addPositionListener( (row, col) -> {
            gameLog.addLogMessage("A posição " + String.format("[%d, %d]", row, col) + " foi selecionada!");
        });
        boardWrapper.add(board, BorderLayout.CENTER);

        // Pode ser visto outra forma de pegar esses dados
        p1 = new PlayerCards(this, new Player("Harllem", cards.subList(0, 5), Color.decode("#08C2FF")), plW, plH);
        p2 = new PlayerCards(this, new Player("Neto", cards.subList(5, 10), Color.decode("#C96868")), plW, plH);

        // Só pra testes
        addExampleCardToBoard(cards, Color.decode("#fa6469"));

        // log do jogo
        gameLog.setPreferredSize(new Dimension(glW, glH));
        
        // Adiciona os componentes ao layout
        boardWrapper.setBorder(BorderFactory.createTitledBorder("Board"));
        p1.setBorder(BorderFactory.createTitledBorder("Jogador 1"));
        p2.setBorder(BorderFactory.createTitledBorder("Jogador 2"));
        gameLog.setBorder(BorderFactory.createTitledBorder("Log de Jogadas"));
        add(boardWrapper, BorderLayout.CENTER);
        add(p1, BorderLayout.WEST);
        add(p2, BorderLayout.EAST);
        add(gameLog, BorderLayout.SOUTH);

        // Configurações de janela
        setLocationRelativeTo(null);
        soundController.loop();
        soundController.play();
        setVisible(true);
    }

    private void addExampleCardToBoard(List<CardData> cards, Color color) {
        final var cardData = cards.get(10);
        cardData.setOwner(p1.getPlayer());
        var cardComponent = new CardComponent(cardData, e -> {
            gameLog.addLogMessage(String.format("A carta '%s' foi selecionada!", cardData.getName()));
        });
        board.add(cardComponent, 2, 2);

        final var cardData2 = cards.get(109);
        cardData2.setOwner(p2.getPlayer());
        cardComponent = new CardComponent(cardData2, e -> {
            gameLog.addLogMessage(String.format("A carta '%s' foi selecionada!", cardData2.getName()));
        });
        board.add(cardComponent, 1, 1);

        final var cardData3 = cards.get(80);
        cardData3.setOwner(p1.getPlayer());
        cardComponent = new CardComponent(cardData3, e -> {
            gameLog.addLogMessage(String.format("A carta '%s' foi selecionada!", cardData3.getName()));
        });
        board.add(cardComponent, 0, 0);
    }
}