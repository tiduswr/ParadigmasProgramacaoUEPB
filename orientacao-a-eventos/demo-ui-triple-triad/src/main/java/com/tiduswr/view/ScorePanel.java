package com.tiduswr.view;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tiduswr.model.Player;

import lombok.Getter;

/**
 * Painel que exibe a pontuação dos jogadores em um jogo.
 * Mostra a pontuação atual de dois jogadores em um formato "Jogador 1 : Jogador 2".
 */
@Getter
public class ScorePanel extends JPanel {
    /** Rótulo que exibe a pontuação */
    private JLabel scoreLabel; 
    /** Jogadores cujas pontuações estão sendo exibidas */
    private Player p1, p2; 

    /**
     * Construtor da classe ScorePanel.
     * Inicializa o painel com os jogadores fornecidos e cria a interface de pontuação.
     *
     * @param p1 O primeiro jogador.
     * @param p2 O segundo jogador.
     */
    public ScorePanel(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        createScorePanel();
    }

    /**
     * Cria a interface do painel de pontuação.
     * Configura o layout, o rótulo e as propriedades visuais.
     */
    private void createScorePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        scoreLabel = new JLabel(String.format("%d : %d", 
            p1.getPoints(),
            p2.getPoints()
        ));
        scoreLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        setBorder(new EmptyBorder(5, 0, 0, 0));
        add(scoreLabel);
    }

    /**
     * Atualiza o rótulo de pontuação com os pontos atuais dos jogadores.
     * Este método deve ser chamado após atualizar a pontuação de p1 ou p2.
     */
    public void updateScores() {
        scoreLabel.setText(String.format("%d : %d", 
            p1.getPoints(),
            p2.getPoints()
        ));
    }
}