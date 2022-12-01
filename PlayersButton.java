import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayersButton extends JButton {
    static int players = 3;

    PlayersButton(){
        super("Players: " + 3);
        setBounds(760, 0, 100, 60);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (players >= 6){
                    players = 3;
                } else {
                    players += 1;
                }
                setText("Players: " + players);
            }
        });
    }
}
