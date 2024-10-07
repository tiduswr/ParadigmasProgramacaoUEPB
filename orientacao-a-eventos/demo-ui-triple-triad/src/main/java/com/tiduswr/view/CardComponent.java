package com.tiduswr.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.tiduswr.model.CardData;
import com.tiduswr.model.CardsReader;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CardComponent extends JButton {

    private CardData info;
    private boolean cardIsSelected;
    private static final BufferedImage selectedIcon = CardsReader.selectionIcon();

    public CardComponent(CardData info, ActionListener listener) {
        this.info = info;
        this.cardIsSelected = false;
        setFocusPainted(false);
        addActionListener(listener);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    public static CardComponent EMPTY_CARD_COMPONENT() {
        return new CardComponent(null, null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isEnabled()) return;

        Graphics2D g2d = (Graphics2D) g.create();

        Color selectionColor = Color.decode("#347928");
        if (getModel().isPressed()) {
            g2d.setColor(new Color(selectionColor.getRed(), selectionColor.getGreen(), selectionColor.getBlue(), 150));
        } else {
            g2d.setColor(new Color(255, 255, 255, 0));
        }
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Desenha o ícone de seleção no canto inferior esquerdo se o card estiver selecionado
        if (cardIsSelected) {
            g2d.drawImage(selectedIcon, 7, getHeight() - 32, 40, 25, this);
        }

        if (info != null) drawCardValues(g2d);

        g2d.dispose();
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        if (info != null)
            loadCard(width, height);
    }

    public void loadCard(int width, int height) {
        BufferedImage sprite = info.getImage();

        int newWidth = width;
        int newHeight = height;

        // Criar a imagem cortada com o tamanho do componente
        BufferedImage croppedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = croppedImage.createGraphics();
        g2d.drawImage(sprite, 0, 0, newWidth, newHeight, 2, 2, sprite.getWidth() - 2, sprite.getHeight() - 2, null);
        g2d.dispose();

        // Criar uma imagem de fundo colorida com o tamanho do componente
        Color backgroundColor = info.getOwner().getColor();
        BufferedImage background = new BufferedImage(newWidth - 5, newHeight - 5, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2dBackground = background.createGraphics();
        g2dBackground.setColor(backgroundColor);
        g2dBackground.fillRect(5, 5, newWidth, newHeight);
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

    private void drawCardValues(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        String upValue = info.getUp() == 10 ? "A" : String.valueOf(info.getUp());
        String downValue = info.getDown() == 10 ? "A" : String.valueOf(info.getDown());
        String leftValue = info.getLeft() == 10 ? "A" : String.valueOf(info.getRight());
        String rightValue = info.getRight() == 10 ? "A" : String.valueOf(info.getLeft());

        drawValue(g2d, String.valueOf(upValue), 24, 23);
        drawValue(g2d, String.valueOf(downValue), 24, 46);
        drawValue(g2d, String.valueOf(leftValue), 9, 38);
        drawValue(g2d, String.valueOf(rightValue), 39, 38);

        if (info.getTypeIcon() != null) drawType(g2d);

        g2d.dispose();
    }

    private void drawType(Graphics2D g2d) {
        var icon = info.getTypeIcon();
        var y = 10;
        var x = getWidth() - (icon.getWidth() + 9);

        g2d.drawImage(info.getTypeIcon(), x, y, 25, 25, this);
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

        g2d.setColor(Color.BLACK); // Cor do texto
        g2d.drawString(String.valueOf(value), x, y);
    }

}
