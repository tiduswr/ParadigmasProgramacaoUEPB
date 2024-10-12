package com.tiduswr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//Pra ser possivel usar a mesma carta por duas pessoas diferentes
@Getter @Setter @AllArgsConstructor
public class PlayerCardData {
    private CardData cardData;
    private Player owner;
    /* 
        Para trabalhar com as regras Ascend e Descend, 
        só basta que você coloque um valor positivo ou negativo 
        aqui e some na hora de comparar os lados das cartas 
    */
    private int modifier; 
    private boolean flipped;
}
