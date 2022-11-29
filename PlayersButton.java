import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayersButton extends JButton {
    static int players = 3;

    PlayersButton(){
        super("Players: " + 3);
        setBounds(760, 0, 100, 60);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (players >= 6){
                    players = 3;
                } else {
                    players += 1;
                }
                setText("Players: " + players);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
