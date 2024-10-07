package com.tiduswr.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.tiduswr.model.Player;

import lombok.Getter;

@Getter
public class PlayerCards extends JPanel {
    
    private Player player;
    private int handSize = 5;
    private int selectedIndex;

    public PlayerCards(JFrame father, Player player, int width, int height) {
        this.player = player;
        selectedIndex = -1;
        handSize = player.getCards().size() <= 5 ? player.getCards().size() : 5;

        setLayout(new GridLayout(5, 1));
        setPreferredSize(new Dimension(width, height));
        setFocusable(true); // Permite que o painel receba foco

        for (int i = 0; i < handSize; i++) {
            var cardData = player.getCards().get(i);
            cardData.setOwner(player);
            
            var cardComponent = new CardComponent(cardData, e -> {
                selectedIndex = player.getCards().indexOf(cardData);
                updateBorders(); // Atualiza as bordas quando uma carta é selecionada
            });

            // Define uma borda inicial
            cardComponent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add(cardComponent);
        }

        // Listener para resetar seleção ao clicar fora das cartas
        father.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!contains(e.getPoint()) && selectedIndex != -1) {
                    selectedIndex = -1;
                    updateBorders();
                }
            }
        });
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for(var componente : getComponents()){
            componente.setEnabled(enabled);
        }
    }

    private void updateBorders() {
        for (int i = 0; i < handSize; i++) {
            var cardComponent = (CardComponent) getComponent(i);
            cardComponent.setCardIsSelected(i == selectedIndex);
        }
        revalidate();
        repaint();
    }
}
