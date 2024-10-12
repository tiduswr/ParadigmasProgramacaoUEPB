package com.tiduswr.model;

import java.awt.Color;
import java.util.List;

import lombok.Getter;

@Getter
public class Player {

    private String name;
    private final int points = 5;
    private List<CardData> cards;
    private Color color;

    public Player(String name, List<CardData> cards, Color color) {
        if(cards.size() != 5)
            throw new RuntimeException("A mão do jogador, inicialmente, precisa conter exatamente 5 cartas!");
        this.cards = cards;
        this.name = name;
        this.color = color;
    }

}
