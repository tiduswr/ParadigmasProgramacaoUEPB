package com.tiduswr.model;

import java.awt.image.BufferedImage;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe que representa os dados de uma carta do jogo.
 */
@Getter @Setter
public class CardData {

    /**
     * Imagem da carta.
     */
    private BufferedImage image;

    /**
     * Identificador único da carta.
     */
    private int cardId;

    /**
     * Nome da carta.
     */
    private String name;

    /**
     * Valor da carta na direção para cima.
     */
    private int up;

    /**
     * Valor da carta na direção para baixo.
     */
    private int down; 

    /**
     * Valor da carta na direção para a esquerda.
     */
    private int left;

    /**
     * Valor da carta na direção para a direita.
     */
    private int right;

    /**
     * Tipo da carta.
     */
    private String Type;

    /**
     * Ícone representando o tipo da carta.
     */
    private BufferedImage typeIcon;
}