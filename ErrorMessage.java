import javax.swing.*;

public class ErrorMessage extends JOptionPane {
    JFrame f;
    ErrorMessage() {
        f = new JFrame();
        JOptionPane.showMessageDialog(f,"Error Illegal Target(s)/Quantity");
    }
}
