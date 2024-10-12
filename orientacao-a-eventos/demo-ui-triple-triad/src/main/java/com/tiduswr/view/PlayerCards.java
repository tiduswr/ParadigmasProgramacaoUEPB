package com.tiduswr.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.tiduswr.model.Player;
import com.tiduswr.model.PlayerCardData;

import lombok.Getter;

@Getter
public class PlayerCards extends JPanel {
    
    private Player player;
    private int handSize = 5;
    private boolean cardsActive = true;
    private int selectedIndex;

    public PlayerCards(TripleTriadUI father, Player player, int width, int height) {
        this.player = player;
        selectedIndex = -1;

        setLayout(new GridLayout(5, 1));
        setPreferredSize(new Dimension(width, height));

        for (int i = 0; i < handSize; i++) {
            var index = i;
            var cardData = player.getCards().get(index);
            var playerCardData = new PlayerCardData(cardData, player, 0, false);
            
            var cardComponent = new CardComponent(playerCardData, e -> {
                if(!cardsActive || playerCardData.isFlipped())
                    return;

                father.getGameLog().addLogMessage(String.format("A carta '%s' foi selecionada!", cardData.getName()));
                father.getSoundServices().getSoundService("selection").play();
                selectedIndex = player.getCards().indexOf(cardData);
                updateBorders();
            });
            add(cardComponent);
        }

        setBorder(BorderFactory.createTitledBorder(getPlayer().getName()));

        // Listener para resetar seleção ao clicar fora das cartas
        father.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!contains(e.getPoint()) && selectedIndex != -1 && cardsActive) {
                    selectedIndex = -1;
                    updateBorders();
                }
            }
        });

        //Exemplo de passar uma carta da mão para o campo
        father.getBoard().addPositionListener((row, col) -> {
            var selected = getSelected();
            if(selected != null){
                if(!father.getBoard().addCard(selected, row, col)){       
                    father.getSoundServices().getSoundService("error").play();             
                    father.getGameLog().addLogMessage(String.format("A posição [%d, %d] ja possui uma carta!", row, col));
                }else{
                    father.getSoundServices().getSoundService("card-placed").play();
                    removeSelected();
                }
            }
        });
    }

    public void setCardsActive(boolean enabled) {
        this.cardsActive = enabled;
    }

    private void updateBorders() {
        for (int i = 0; i < handSize; i++) {
            var cardComponent = (CardComponent) getComponent(i);
            cardComponent.setCardIsSelected(i == selectedIndex);
        }
        revalidate();
        repaint();
    }

    public PlayerCardData getSelected() {
        if (selectedIndex != -1) {
            return ((CardComponent) getComponent(selectedIndex)).getInfo();
        }
        return null;
    }

    public boolean removeSelected() {
        var selected = getSelected();

        if(selected == null) 
            return false;

        CardComponent selectedCard = (CardComponent) getComponent(selectedIndex);
        player.getCards().removeIf(card -> card.equals(selectedCard.getInfo().getCardData()));
        selectedIndex = -1;
        handSize--;

        remove(selectedCard);
        revalidate();
        repaint();

        return true;
    }    

    private List<PlayerCardData> getAllPlayerCardData() {
        List<PlayerCardData> cardDataList = new ArrayList<>();
        for (int i = 0; i < getComponentCount(); i++) {
            CardComponent cardComponent = (CardComponent) getComponent(i);
            cardDataList.add(cardComponent.getInfo());
        }
        return cardDataList;
    }

    public void processAllPlayerCardData(BiConsumer<Integer, PlayerCardData> action){
        var cards = getAllPlayerCardData();

        for(int i = 0; i < cards.size(); i++){
            action.accept(i, cards.get(i));
        }
        
        revalidate();
        repaint();
    }
}
