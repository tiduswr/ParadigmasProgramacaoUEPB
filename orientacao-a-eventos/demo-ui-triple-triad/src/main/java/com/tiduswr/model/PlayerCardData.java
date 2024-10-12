package com.tiduswr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe que representa os dados de uma carta pertencente a um jogador.
 * 
 * Essa classe permite que a mesma carta seja usada por dois jogadores diferentes.
 */
@Getter 
@Setter 
@AllArgsConstructor
public class PlayerCardData {
    // Dados da carta
    private CardData cardData; 
    // Jogador que possui a carta
    private Player owner; 
    /* 
        Modificador utilizado para aplicar regras de jogo como Ascend e Descend. 
        Um valor positivo ou negativo deve ser atribuído aqui para influenciar 
        a comparação dos lados das cartas durante o jogo.
    */
    private int modifier; 
    // Indica se a carta está virada
    private boolean flipped; 
}