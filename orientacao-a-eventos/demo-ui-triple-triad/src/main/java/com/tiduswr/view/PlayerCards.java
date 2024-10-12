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

/**
 * Componente que representa as cartas de um jogador em uma interface de jogo.
 * Permite a seleção e a interação com as cartas na mão do jogador.
 */
@Getter
public class PlayerCards extends JPanel {

    /** Jogador associado a estas cartas */
    private Player player; 

    /** Número de cartas na mão */
    private int handSize = 5; 

    /** Indica se as cartas estão ativas para seleção */
    private boolean cardsActive = true; 

    /** Índice da carta selecionada */
    private int selectedIndex; 

    /**
     * Construtor da classe PlayerCards.
     * Inicializa o painel com as cartas do jogador e configura os ouvintes de eventos.
     *
     * @param father A interface do jogo que contém este componente.
     * @param player O jogador associado a estas cartas.
     * @param width Largura preferida do painel.
     * @param height Altura preferida do painel.
     */
    public PlayerCards(TripleTriadUI father, Player player, int width, int height) {
        this.player = player;
        selectedIndex = -1;

        setLayout(new GridLayout(5, 1)); // Layout em grade para as cartas
        setPreferredSize(new Dimension(width, height)); // Define o tamanho preferido

        // Adiciona componentes de carta ao painel
        for (int i = 0; i < handSize; i++) {
            var index = i;
            var cardData = player.getCards().get(index);
            var playerCardData = new PlayerCardData(cardData, player, 0, false);
            
            var cardComponent = new CardComponent(playerCardData, e -> {
                if (!cardsActive || playerCardData.isFlipped())
                    return;

                father.getGameLog().addLogMessage(String.format("A carta '%s' foi selecionada!", cardData.getName()));
                father.getSoundServices().getSoundService("selection").play();
                selectedIndex = player.getCards().indexOf(cardData);
                updateBorders();
            });
            add(cardComponent);
        }

        setBorder(BorderFactory.createTitledBorder(getPlayer().getName())); // Adiciona borda com o nome do jogador

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

        // Exemplo de passar uma carta da mão para o campo
        father.getBoard().addPositionListener((row, col) -> {
            var selected = getSelected();
            if (selected != null) {
                if (!father.getBoard().addCard(selected, row, col)) {
                    father.getSoundServices().getSoundService("error").play();
                    father.getGameLog().addLogMessage(String.format("A posição [%d, %d] já possui uma carta!", row, col));
                } else {
                    father.getSoundServices().getSoundService("card-placed").play();
                    removeSelected();
                }
            }
        });
    }

    /**
     * Define se as cartas estão ativas para seleção.
     *
     * @param enabled True para ativar a seleção de cartas, false para desativar.
     */
    public void setCardsActive(boolean enabled) {
        this.cardsActive = enabled;
    }

    /**
     * Atualiza as bordas das cartas com base na seleção atual.
     */
    private void updateBorders() {
        for (int i = 0; i < handSize; i++) {
            var cardComponent = (CardComponent) getComponent(i);
            cardComponent.setCardIsSelected(i == selectedIndex); // Destaca a carta selecionada
        }
        revalidate();
        repaint();
    }

    /**
     * Obtém a carta atualmente selecionada.
     *
     * @return A carta selecionada, ou null se nenhuma carta estiver selecionada.
     */
    public PlayerCardData getSelected() {
        if (selectedIndex != -1) {
            return ((CardComponent) getComponent(selectedIndex)).getInfo();
        }
        return null;
    }

    /**
     * Remove a carta atualmente selecionada da mão do jogador.
     *
     * @return True se uma carta foi removida, false caso contrário.
     */
    public boolean removeSelected() {
        var selected = getSelected();

        if (selected == null)
            return false;

        CardComponent selectedCard = (CardComponent) getComponent(selectedIndex);
        player.getCards().removeIf(card -> card.equals(selectedCard.getInfo().getCardData()));
        selectedIndex = -1; // Reseta o índice selecionado
        handSize--; // Reduz o tamanho da mão

        remove(selectedCard);
        revalidate();
        repaint();

        return true;
    }

    /**
     * Obtém uma lista de todos os dados das cartas do jogador.
     *
     * @return Uma lista de objetos PlayerCardData.
     */
    private List<PlayerCardData> getAllPlayerCardData() {
        List<PlayerCardData> cardDataList = new ArrayList<>();
        for (int i = 0; i < getComponentCount(); i++) {
            CardComponent cardComponent = (CardComponent) getComponent(i);
            cardDataList.add(cardComponent.getInfo());
        }
        return cardDataList;
    }

    /**
     * Processa todos os dados das cartas do jogador, aplicando uma ação a cada um.
     *
     * @param action A ação a ser aplicada, representada como um BiConsumer que recebe o índice e os dados da carta.
     */
    public void processAllPlayerCardData(BiConsumer<Integer, PlayerCardData> action) {
        var cards = getAllPlayerCardData();

        for (int i = 0; i < cards.size(); i++) {
            action.accept(i, cards.get(i));
        }

        revalidate();
        repaint();
    }
}