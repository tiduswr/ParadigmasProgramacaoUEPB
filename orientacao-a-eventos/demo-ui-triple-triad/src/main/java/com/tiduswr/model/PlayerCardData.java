package com.tiduswr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//Pra ser possivel usar a mesma carta por duas pessoas diferentes
@Getter @Setter @AllArgsConstructor
public class PlayerCardData {
    private CardData cardData;
    private Player owner;
    private boolean flipped;
}
