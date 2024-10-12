package com.tiduswr.view;

import com.tiduswr.model.PlayerCardData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe que representa um evento de adição de uma carta no tabuleiro.
 * Contém informações sobre a carta adicionada e suas cartas adjacentes.
 */
@Getter
@Setter
@AllArgsConstructor
public class CardAddedEvent {
    // A carta que foi adicionada
    private PlayerCardData card; 
    // A carta acima da carta adicionada   
    private PlayerCardData top;   
    // A carta abaixo da carta adicionada  
    private PlayerCardData bottom;  
    // A carta à esquerda da carta adicionada
    private PlayerCardData left;  
    // A carta à direita da carta adicionada  
    private PlayerCardData right;   
}
