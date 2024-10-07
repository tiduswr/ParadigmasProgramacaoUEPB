package com.tiduswr.model;

import java.awt.Color;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class Player {

    private String name;
    private List<CardData> cards;
    private Color color;    

}
