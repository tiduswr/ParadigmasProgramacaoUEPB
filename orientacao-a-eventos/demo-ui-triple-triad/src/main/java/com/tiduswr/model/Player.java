package com.tiduswr.model;

import java.awt.Color;
import java.util.List;

import lombok.Getter;

/**
 * Classe que representa um jogador no jogo Triple Triad.
 */
@Getter
public class Player {

    // Nome do jogador
    private String name; 
    // Pontos iniciais do jogador
    private final int points = 5; 
    // Cartas do jogador
    private List<CardData> cards; 
    // Cor associada ao jogador
    private Color color; 

    /**
     * Construtor da classe Player.
     * 
     * @param name  Nome do jogador.
     * @param cards Lista de cartas do jogador, que deve conter exatamente 5 cartas.
     * @param color Cor associada ao jogador.
     * @throws RuntimeException Se a lista de cartas não contiver exatamente 5 cartas.
     */
    public Player(String name, List<CardData> cards, Color color) {
        if(cards.size() != 5)
            throw new RuntimeException("A mão do jogador, inicialmente, precisa conter exatamente 5 cartas!");
        this.cards = cards;
        this.name = name;
        this.color = color;
    }
}
