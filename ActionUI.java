import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
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
    static TerritoryButton selectedButton = new TerritoryButton("Selected");
    static TerritoryButton targetedButton = new TerritoryButton("Targeted");
    static Territory selectedTerritory = new Territory();
    static Territory targetedTerritory = new Territory();

    static boolean hasFortified = false;
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
        cycleAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (phase == Phase.PLAYING){
                    toggleAction();
                } else if (phase == Phase.PLACING){
                    nextPlayer();
                }
                actionTracker.setText(action.name());
            }
        });
        AttackFortify.attackFortifyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (action == Action.ATTACK){
                    AttackFortify.simulateBattle();
                } else if (action == Action.FORTIFY){
                    AttackFortify.fortify();
                }
            }
        });
    }
    static void toggleAction(){
        if (action == Action.DEPLOY){
            action = Action.ATTACK;
            AttackFortify.attackFortifyBtn.setText("Attack!");
        } else if (action == Action.ATTACK){
            action = action.FORTIFY;
            AttackFortify.attackFortifyBtn.setText("Fortify");
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
    static public class TerritoryButton extends JButton {
        String name;
        static List<TerritoryButton> tButtonList = new ArrayList<>();
        static boolean selectedOn = false;
        static boolean targetedOn = false;
        TerritoryButton(String n){
            super(n + ": ");
            name = n;
            tButtonList.add(this);
            setOpaque(true);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (name.equals("Selected")){
                        selectedOn = true;
                        targetedOn = false;
                    }
                    if (name.equals("Targeted")){
                        targetedOn = true;
                        selectedOn = false;
                    }
                }
            });
        }
    }
    public class AttackFortify {
        static JButton attackFortifyBtn = new JButton("Attack!");
        static SpinnerModel model = new SpinnerNumberModel(1, 1, 99, 1);
        // TODO dynamically set maximum of spinner to selectedTerritory.troops - 1
        static JSpinner spinner = new JSpinner(model);
        AttackFortify(){}

        static void simulateBattle(){
            if (selectedTerritory != null && targetedTerritory != null){
                int attackingWith = (Integer)spinner.getValue();
                if (attackingWith > 1 && selectedTerritory.troops > 1){
                    if (isAdjacent(selectedTerritory, targetedTerritory)){
                        Random random = new Random();
                        int a1 = random.nextInt(1, 7);
                        int a2 = random.nextInt(1, 7);
                        int a3 = random.nextInt(1, 7);
                        int d1 = random.nextInt(1, 7);
                        int d2 = random.nextInt(1, 7);
                        List<Integer> rolls = Arrays.asList(a1, a2, a3, d1, d2);
                        Collections.sort(rolls, Collections.reverseOrder());
                        int losses = 0;
                        if (d1 == rolls.get(0) || d1 == rolls.get(1)){
                            selectedTerritory.troops -= 1;
                            losses += 1;
                        } else {
                            targetedTerritory.troops -= 1;
                        }
                        if (d2 == rolls.get(0) || d2 == rolls.get(1)){
                            selectedTerritory.troops -= 1;
                            losses += 1;
                        } else {
                            targetedTerritory.troops -= 1;
                        }
                        Player target = targetedTerritory.controlledBy;
                        Player selected = selectedTerritory.controlledBy;
                        if (targetedTerritory.troops <= 0){
                            targetedTerritory.controlledBy = selected;
                            target.playerTerritories.remove(targetedTerritory);
                            targetedTerritory.troops = (Integer)spinner.getValue() - losses;
                            selectedTerritory.troops -= (Integer)spinner.getValue() - losses;
                        }
                        selectedTerritory.area.setText(selectedTerritory.name +
                                " "+ String.valueOf(selectedTerritory.troops));
                        targetedTerritory.area.setText(targetedTerritory.name +
                                " "+ String.valueOf(targetedTerritory.troops));
                        targetedTerritory.area.setBackground(Territory.getColor(selected.team));
                    }
                }
            }
        }
        static void fortify(){
            // TODO check if selectedTerritory and targeted are linked
            if (selectedTerritory.controlledBy.team == targetedTerritory.controlledBy.team){
                targetedTerritory.troops += (Integer)spinner.getValue();
                selectedTerritory.troops -= (Integer)spinner.getValue();
                selectedTerritory.area.setText(selectedTerritory.name +
                        " "+ String.valueOf(selectedTerritory.troops));
                targetedTerritory.area.setText(targetedTerritory.name +
                        " "+ String.valueOf(targetedTerritory.troops));
                hasFortified = true;
            }
        }
    }

    static boolean isAdjacent(Territory t1, Territory t2){
        // TODO btw, should probably just change to array list in constructor, didn't want to mess with your code
        List<Integer> temp = Arrays.stream(t1.adjacentTerritories).boxed().toList();
        if (temp.contains(t2.id)){
            return true;
        }
        return false;
    }
    void addButtons(){
        add(cycleAction);
        add(selectedButton);
        add(targetedButton);
        add(AttackFortify.attackFortifyBtn);
        add(AttackFortify.spinner);
    }
}
