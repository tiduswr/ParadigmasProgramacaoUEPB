package com.tiduswr.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CardAddedEvent {
    private CardComponent card;
    private CardComponent top;
    private CardComponent bottom;
    private CardComponent left;
    private CardComponent right;
}
