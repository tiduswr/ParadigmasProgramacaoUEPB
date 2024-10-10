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

import com.tiduswr.model.CardData;
import com.tiduswr.model.Player;
import com.tiduswr.model.PlayerCardData;
import com.tiduswr.model.SoundService;

import lombok.Getter;

@Getter
public class TripleTriadUI extends JFrame {

    private Board board;
    private GameLog gameLog;
    private PlayerCards p1, p2;
    private final int glW, glH, plW, plH;
    private final Dimension SCREEN_SIZE = new Dimension(800, 800);
    private SoundService themeSoundService, selectionSoundService;

    public TripleTriadUI(List<CardData> cards, SoundService themeSoundServiceInjection, SoundService selectionSoundServiceInjection) throws IOException {
        this.themeSoundService = themeSoundServiceInjection;
        this.selectionSoundService = selectionSoundServiceInjection;

        // Tocador de fitas :)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                themeSoundService.close();
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

        // Instancia do gamelog para mensagens
        gameLog = new GameLog();

        // Wrapper para o campo
        JPanel boardWrapper = new JPanel(new BorderLayout());
        board = new Board("/back.png", cards);
        board.addCardAddedListener(e -> {
            gameLog.addLogMessage("A carta '" + e.getCard().getInfo().getCardData().getName() + "' foi inserida!");
        });
        board.addPositionListener((row, col) -> {
            gameLog.addLogMessage("A posição " + String.format("[%d, %d]", row, col) + " foi selecionada!");
        });
        // Exemplo de adição de efeito sonoro ao clicar
        board.addPositionListener((row, col) -> {
            selectionSoundService.play();
        });
        boardWrapper.add(board, BorderLayout.CENTER);

        // Pode ser visto outra forma de pegar esses dados
        p1 = new PlayerCards(this, new Player("José", 5, cards.subList(0, 5), Color.decode("#08C2FF")), plW, plH);
        p2 = new PlayerCards(this, new Player("Maria", 5, cards.subList(5, 10), Color.decode("#C96868")), plW, plH);
        p2.setCardsActive(false); // Você pode inativar uma mão de um jogador assim
        p2.processAllPlayerCardData((indice, carta) -> {
            carta.setFlipped(true); // Como você pode esconder cartas da mão do usuário
        });

        // Só pra testes
        exemploDeCartasNoCampo(cards, Color.decode("#fa6469"));

        // log do jogo
        gameLog.setPreferredSize(new Dimension(glW, glH));

        // Adiciona os componentes ao layout
        boardWrapper.setBorder(BorderFactory.createTitledBorder("Campo"));
        gameLog.setBorder(BorderFactory.createTitledBorder("Log de Jogadas"));
        add(boardWrapper, BorderLayout.CENTER);
        add(p1, BorderLayout.WEST);
        add(p2, BorderLayout.EAST);
        add(gameLog, BorderLayout.SOUTH);

        // Criação do painel de pontuação
        JPanel scorePanel = new ScorePanel(p1.getPlayer(), p2.getPlayer());
        add(scorePanel, BorderLayout.NORTH); // Adiciona o painel de pontuação no topo

        // Configurações de janela
        setLocationRelativeTo(null);
        themeSoundService.playThenLoop("theme-loop.wav");
        setVisible(true);
    }    

    private void exemploDeCartasNoCampo(List<CardData> cards, Color color) {
        final var cardData = new PlayerCardData(cards.get(0), p2.getPlayer(), false);
        var cardComponent = new CardComponent(cardData, null, 10);
        board.addCard(cardComponent, 2, 2);

        final var cardData2 = new PlayerCardData(cards.get(109), p2.getPlayer(), false);
        cardComponent = new CardComponent(cardData2, null, 10);
        board.addCard(cardComponent, 1, 1);

        final var cardData3 = new PlayerCardData(cards.get(80), p1.getPlayer(), false);
        cardData3.setOwner(p1.getPlayer());
        cardComponent = new CardComponent(cardData3, null, 10);
        board.addCard(cardComponent, 0, 0);
    }
}