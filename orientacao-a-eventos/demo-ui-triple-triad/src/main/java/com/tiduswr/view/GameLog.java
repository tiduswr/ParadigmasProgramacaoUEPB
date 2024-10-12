package com.tiduswr.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * Componente que representa o registro de mensagens do jogo.
 * Exibe mensagens de log com timestamp em um painel com rolagem.
 */
public class GameLog extends JPanel {
    /** Área onde as mensagens de log são exibidas */
    private JTextPane logArea; 
    
    /**
     * Construtor da classe GameLog.
     * Inicializa o painel e a área de texto para exibir mensagens de log.
     */
    public GameLog() {
        setLayout(new BorderLayout());
        
        logArea = new JTextPane();
        logArea.setEditable(false); // A área de texto não é editável
        logArea.setContentType("text/html"); // Permite o uso de estilos

        JScrollPane scrollPane = new JScrollPane(logArea); // Adiciona rolagem
        add(scrollPane, BorderLayout.CENTER); // Adiciona a área de rolagem ao painel
    }

    /**
     * Adiciona uma mensagem de log à área de log com um timestamp.
     *
     * @param message A mensagem a ser adicionada ao log.
     */
    public void addLogMessage(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter); // Formata a data e hora atual

        Style style = logArea.addStyle("Bold", null); // Define um estilo para o timestamp
        StyleConstants.setBold(style, true); // Define o estilo como negrito
        StyleConstants.setForeground(style, Color.BLACK); // Define a cor do texto como preto

        StyledDocument doc = logArea.getStyledDocument();

        try {
            doc.insertString(doc.getLength(), "[" + timestamp + "] ", logArea.getStyle("Bold")); // Insere o timestamp
            doc.insertString(doc.getLength(), message + "\n", null); // Insere a mensagem
        } catch (BadLocationException e) {
            e.printStackTrace(); // Imprime a pilha de erros se houver uma exceção
        }

        // Rola para a última linha
        logArea.setCaretPosition(doc.getLength()); // Define o cursor na última linha
    }
}