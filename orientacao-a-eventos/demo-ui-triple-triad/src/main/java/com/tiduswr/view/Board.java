package com.tiduswr.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import com.tiduswr.model.CardData;
import com.tiduswr.model.PlayerCardData;
import com.tiduswr.view.listeners.CardAddedListener;
import com.tiduswr.view.listeners.PositionListener;

import lombok.Getter;

/**
 * Classe que representa o tabuleiro do jogo, onde as cartas são dispostas.
 */
@Getter
public class Board extends BackgroundPanel {

    /** 
     * Array para representar o campo do jogo 
     */
    private CardComponent[][] boardButtons;
    
    /** 
     * Lista de todas as cartas carregadas do arquivo CSV 
     */
    private List<CardData> allCards;
    
    /** 
     * Listeners para o evento de Carta Adicionada ao Campo 
     */
    private List<CardAddedListener> cardAddedListeners;
    
    /** 
     * Listeners para o evento de Posição selecionada no campo 
     */
    private List<PositionListener> positionListeners;

    /**
     * Construtor que inicializa o tabuleiro com um fundo e uma lista de cartas.
     *
     * @param filename O nome do arquivo da imagem de fundo do tabuleiro.
     * @param allCards A lista de todas as cartas disponíveis.
     */
    public Board(String filename, List<CardData> allCards) {
        super(filename);
        boardButtons = new CardComponent[3][3];
        setLayout(new GridLayout(3, 3));
        this.allCards = allCards;
        this.cardAddedListeners = new ArrayList<>();
        this.positionListeners = new ArrayList<>();
        initializeBoard();
    }

    /**
     * Adiciona um ouvinte que será notificado quando uma carta for adicionada.
     *
     * @param listener O ouvinte a ser adicionado.
     */
    public void addCardAddedListener(CardAddedListener listener) {
        cardAddedListeners.add(listener);
    }

    /**
     * Adiciona um ouvinte que será notificado quando uma posição for clicada.
     *
     * @param listener O ouvinte a ser adicionado.
     */
    public void addPositionListener(PositionListener listener) {
        positionListeners.add(listener);
    }

    /**
     * Adiciona uma carta na posição especificada do tabuleiro.
     *
     * @param cardData Os dados da carta a ser adicionada.
     * @param row A linha onde a carta será adicionada.
     * @param col A coluna onde a carta será adicionada.
     * @return True se a carta foi adicionada com sucesso, caso contrário, false.
     */
    public boolean addCard(PlayerCardData cardData, int row, int col) {
        if (boardButtons[row][col].getInfo() != null)
            return false;
        boardButtons[row][col].setInfo(cardData);    
        configureCardAddedListeners(cardData, row, col);
    
        redrawCards();
    
        return true;
    }

    /**
     * Atualiza a exibição das cartas no tabuleiro.
     */
    private void redrawCards(){
        removeAll();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                add(boardButtons[i][j]);
            }
        }

        revalidate();
        repaint();
    }

    /**
     * Configura os ouvintes para serem notificados quando uma carta for adicionada.
     *
     * @param card Os dados da carta adicionada.
     * @param row A linha onde a carta foi adicionada.
     * @param col A coluna onde a carta foi adicionada.
     */
    private void configureCardAddedListeners(PlayerCardData card, int row, int col){
        CardAddedEvent event = createCardAddedEvent(card, row, col);        
        cardAddedListeners.forEach(listener -> {
            listener.onCardAdded(event);
        });
    }

    /**
     * Notifica todos os ouvintes sobre um clique em uma posição do tabuleiro.
     *
     * @param row A linha da posição clicada.
     * @param col A coluna da posição clicada.
     */
    private void notifyPositionListeners(int row, int col) {
        positionListeners.forEach(listener -> {
            listener.onPositionClicked(row, col);
        });
    }

    /**
     * Cria um evento de adição de carta com informações sobre as cartas adjacentes.
     *
     * @param card Os dados da carta adicionada.
     * @param row A linha da carta adicionada.
     * @param col A coluna da carta adicionada.
     * @return O evento de adição de carta criado.
     */
    private CardAddedEvent createCardAddedEvent(PlayerCardData card, int row, int col) {
        PlayerCardData top = (row > 0) ? boardButtons[row - 1][col].getInfo() : null;
        PlayerCardData bottom = (row < 2) ? boardButtons[row + 1][col].getInfo() : null;
        PlayerCardData left = (col > 0) ? boardButtons[row][col - 1].getInfo() : null;
        PlayerCardData right = (col < 2) ? boardButtons[row][col + 1].getInfo() : null;

        return new CardAddedEvent(card, top, bottom, left, right);
    }

    /**
     * Reinicia o tabuleiro, removendo todas as cartas.
     */
    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boardButtons[i][j] != null) {
                    boardButtons[i][j].setInfo(null);
                }
            }
        }
        repaint();
    }

    /**
     * Inicializa o tabuleiro criando os componentes de carta.
     */
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int row = i;
                final int col = j;
                var card = new CardComponent(null, e -> {
                    notifyPositionListeners(row, col);
                }, 10);
                boardButtons[i][j] = card;
                add(card);
            }
        }
        revalidate();
    }

    /**
     * Obtém o array de componentes de cartas do tabuleiro.
     *
     * @return O array de componentes de cartas.
     */
    public CardComponent[][] getBoardCards() {
        return boardButtons;
    }
}