
package LoginApplication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public UserForm(String action) {
        setTitle(action);
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel, action);

        JButton actionButton = new JButton(action);
        actionButton.setBounds(100, 80, 80, 25);
        panel.add(actionButton);

        actionButton.addActionListener((ActionEvent e) -> {
            if (action.equals("Create User")) {
                createUser();
            } else if (action.equals("Remove User")) {
                removeUser();
            }
        });
    }

    private void placeComponents(JPanel panel, String action) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 10, 160, 25);
        panel.add(usernameField);

        if (action.equals("Create User")) {
            JLabel passwordLabel = new JLabel("Password:");
            passwordLabel.setBounds(10, 40, 80, 25);
            panel.add(passwordLabel);

            passwordField = new JPasswordField(20);
            passwordField.setBounds(100, 40, 160, 25);
            panel.add(passwordField);
        }
    }

    private void createUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "User created successfully!");
                this.dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }

    private void removeUser() {
        String username = usernameField.getText();

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "DELETE FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "User removed successfully!");
                this.dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }
}

