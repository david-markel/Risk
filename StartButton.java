import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButton extends JButton {

    static boolean hasStarted = false;
    StartButton(){
        super("Start");
        setBounds(860, 0, 100, 60);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hasStarted = true;
            }
        });
    }
}
