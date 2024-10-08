package com.tiduswr.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.tiduswr.model.CardsReader;
import com.tiduswr.model.PlayerCardData;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CardComponent extends JButton {

    private PlayerCardData info;
    private boolean cardIsSelected;
    private static final BufferedImage selectedIcon = CardsReader.selectionIcon();
    private static final BufferedImage backCard = CardsReader.cardBack();
    private static final Color SELECTION_COLOR = Color.decode("#347928");
    private final int OFFSET;
    private final int COMPENSATION;

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
    }

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
            g2d.drawImage(backCard, OFFSET, OFFSET, w-COMPENSATION*2, h-COMPENSATION*2, this);
            drawSelection(g2d);

        } else {
            drawSelection(g2d);

            // Desenha o ícone de seleção no canto inferior esquerdo se o card estiver selecionado
            if (cardIsSelected) {
                g2d.drawImage(selectedIcon, OFFSET, getHeight() - 32 - OFFSET, 40, 25, this);
            }
            
            if (info != null) drawCardValues(g2d);
        }

        g2d.dispose();
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        if (info != null && !info.isFlipped())
            loadCard(width, height);
    }

    public void loadCard(int width, int height) {
        BufferedImage sprite = info.getCardData().getImage();

        int newWidth = width - 2 * OFFSET;
        int newHeight = height - 2 * OFFSET;

        // Criar a imagem cortada com o tamanho do componente
        BufferedImage croppedImage = new BufferedImage(newWidth+2, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = croppedImage.createGraphics();
        g2d.drawImage(sprite, -COMPENSATION, -COMPENSATION, newWidth-COMPENSATION*2, newHeight-COMPENSATION*2, 2, 2, sprite.getWidth() - 2, sprite.getHeight() - 2, null);
        g2d.dispose();

        // Criar uma imagem de fundo colorida com o tamanho do componente
        Color backgroundColor = info.getOwner().getColor();
        BufferedImage background = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2dBackground = background.createGraphics();
        g2dBackground.setColor(backgroundColor);
        g2dBackground.fillRect(0, 0, newWidth-6, newHeight-6);
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

    private void drawSelection(Graphics2D g2d){
        Color selectionColor = SELECTION_COLOR;
        if (getModel().isPressed()) {
            g2d.setColor(new Color(selectionColor.getRed(), selectionColor.getGreen(), selectionColor.getBlue(), 150));
        } else {
            g2d.setColor(new Color(255, 255, 255, 0));
        }
        g2d.fillRect(OFFSET, OFFSET, getWidth() - 2 * OFFSET, getHeight() - 2 * OFFSET);
    }

    private void drawCardValues(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        String upValue = info.getCardData().getUp() == 10 ? "A" : String.valueOf(info.getCardData().getUp());
        String downValue = info.getCardData().getDown() == 10 ? "A" : String.valueOf(info.getCardData().getDown());
        String leftValue = info.getCardData().getLeft() == 10 ? "A" : String.valueOf(info.getCardData().getRight());
        String rightValue = info.getCardData().getRight() == 10 ? "A" : String.valueOf(info.getCardData().getLeft());

        drawValue(g2d, String.valueOf(upValue), 24 + OFFSET, 23 + OFFSET);
        drawValue(g2d, String.valueOf(downValue), 24 + OFFSET, 46 + OFFSET);
        drawValue(g2d, String.valueOf(leftValue), 9 + OFFSET, 38 + OFFSET);
        drawValue(g2d, String.valueOf(rightValue), 39 + OFFSET, 38 + OFFSET);

        if (info.getCardData().getTypeIcon() != null) drawType(g2d);
    }

    private void drawType(Graphics2D g2d) {
        var icon = info.getCardData().getTypeIcon();
        var y = 10 + OFFSET;
        var x = getWidth() - (icon.getWidth() + 9 + OFFSET);

        g2d.drawImage(info.getCardData().getTypeIcon(), x, y, 25, 25, this);
    }

    private void drawValue(Graphics2D g2d, String value, int x, int y) {
        g2d.setFont(new Font("sans", Font.BOLD, 20));
        g2d.setColor(Color.WHITE); // Cor da borda
        float outlineThickness = 1.5f;

        // Desenhar o texto com contorno
        for (float dx = -outlineThickness; dx <= outlineThickness; dx += outlineThickness) {
            for (float dy = -outlineThickness; dy <= outlineThickness; dy += outlineThickness) {
                g2d.drawString(value, x + dx, y + dy);
            }
        }

        g2d.setColor(Color.BLACK);
        g2d.drawString(String.valueOf(value), x, y);
    }
}
