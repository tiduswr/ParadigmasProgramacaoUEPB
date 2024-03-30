package com.tiduswr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Login extends JDialog {
    private JTextField txtName;
    private JLabel lblName;
    private JPasswordField txtSenha;
    private JLabel lblSenha;
    private JPanel bottom;
    private JPanel center;
    private JPanel background;
    private JButton okButton;
    private JButton sairButton;

    private final int ITENS_HEIGHT = 30;
    private final Dimension BUTTON_SIZE = new Dimension(100, ITENS_HEIGHT);
    private final Dimension SCREEN_SIZE = new Dimension(350, 400);

    public Login(JFrame parent) throws Exception {
        super(parent);
        init(parent);
    }

    private void init(JFrame parent) throws Exception {
        setTitle("Login");
        setContentPane(background);
        setSize(SCREEN_SIZE);
        txtSenha.setMinimumSize(BUTTON_SIZE);
        txtName.setMinimumSize(BUTTON_SIZE);
        okButton.setMinimumSize(BUTTON_SIZE);
        sairButton.setMinimumSize(BUTTON_SIZE);
        setMinimumSize(SCREEN_SIZE);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        var keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {}

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                var c = keyEvent.getKeyChar();

                if(Character.isLetterOrDigit(c))
                    System.out.println("Você digitou a tecla '" + c + "'!");

                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    fazerLogin();
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {}
        };

        txtName.addKeyListener(keyListener);
        txtSenha.addKeyListener(keyListener);
        okButton.addActionListener(event -> fazerLogin());
        sairButton.addActionListener(event -> dispose());
    }

    private void fazerLogin(){
        String usuario = txtName.getText();
        String senha = new String(txtSenha.getPassword());

        if (usuario.equals("admin") && senha.equals("123")) {
            JOptionPane.showMessageDialog(Login.this, "Login bem-sucedido!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(Login.this, "Credenciais inválidas. Tente novamente.");
        }

    }

}
