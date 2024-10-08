package com.tiduswr.model;

import java.awt.image.BufferedImage;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CardData {

    private BufferedImage image;
    private int cardId;
    private String name;
    private int up;
    private int down; 
    private int left;
    private int right;
    private String Type;
    private BufferedImage typeIcon;
    
}
