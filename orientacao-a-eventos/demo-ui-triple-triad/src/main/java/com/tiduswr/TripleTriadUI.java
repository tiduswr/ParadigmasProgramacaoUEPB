package com.tiduswr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TripleTriadUI extends JFrame {

    private JButton[][] boardButtons;
    private BackgroundPanel boardPanel;
    private JPanel controlPanel;
    private JLabel statusLabel;

    private SpriteSheet spriteSheet;

    public TripleTriadUI() throws IOException {
        setTitle("Triple Triad");
        setSize(596, 674);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        var layout = new BorderLayout();
        layout.setVgap(10);
        setLayout(layout);
        boardButtons = new JButton[3][3];
        boardPanel = new BackgroundPanel("/back.png");
        boardPanel.setLayout(new GridLayout(3, 3));
        controlPanel = new JPanel();
        statusLabel = new JLabel("Bem-vindo ao Triple Triad!");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        spriteSheet = new SpriteSheet("/spritesheet.png", 192, 192);
        spriteSheet.loadSprites();
        
        initializeBoard();
        initializeControlPanel();

        add(boardPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                var bt = new JButton() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        if (getModel().isPressed()) {
                            g.setColor(new Color(102, 0, 102, 128));
                        } else {
                            g.setColor(new Color(255, 255, 255, 0));
                        }
                        g.fillRect(0, 0, getWidth(), getHeight());
                    }
                };
                bt.setFocusPainted(false);
                bt.addActionListener(new BoardButtonListener(i, j));
                bt.setOpaque(false);
                bt.setContentAreaFilled(false);
                bt.setBorderPainted(false);
    
                boardButtons[i][j] = bt;
                boardPanel.add(boardButtons[i][j]);
            }
        }
    }

    private void initializeControlPanel() {
        JButton resetButton = new JButton("Resetar Campo");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });
        controlPanel.add(resetButton);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardButtons[i][j].setIcon(null);
                boardButtons[i][j].setText("");
            }
        }
        statusLabel.setText("Tabuleiro resetado!");
    }

    private class BoardButtonListener implements ActionListener {
        private int row;
        private int col;

        public BoardButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = boardButtons[row][col];
            loadRandomCard(button);
            statusLabel.setText("Carta colocada em " + row + ", " + col);
        }
    }

    private void loadRandomCard(JButton button) {
        int cardIndex = new Random().nextInt(16);
        BufferedImage sprite = spriteSheet.getSprite(cardIndex);
    
        int newWidth = sprite.getWidth() - 5;
        int newHeight = sprite.getHeight() - 5;
        BufferedImage croppedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = croppedImage.createGraphics();
        g2d.drawImage(sprite, 0, 0, newWidth, newHeight, 2, 2, newWidth + 2, newHeight + 2, null);
        g2d.dispose();

        // Criar uma imagem de fundo colorida
        Color backgroundColor = getRandomColor();
        BufferedImage background = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2dBackground = background.createGraphics();
        g2dBackground.setColor(backgroundColor);
        g2dBackground.fillRect(0, 0, newWidth, newHeight);
        g2dBackground.dispose();
    
        // Mesclar a imagem de fundo com a imagem cortada
        BufferedImage mergedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2dMerged = mergedImage.createGraphics();
        g2dMerged.drawImage(background, 0, 0, null);
        g2dMerged.drawImage(croppedImage, 0, 0, null);
        g2dMerged.dispose();
    
        // Definir a imagem mesclada como o ícone do botão
        button.setIcon(new ImageIcon(mergedImage));
    }
    
    private Color getRandomColor() {
        Random random = new Random();
        int colorIndex = random.nextInt(2);
        if (colorIndex == 0) {
            return new Color(116, 171, 255, 200);
        } else {
            return new Color(255, 125, 116, 200);
        }
    }
}