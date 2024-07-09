
package LoginApplication;

import javax.swing.SwingUtilities;

public class LoginApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
}