import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Territory {
    static Action currentAction = Action.DEPLOY;
    static Player currentPlayer = null;
    static Phase currentPhase = Phase.START;
    static boolean clickedWhilePlacing = false;
    String name;
    Player controlledBy = null;
    Continent continent;
    int id;
    int troops = 0;
    int[] adjacentTerritories;
    static List<Territory> naList = new ArrayList();
    static List<Territory> saList = new ArrayList();
    static List<Territory> europeList = new ArrayList();
    static List<Territory> africaList = new ArrayList();
    static List<Territory> asiaList = new ArrayList();
    static List<Territory> australiaList = new ArrayList();
    static List<Territory> masterList = new ArrayList<>();
    ClickableText area;
    Territory(){};

    Territory(String n, Continent c, int i, int[] at, int x, int y){
        name = n;
        continent = c;
        id = i;
        name = n;
        adjacentTerritories = new int[at.length];
        for (int j = 0; j < at.length; j++){
            adjacentTerritories[j] = at[j];
        }
        area = new ClickableText(0, name, x, y);
        if (continent == Continent.NA){
            naList.add(this);
        }
        if (continent == Continent.SA){
            saList.add(this);
        }
        if (continent == Continent.EUROPE){
            europeList.add(this);
        }
        if (continent == Continent.ASIA){
            asiaList.add(this);
        }
        if (continent == Continent.AFRICA){
            africaList.add(this);
        }
        if (continent == Continent.AUSTRALIA){
            australiaList.add(this);
        }
        masterList.add(this);
        area.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentPlayer != null && currentAction == Action.DEPLOY){
                    if (controlledBy == null){
                        setOwnership(currentPlayer);
                    }
                    // if placing and territories left
                    if (troops > 0 && currentPhase == Phase.PLACING){
                        for (int i = 0; i < masterList.size(); i++){
                            if (masterList.get(i).controlledBy == null){
                                return;
                            }
                        }
                    }
                    if(controlledBy == currentPlayer){
                        troops += 1;
                        area.count = troops;
                        area.setText(name + " "+ String.valueOf(area.count));
                        clickedWhilePlacing = true;
                    }
                }
                if (currentAction == Action.ATTACK){
                    if (ActionUI.TerritoryButton.selectedOn){
                        ActionUI.selectedTerritory = returnSelf();
                        ActionUI.selectedButton.setText("Selected: " + ActionUI.selectedTerritory.name);
                    }
                    if (ActionUI.TerritoryButton.targetedOn){
                        ActionUI.targetedTerritory = returnSelf();
                        ActionUI.targetedButton.setText("Targeted: " + ActionUI.targetedTerritory.name);
                    }
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
                area.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                area.setBorder(null);
            }
        });
    }
    Territory returnSelf(){
        // seems dumb, but needed for inner mouseListener
        return this;
    }
    void setOwnership(Player player){
        controlledBy = player;
        if (player.team == Teams.RED){
            area.setBackground(Color.RED);
        } else if (player.team == Teams.BLUE) {
            area.setBackground(Color.BLUE);
        } else if (player.team == Teams.GREEN) {
            area.setBackground(Color.GREEN);
        } else if (player.team == Teams.YELLOW) {
            area.setBackground(Color.YELLOW);
        } else if (player.team == Teams.ORANGE) {
            area.setBackground(Color.ORANGE);
        } else if (player.team == Teams.BROWN) {
            area.setBackground(new Color(153, 102, 0));
        }
        area.setOpaque(true);
        if (!player.playerTerritories.contains(this)){
            player.playerTerritories.add(this);
        }
    }
    //calculate legal fortification targets
    int[] legalFortify() {
        int[] targets = new int[41];

        return targets;
    }
}
