import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class ActionUI extends JPanel {
    static int turn = 0; // basically an index for list
    static List<Player> playerList = new ArrayList<>(); //gets player list from player class
    static JLabel turnTracker = new JLabel();
    static JLabel actionTracker = new JLabel();
    static JLabel troopCounter = new JLabel();
    static JButton cycleAction = new JButton("Cycle Action");
    static Action action = Action.DEPLOY; // used in multiple classes, all same object
    static Phase phase = Phase.START;
    static float placedTroops;
    static boolean receivedTroops = false;
    TerritoryButton selectedTerritory = new TerritoryButton("Selected");
    TerritoryButton targetedTerritory = new TerritoryButton("Targeted");
    ActionUI(){};

    ActionUI(List<Player> list, int troops){
        setBackground(new Color(210, 180, 140));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        playerList = list;
        placedTroops = troops * playerList.size();
        turnTracker.setText(Player.playerList.get(0).team.name());
        actionTracker.setText(action.name());
        troopCounter.setText("Troops: " + Math.round(placedTroops / playerList.size()));
        add(turnTracker);
        add(actionTracker);
        add(troopCounter);

        cycleAction.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (phase == Phase.PLAYING){
                    toggleAction();
                } else if (phase == Phase.PLACING){
                    nextPlayer();
                }
                actionTracker.setText(action.name());
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
    static void toggleAction(){
        if (action == Action.DEPLOY){
            action = Action.ATTACK;
        } else if (action == Action.ATTACK){
            action = action.FORTIFY;
        } else {
            action = Action.DEPLOY;
            nextPlayer();
        }
        actionTracker.setText(action.name());
    }
    static void nextPlayer(){
        if (turn + 1 < playerList.size()){
            turn += 1;
        } else {
            turn = 0;
        }
        troopCounter.setText("Troops: " + Math.round(placedTroops / playerList.size()));
        turnTracker.setText(playerList.get(turn).team.name());
    }

    static void getIncomingTroops(){
        if (phase == Phase.PLAYING){
            Player currentPlayer = playerList.get(turn);
            int calc = (Math.floorDiv (currentPlayer.playerTerritories.size(), 3))
                    + currentPlayer.calcContinentRewards();
            placedTroops = calc > 3 ? calc : 3;
            troopCounter.setText("Troops: " + (int)placedTroops);
        } else {
            return;
        }
    }
    public class TerritoryButton extends JButton {
        String name;
        Territory territory;
        static boolean selectedOn = false;
        static boolean targetedOn = false;
        TerritoryButton(String n){
            super(n + ": ");
            name = n;
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (name.equals("Selected")){
                        selectedOn = true;
                        targetedOn = false;
                    }
                    if (name.equals("Targeted")){
                        targetedOn = true;
                        selectedOn = false;
                    }
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
    void addButtons(){
        add(cycleAction);
        add(selectedTerritory);
        add(targetedTerritory);
    }
}
