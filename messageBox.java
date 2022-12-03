import javax.swing.*;

public class messageBox extends JOptionPane {
    JFrame f;
    String input;
    messageBox() {
        f = new JFrame();
        JOptionPane.showMessageDialog(f,"Error Illegal Target(s)/Quantity");
    }
    messageBox(String message) {
        f = new JFrame();
        JOptionPane.showMessageDialog(f, message);
    }
}
