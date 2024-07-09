
package LoginApplication;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainForm extends JFrame {

    public MainForm() {
        setTitle("Main Form");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to the Main Form!");
        welcomeLabel.setBounds(10, 10, 200, 25);
        panel.add(welcomeLabel);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(100, 50, 80, 25);
        panel.add(logoutButton);

        logoutButton.addActionListener((ActionEvent e) -> {
            new LoginForm().setVisible(true);
            dispose();
        });
    }
}
