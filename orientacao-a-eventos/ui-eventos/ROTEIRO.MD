## Desenho da interfaces
Mostre como desenhar a interface através do InteliJ IDEA

## Código

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JDialog {
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JButton btLogin;
    private JPanel mainPanel;

    private final Dimension SCREEN_SIZE = new Dimension(350, 270);
    private final String login = "admin";
    private final String senha = "senha123";

    public Login(JFrame parent) {
        super(parent);
        init(parent);
    }

    public void init(JFrame parent) {
        setTitle("Formulário de Login");
        setContentPane(mainPanel);
        setMinimumSize(SCREEN_SIZE);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btLogin.setEnabled(false);
        setVisible(true);

        var validator = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateLoginFields();
            }
        };

        txtUsername.addKeyListener(validator);
        txtPassword.addKeyListener(validator);
        btLogin.addActionListener(e -> login());
    }

    private void validateLoginFields() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        boolean isValid = username.matches("^(?:[a-zA-Z0-9]+){3,}$")
                && password.matches("^(?=(?:.*[a-zA-Z]){3})(?=(?:.*\\d){3})[a-zA-Z\\d]{6,}$");

        btLogin.setEnabled(isValid);
    }

    public void login() {
        btLogin.setEnabled(false);
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        var worker = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                Thread.sleep(1500);
                return username.equals(login) && password.equals(senha);
            }

            @Override
            protected void done() {
                try {
                    if (get()) {
                        JOptionPane.showMessageDialog(Login.this,
                                String.format("Login bem-sucedido: %s\n", username), "Sistema",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(Login.this,
                                "Credenciais inválidas. Tente novamente.",
                                "Sistema", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    btLogin.setEnabled(true);
                }
            }
        };

        worker.execute();
    }
}
```