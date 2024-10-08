package com.tiduswr.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import com.tiduswr.model.CardData;
import com.tiduswr.view.listeners.CardAddedListener;
import com.tiduswr.view.listeners.PositionListener;

import lombok.Getter;

@Getter
public class Board extends BackgroundPanel {

    private CardComponent[][] boardButtons;
    private List<CardData> allCards;
    private List<CardAddedListener> cardAddedListeners;
    private List<PositionListener> positionListeners;

    public Board(String filename, List<CardData> allCards) {
        super(filename);
        boardButtons = new CardComponent[3][3];
        setLayout(new GridLayout(3, 3));
        this.allCards = allCards;
        this.cardAddedListeners = new ArrayList<>();
        this.positionListeners = new ArrayList<>();
        initializeBoard();
    }

    public void addCardAddedListener(CardAddedListener listener) {
        cardAddedListeners.add(listener);
    }

    public void addPositionListener(PositionListener listener) {
        positionListeners.add(listener);
    }

    public boolean addCard(CardComponent card, int row, int col) {
        if (boardButtons[row][col].getInfo() != null)
            return false;

        boardButtons[row][col] = card;
        
        configureCardAddedListeners(card, row, col);

        card.addActionListener(e -> {
            notifyPositionListeners(row, col);
        });

        redrawCards();

        return true;
    }

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

    private void configureCardAddedListeners(CardComponent card, int row, int col){
        CardAddedEvent event = createCardAddedEvent(card, row, col);        
        cardAddedListeners.forEach(listener -> {
            listener.onCardAdded(event);
        });
    }

    private void notifyPositionListeners(int row, int col) {
        positionListeners.forEach(listener -> {
            listener.onPositionClicked(row, col);
        });
    }

    private CardAddedEvent createCardAddedEvent(CardComponent card, int row, int col) {
        CardComponent top = (row > 0) ? boardButtons[row - 1][col] : null;
        CardComponent bottom = (row < 2) ? boardButtons[row + 1][col] : null;
        CardComponent left = (col > 0) ? boardButtons[row][col - 1] : null;
        CardComponent right = (col < 2) ? boardButtons[row][col + 1] : null;

        return new CardAddedEvent(card, top, bottom, left, right);
    }

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

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int row = i;
                final int col = j;
                var card = new CardComponent(null, e -> {
                    notifyPositionListeners(row, col);
                });
                boardButtons[i][j] = card;
                add(card);
            }
        }
        revalidate();
    }

    public CardComponent[][] getBoardCards() {
        return boardButtons;
    }
}