package com.tiduswr.view;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tiduswr.model.Player;

import lombok.Getter;

@Getter
public class ScorePanel extends JPanel{
    private JLabel scoreLabel;
    private Player p1, p2;

    public ScorePanel(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;
        createScorePanel();
    }

    private void createScorePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        scoreLabel = new JLabel(String.format("%d : %d", 
            p1.getPoints(),
            p2.getPoints()
        ));
        scoreLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        setBorder(new EmptyBorder(15,0,0,0));
        add(scoreLabel);
    }

    // Atualize p1 ou p2 e depois chame esse método
    public void updateScores() {
        scoreLabel.setText(String.format("%d : %d", 
            p1.getPoints(),
            p2.getPoints()
        ));
    }

}
