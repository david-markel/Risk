import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class ActionUI extends JPanel {
    int turn = 0;
    List<Player> playerList;
    JLabel turnTracker = new JLabel();
    JLabel actionTracker = new JLabel();
    JButton cycleAction = new JButton("Cycle Action");
    static Action action = Action.DEPLOY;

    ActionUI(List<Player> list){
//        setLayout(null);
//        setBounds(0, 0, )
        playerList = list;
        turnTracker.setText(Player.playerList.get(0).team.name());
        add(turnTracker);
        actionTracker.setText(action.name());
        add(actionTracker);
        add(cycleAction);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cycleAction.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (action == Action.DEPLOY){
                    action = Action.ATTACK;
                } else if (action == Action.ATTACK){
                    action = action.FORTIFY;
                } else {
                    action = Action.DEPLOY;
                    nextPlayer();
                }
                actionTracker.setText(action.name());
                turnTracker.setText(playerList.get(turn).team.name());
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
    void nextPlayer(){
        if (turn + 1 < playerList.size()){
            turn += 1;
        } else {
            turn = 0;
        }
    }
}
