package com.tiduswr.view;

import com.tiduswr.model.PlayerCardData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CardAddedEvent {
    private PlayerCardData card;
    private PlayerCardData top;
    private PlayerCardData bottom;
    private PlayerCardData left;
    private PlayerCardData right;
}
