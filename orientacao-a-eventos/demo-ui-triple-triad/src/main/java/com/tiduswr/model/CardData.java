// Generated by delombok at Sat Oct 12 00:30:20 BRT 2024
package com.tiduswr.model;

import java.awt.image.BufferedImage;

/**
 * Classe que representa os dados de uma carta do jogo.
 */
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

    /**
     * Imagem da carta.
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * Identificador único da carta.
     */
    public int getCardId() {
        return this.cardId;
    }

    /**
     * Nome da carta.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Valor da carta na direção para cima.
     */
    public int getUp() {
        return this.up;
    }

    /**
     * Valor da carta na direção para baixo.
     */
    public int getDown() {
        return this.down;
    }

    /**
     * Valor da carta na direção para a esquerda.
     */
    public int getLeft() {
        return this.left;
    }

    /**
     * Valor da carta na direção para a direita.
     */
    public int getRight() {
        return this.right;
    }

    /**
     * Tipo da carta.
     */
    public String getType() {
        return this.Type;
    }

    /**
     * Ícone representando o tipo da carta.
     */
    public BufferedImage getTypeIcon() {
        return this.typeIcon;
    }

    /**
     * Imagem da carta.
     */
    public void setImage(final BufferedImage image) {
        this.image = image;
    }

    /**
     * Identificador único da carta.
     */
    public void setCardId(final int cardId) {
        this.cardId = cardId;
    }

    /**
     * Nome da carta.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Valor da carta na direção para cima.
     */
    public void setUp(final int up) {
        this.up = up;
    }

    /**
     * Valor da carta na direção para baixo.
     */
    public void setDown(final int down) {
        this.down = down;
    }

    /**
     * Valor da carta na direção para a esquerda.
     */
    public void setLeft(final int left) {
        this.left = left;
    }

    /**
     * Valor da carta na direção para a direita.
     */
    public void setRight(final int right) {
        this.right = right;
    }

    /**
     * Tipo da carta.
     */
    public void setType(final String Type) {
        this.Type = Type;
    }

    /**
     * Ícone representando o tipo da carta.
     */
    public void setTypeIcon(final BufferedImage typeIcon) {
        this.typeIcon = typeIcon;
    }
}
