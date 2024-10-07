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

public class GameLog extends JPanel {
    private JTextPane logArea;
    
    public GameLog() {
        setLayout(new BorderLayout());
        
        logArea = new JTextPane();
        logArea.setEditable(false);
        logArea.setContentType("text/html"); // Para permitir estilos

        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addLogMessage(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);

        Style style = logArea.addStyle("Bold", null);
        StyleConstants.setBold(style, true);
        StyleConstants.setForeground(style, Color.BLACK);

        StyledDocument doc = logArea.getStyledDocument();

        try {
            doc.insertString(doc.getLength(), "[" + timestamp + "] ", logArea.getStyle("Bold"));
            doc.insertString(doc.getLength(), message + "\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        // Rola para a última linha
        logArea.setCaretPosition(doc.getLength());
    }
}