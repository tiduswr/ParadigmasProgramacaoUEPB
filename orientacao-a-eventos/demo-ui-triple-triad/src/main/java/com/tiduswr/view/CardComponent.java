package com.tiduswr.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.tiduswr.model.CardsReader;
import com.tiduswr.model.PlayerCardData;

import lombok.Getter;
import lombok.Setter;

/**
 * Componente de carta que representa uma carta do jogo.
 * Herda de JButton e fornece funcionalidades adicionais para exibir informações da carta.
 */
@Setter @Getter
public class CardComponent extends JButton {

    /** Informação da carta */
    private PlayerCardData info; 
    
    /** Indica se a carta está selecionada */
    private boolean cardIsSelected; 
    
    /** Ícone para seleção de carta */
    private static final BufferedImage selectedIcon = CardsReader.selectionIcon(); 
    
    /** Imagem de fundo da carta */
    private static final BufferedImage backCard = CardsReader.cardBack(); 
    
    /** Fonte usada para desenhar valores */
    private static final Font font = new Font("sans", Font.BOLD, 18); 
    
    /** Deslocamento para renderização */
    private final int OFFSET; 
    
    /** Compensação para renderização */
    private final int COMPENSATION; 

    /** Posição do clique em X */
    private int clickX = -1; 
    
    /** Posição do clique em Y */
    private int clickY = -1; 

