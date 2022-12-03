import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.*;
import java.util.List;

public class ActionUI extends JPanel {
    static int turn = 0; // basically an index for list
    static List<Player> playerList = new ArrayList<>(); //gets player list from player class
    static JLabel turnTracker = new JLabel();
    static JLabel actionTracker = new JLabel();
    static JLabel troopCounter = new JLabel();
    static JButton cycleAction = new JButton("Next Phase");
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
                troopCounter.setText("Troops: " + (int)placedTroops);
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
            AttackFortify.attackFortifyBtn.setText("Deploy");
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
            if(calc > 3) {
                placedTroops = calc;
            } else {
                placedTroops = 3;
            }
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

        static void simulateBattle() {
            if (selectedTerritory != null && targetedTerritory != null) {
                int attackingWith = (Integer) spinner.getValue();
                if (attackingWith < selectedTerritory.troops) {
                    if (selectedTerritory.isAdjacent(targetedTerritory)) {
                        Random random = new Random();
                        int a1 = random.nextInt(1, 7);
                        int a2 = random.nextInt(1, 7);
                        int a3 = random.nextInt(1, 7);
                        int d1 = random.nextInt(1, 7);
                        int d2 = random.nextInt(1, 7);
                        List<Integer> attackingRolls = Arrays.asList(a1, a2, a3);
                        List<Integer> defendingRolls = Arrays.asList(d1, d2);
                        if (attackingWith < 3) {
                            attackingRolls = Arrays.asList(a1, a2);
                            Collections.sort(attackingRolls, Collections.reverseOrder());
                            Collections.sort(defendingRolls, Collections.reverseOrder());
                            if (targetedTerritory.troops == 1) { //one defensive dice
                                if (attackingWith == 1) {   //one defensive one offensive
                                    if (a1 > d1) {
                                        targetedTerritory.troops--;
                                    } else {
                                        selectedTerritory.troops--;
                                        attackingWith--;
                                    }
                                } else { //two offensive 1 defensive
                                    if (attackingRolls.get(0) > d1) {
                                        targetedTerritory.troops--;
                                    } else {
                                        selectedTerritory.troops--;
                                        attackingWith--;
                                    }
                                }
                            }
                        } else {    //3 offensive dice
                            if (attackingWith == 1) {   //2 or more attackers 1 defender
                                if (a1 > defendingRolls.get(0)) {
                                    targetedTerritory.troops--;
                                } else {
                                    selectedTerritory.troops--;
                                    attackingWith--;
                                }
                            } else { //2 or more attackers and 2 defenders
                                if (attackingRolls.get(0) > defendingRolls.get(0)) {
                                    targetedTerritory.troops--;
                                } else {
                                    selectedTerritory.troops--;
                                    attackingWith--;
                                }
                                if (attackingRolls.get(1) > defendingRolls.get(1)) {
                                    targetedTerritory.troops--;
                                } else {
                                    selectedTerritory.troops--;
                                    attackingWith--;
                                }
                            }
                        }
                    }
                            Player target = targetedTerritory.controlledBy;
                            Player selected = selectedTerritory.controlledBy;
                            if (targetedTerritory.troops <= 0) {
                                targetedTerritory.controlledBy = selected;
                                target.playerTerritories.remove(targetedTerritory);
                                targetedTerritory.troops = attackingWith;
                                selectedTerritory.troops -= attackingWith;
                                selectedTerritory.area.setText(selectedTerritory.name +
                                        " " + String.valueOf(selectedTerritory.troops));
                                targetedTerritory.area.setText(targetedTerritory.name +
                                        " " + String.valueOf(targetedTerritory.troops));
                                targetedTerritory.area.setBackground(Territory.getColor(selected.team));
                            }
                }
            } else {
                new messageBox();
            }
        }
        static void fortify(){
            List<Territory> targets = new ArrayList();
            if (selectedTerritory.legalFortify(targets).contains(targetedTerritory) && selectedTerritory.troops > (Integer)spinner.getValue()){
                targetedTerritory.troops += (Integer)spinner.getValue();
                selectedTerritory.troops -= (Integer)spinner.getValue();
                selectedTerritory.area.setText(selectedTerritory.name +
                        " "+ String.valueOf(selectedTerritory.troops));
                targetedTerritory.area.setText(targetedTerritory.name +
                        " "+ String.valueOf(targetedTerritory.troops));
            } else {
                new messageBox();
            }
        }
    }

    void addButtons(){
        add(cycleAction);
        add(selectedButton);
        add(targetedButton);
        add(AttackFortify.attackFortifyBtn);
        add(AttackFortify.spinner);
    }
}