    /**
     * Construtor da classe CardComponent.
     *
     * @param info Informações da carta.
     * @param listener Listener de ação para o botão.
     */
    public CardComponent(PlayerCardData info, ActionListener listener) {
        OFFSET = 0;
        COMPENSATION = 2;
        this.info = info;
        this.cardIsSelected = false;
        setFocusPainted(false);
        addActionListener(listener);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                clickX = e.getX();
                clickY = e.getY();
                repaint();
            }
        });
    }

    /**
     * Construtor da classe CardComponent com deslocamento personalizado.
     *
     * @param info Informações da carta.
     * @param listener Listener de ação para o botão.
     * @param offset Deslocamento para renderização.
     */
    public CardComponent(PlayerCardData info, ActionListener listener, int offset) {
        OFFSET = offset;
        COMPENSATION = 2;
        this.info = info;
        this.cardIsSelected = false;
        setFocusPainted(false);
        addActionListener(listener);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                clickX = e.getX();
                clickY = e.getY();
                repaint();
            }
        });
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isEnabled()) return;

        Graphics2D g2d = (Graphics2D) g.create();

        if (info != null && info.isFlipped()) {
            var w = getWidth() - 2 * OFFSET;
            var h = getHeight() - 2 * OFFSET;
            g2d.drawImage(backCard, OFFSET, OFFSET, w - COMPENSATION * 2, h - COMPENSATION * 2, this);
        } else {
            // Desenha o ícone de seleção no canto inferior esquerdo se a carta estiver selecionada
            if (cardIsSelected) {
                g2d.drawImage(selectedIcon, OFFSET, getHeight() - 32 - OFFSET, 40, 25, this);
            }
            
            if (info != null) {
                drawCardValues(g2d);
                
                // Desenha o valor de modificador (Ascend ou Descend)
                if (info.getModifier() != 0) {
                    var strModifier = info.getModifier() > 0 ? "+" + Integer.toString(info.getModifier()) : Integer.toString(info.getModifier());
                    var color = info.getModifier() < 0 ? Color.RED : Color.decode("#08a833");
                    var metrics = g.getFontMetrics(font);
                    var fontWidth = metrics.stringWidth(strModifier);
                    var fontHeight = metrics.getHeight();
                    var xOffset = 15;

                    drawValue(g2d, strModifier, getWidth() - (fontWidth + xOffset) - OFFSET, getHeight() - fontHeight - OFFSET, color);
                }
            }
            drawSelection(g2d);
        }

        g2d.dispose();
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        if (info != null && !info.isFlipped())
            loadCard(width, height);
    }

    /**
     * Carrega a carta no componente com o tamanho especificado.
     *
     * @param width Largura do componente.
     * @param height Altura do componente.
     */
    public void loadCard(int width, int height) {
        BufferedImage sprite = info.getCardData().getImage();

        int newWidth = width - 2 * OFFSET;
        int newHeight = height - 2 * OFFSET;

        // Criar a imagem cortada com o tamanho do componente
        BufferedImage croppedImage = new BufferedImage(newWidth + 2, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = croppedImage.createGraphics();
        g2d.drawImage(sprite, -COMPENSATION, -COMPENSATION, newWidth - COMPENSATION * 2, newHeight - COMPENSATION * 2, 2, 2, sprite.getWidth() - 2, sprite.getHeight() - 2, null);
        g2d.dispose();

        // Criar uma imagem de fundo colorida com o tamanho do componente
        Color backgroundColor = info.getOwner().getColor();
        BufferedImage background = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2dBackground = background.createGraphics();
        g2dBackground.setColor(backgroundColor);
        g2dBackground.fillRect(0, 0, newWidth - 6, newHeight - 6);
        g2dBackground.dispose();

        // Mesclar a imagem de fundo com a imagem cortada
        BufferedImage mergedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2dMerged = mergedImage.createGraphics();
        g2dMerged.drawImage(background, 0, 0, null);
        g2dMerged.drawImage(croppedImage, 0, 0, null);
        g2dMerged.dispose();

        // Definir a imagem mesclada como o ícone do botão
        setIcon(new ImageIcon(mergedImage));
    }

    /**
     * Desenha a seleção no componente quando pressionado.
     *
     * @param g2d O objeto Graphics2D usado para desenhar.
     */
    private void drawSelection(Graphics2D g2d) {
        if (getModel().isPressed()) {
            if (clickX >= 0 && clickY >= 0) {
                g2d.drawImage(selectedIcon, clickX - 40, clickY - 10, 40, 25, this);
            }
        }
    }

    /**
     * Converte o valor da carta para uma representação adequada.
     *
     * @param value O valor da carta.
     * @return A representação em string do valor.
     */
    private String convertSideValue(int value) {
        return value == 10 ? "A" : String.valueOf(value);
    }

    /**
     * Desenha os valores da carta no componente.
     *
     * @param g O objeto Graphics usado para desenhar.
     */
    private void drawCardValues(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        String upValue = convertSideValue(info.getCardData().getUp());
        String downValue = convertSideValue(info.getCardData().getDown());
        String leftValue = convertSideValue(info.getCardData().getLeft());
        String rightValue = convertSideValue(info.getCardData().getRight());

        drawValue(g2d, upValue, 24 + OFFSET, 23 + OFFSET, Color.BLACK);
        drawValue(g2d, downValue, 24 + OFFSET, 46 + OFFSET, Color.BLACK);
        drawValue(g2d, leftValue, 9 + OFFSET, 38 + OFFSET, Color.BLACK);
        drawValue(g2d, rightValue, 39 + OFFSET, 38 + OFFSET, Color.BLACK);

        if (info.getCardData().getTypeIcon() != null) drawType(g2d);
    }

    /**
     * Desenha o ícone do tipo de carta no componente.
     *
     * @param g2d O objeto Graphics2D usado para desenhar.
     */
    private void drawType(Graphics2D g2d) {
        var icon = info.getCardData().getTypeIcon();
        var y = 10 + OFFSET;
        var x = getWidth() - (icon.getWidth() + 15 + OFFSET);

        g2d.drawImage(info.getCardData().getTypeIcon(), x, y, 25, 25, this);
    }

    /**
     * Desenha o valor da carta no componente com borda.
     *
     * @param g2d O objeto Graphics2D usado para desenhar.
     * @param value O valor a ser desenhado.
     * @param x A posição X onde o valor deve ser desenhado.
     * @param y A posição Y onde o valor deve ser desenhado.
     * @param color A cor do texto.
     */
    private void drawValue(Graphics2D g2d, String value, int x, int y, Color color) {
        g2d.setFont(font);
        g2d.setColor(Color.WHITE); // Cor da borda
        float outlineThickness = 1.5f;

        // Desenhar o texto com contorno
        for (float dx = -outlineThickness; dx <= outlineThickness; dx += outlineThickness) {
            for (float dy = -outlineThickness; dy <= outlineThickness; dy += outlineThickness) {
                g2d.drawString(value, x + dx, y + dy);
            }
        }

        g2d.setColor(color);
        g2d.drawString(String.valueOf(value), x, y);
    }
}