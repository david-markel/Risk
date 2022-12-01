import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Driver {
    static Integer zIndex = 1;
    public static void main(String[] args) {

        // jFrame holds mapPane, otherwise everything is added to mapPane
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Risk");
        frame.setPreferredSize(new Dimension(1024,600));

        JLayeredPane mapPane = new JLayeredPane();
        mapPane.setBounds(0, 0, 1024, 600);
        // mapListener can be removed at final, just a helper to get x y cords when clicking on map
        mapPane.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ;
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int x=e.getX();
                int y=e.getY();
                System.out.println("Cords of mouse helper " +x+","+y);//these co-ords are relative to the component
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                System.out.println("Entered map");
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                System.out.println("Left map");
            }
        });

        // mapPane is layered container, 0 layer has mapPane, rest are on layer 1 over map
        // keep in mind there is essentially no grid layout, so everything is set with setBounds for x, y
        ImagePane map = new ImagePane("Risk_board.png", 0, 0);
        map.setOpaque(true);
        map.setBounds(0, 0, 756, 520);
        mapPane.add(map, Integer.valueOf(0));

        // init all territories, manually set id, adjacencies, x, y
        Territory alaska = new Territory("Alaska", Continent.NA, 1, new int[]{2, 37}, 20, 77);
        Territory nwTerritory = new Territory("N.W. Territory", Continent.NA, 2, new int[]{1, 3}, 84, 77);
        Territory alberta = new Territory("Alberta", Continent.NA, 3, new int[]{1, 2, 4, 7}, 93, 113);
        Territory wUS = new Territory("W. US", Continent.NA, 4, new int[]{3, 5, 6, 7}, 98, 165);
        Territory cAmerica = new Territory("C. America", Continent.NA, 5, new int[]{4, 6, 10}, 42, 247);
        Territory eUS = new Territory("E. US", Continent.NA, 6, new int[]{4, 5, 7, 8}, 164, 177);
        Territory ontario = new Territory("Ontario", Continent.NA, 7, new int[]{2, 3, 4, 6, 8, 9}, 150, 131);
        Territory quebec = new Territory("Quebec", Continent.NA, 8, new int[]{6, 7, 9}, 209, 136);
        Territory greenland = new Territory("Greenland", Continent.NA, 9, new int[]{2, 7, 8, 14}, 236, 44);
        // in constructor, automatically added to continent list, so loop through it to add to mapPane
        for (int i = 0; i < Territory.naList.size(); i++){
            mapPane.add(Territory.naList.get(i).area, zIndex);
        }

        Territory venezuela = new Territory("Venezuela", Continent.SA, 10, new int[]{5, 11, 13}, 158, 276);
        Territory peru = new Territory("Peru", Continent.SA, 11, new int[]{10, 12, 13}, 143, 333);
        Territory argentina = new Territory("Argentina", Continent.SA, 12, new int[]{11, 13}, 178, 405);
        Territory brazil = new Territory("Brazil", Continent.SA, 13, new int[]{10, 11, 12, 21}, 208, 319);
        for (int i = 0; i < Territory.saList.size(); i++){
            mapPane.add(Territory.saList.get(i).area, zIndex);
        }
        
        Territory iceland = new Territory("Iceland", Continent.EUROPE, 14, new int[]{9, 15, 17}, 296, 75);
        Territory greatBritain = new Territory("Great Britain", Continent.EUROPE, 15, new int[]{14, 16, 17, 18}, 288, 159);
        Territory wEurope = new Territory("W. Europe", Continent.EUROPE, 16, new int[]{15, 18, 19, 21}, 302, 228);
        Territory scandinavia = new Territory("Scandinavia", Continent.EUROPE, 17, new int[]{14, 15, 18, 20}, 369, 91);
        Territory nEurope = new Territory("N. Europe", Continent.EUROPE, 18, new int[]{15, 16, 17, 19, 20}, 348, 165);
        Territory sEurope = new Territory("S. Europe", Continent.EUROPE, 19, new int[]{16, 18, 20, 21, 24, 27}, 358, 216);
        Territory ukraine = new Territory("Ukraine", Continent.EUROPE, 20, new int[]{17, 18, 19, 27, 28, 29}, 422, 129);
        for (int i = 0; i < Territory.europeList.size(); i++){
            mapPane.add(Territory.europeList.get(i).area, zIndex);
        }
        Territory nAfrica = new Territory("N. Africa", Continent.AFRICA, 21, new int[]{13, 16, 19, 22, 24, 25, 27}, 320, 301);
        Territory congo = new Territory("Congo", Continent.AFRICA, 22, new int[]{21, 23, 25}, 380, 371);
        Territory sAfrica = new Territory("S. Africa", Continent.AFRICA, 23, new int[]{22, 25, 26}, 389, 422);
        Territory egypt = new Territory("Egypt", Continent.AFRICA, 24, new int[]{19, 21, 25, 27}, 387, 277);
        Territory eAfrica = new Territory("E. Africa", Continent.AFRICA, 25, new int[]{22, 23, 24, 26, 27}, 444, 338);
        Territory madagascar = new Territory("Madagascar", Continent.AFRICA, 26, new int[]{23, 25}, 476, 431);
        for (int i = 0; i < Territory.africaList.size(); i++){
            mapPane.add(Territory.africaList.get(i).area, zIndex);
        }
        Territory middleEast = new Territory("Middle East", Continent.ASIA, 27, new int[]{19, 20, 24, 25, 29, 30}, 438, 246);
        Territory ural = new Territory("Ural", Continent.ASIA, 28, new int[]{20, 29, 31, 32}, 498, 107);
        Territory afghanistan = new Territory("Afghanistan", Continent.ASIA, 29, new int[]{20, 27, 28, 31}, 480, 180);
        Territory india = new Territory("India", Continent.ASIA, 30, new int[]{27, 29, 32, 33}, 520, 245);
        Territory siberia = new Territory("Siberia", Continent.ASIA, 31, new int[]{20, 28, 29, 32, 34, 35, 36}, 525, 69);
        Territory china = new Territory("China", Continent.ASIA, 32, new int[]{28, 29, 30, 31, 33, 36}, 565, 219);
        Territory siam = new Territory("Siam", Continent.ASIA, 33, new int[]{30, 32, 39}, 596, 280);
        Territory yakutsk = new Territory("Yakutsk", Continent.ASIA, 34, new int[]{31, 35, 37}, 594, 62);
        Territory irkutsk = new Territory("Irkutsk", Continent.ASIA, 35, new int[]{31, 34, 36, 37}, 584, 134);
        Territory mongolia = new Territory("Mongolia", Continent.ASIA, 36, new int[]{31, 32, 35, 37, 38}, 595, 171);
        Territory kamchatka = new Territory("Kamchatka", Continent.ASIA, 37, new int[]{1, 34, 35, 36, 38}, 648, 67);
        Territory japan = new Territory("Japan", Continent.ASIA, 38, new int[]{36, 37}, 676, 180);
        for (int i = 0; i < Territory.asiaList.size(); i++){
            mapPane.add(Territory.asiaList.get(i).area, zIndex);
        }
        Territory indonesia = new Territory("Indonesia", Continent.AUSTRALIA, 39, new int[]{33, 40, 41}, 592, 359);
        Territory wAustralia = new Territory("W. Australia", Continent.AUSTRALIA, 40, new int[]{39, 41, 42}, 627, 433);
        Territory nGuinea = new Territory("N. Guinea", Continent.AUSTRALIA, 41, new int[]{39, 40, 42}, 673, 333);
        Territory eAustralia = new Territory("E. Australia", Continent.AUSTRALIA, 42, new int[]{40, 41}, 686, 405);
        for (int i = 0; i < Territory.australiaList.size(); i++){
            mapPane.add(Territory.australiaList.get(i).area, zIndex);
        }

        frame.add(mapPane);
        frame.pack();
        frame.setVisible(true);

        boolean run = true;

        // correspond to start phase, or when deciding on number of players
        PlayersButton playersButton = new PlayersButton();
        mapPane.add(playersButton, zIndex);
        StartButton startButton = new StartButton();
        mapPane.add(startButton, zIndex);

        // used for game logic like deploying, attacking, and fortifying in both placing and playing phase
        ActionUI actionUI = new ActionUI();
        Phase currentPhase = Phase.START;
        int initialTroops = 0;

        // TODO add component that displays text to user telling them what happened, errors, dice rolls, etc...
        while (run){
            if (ActionUI.playerList.size() > 0){
                Territory.currentPlayer = ActionUI.playerList.get(ActionUI.turn);
                Territory.currentAction = ActionUI.action;
                Territory.currentPhase = ActionUI.phase;
            }
            if (StartButton.hasStarted && currentPhase == Phase.START){
                currentPhase = Phase.PLACING;
                ActionUI.phase = currentPhase;
                Player red = new Player(Teams.RED);
                Player blue = new Player(Teams.BLUE);
                if (PlayersButton.players == 3){
                    initialTroops = 35;
                    Player green = new Player(Teams.GREEN);
                } else if (PlayersButton.players == 4){
                    initialTroops = 30;
                    Player green = new Player(Teams.GREEN);
                    Player yellow = new Player(Teams.YELLOW);
                } else if (PlayersButton.players == 5){
                    initialTroops = 25;
                    Player green = new Player(Teams.GREEN);
                    Player yellow = new Player(Teams.YELLOW);
                    Player orange = new Player(Teams.ORANGE);
                }  if (PlayersButton.players == 6){
                    initialTroops = 20;
                    Player green = new Player(Teams.GREEN);
                    Player yellow = new Player(Teams.YELLOW);
                    Player orange = new Player(Teams.ORANGE);
                    Player brown = new Player(Teams.BROWN);
                }
                mapPane.remove(playersButton);
                mapPane.remove(startButton);
                actionUI = new ActionUI(Player.playerList, 3); // TODO put in initialTroops
                actionUI.setBounds(760, 0, 150, 200);
                mapPane.add(actionUI, zIndex);
            }
            if (currentPhase == Phase.PLACING){
                if (Territory.clickedWhilePlacing){
                    // a territory has been clicked signal actionUI nextPlayer
                    Territory.clickedWhilePlacing = false;
                    ActionUI.nextPlayer();
                    ActionUI.placedTroops -= 1;
                }
                if (ActionUI.placedTroops <= 0){
                    // placed all troops
                    currentPhase = Phase.PLAYING;
                    ActionUI.phase = currentPhase;
                    actionUI.addButtons();
                }
            }
            if (currentPhase == Phase.PLAYING){
                if (ActionUI.action == Action.DEPLOY){
                    if (!ActionUI.receivedTroops){
                        ActionUI.getIncomingTroops();
                        ActionUI.receivedTroops = true;
                    }
                    if (Territory.clickedWhilePlacing){
                        Territory.clickedWhilePlacing = false;
                        ActionUI.placedTroops -= 1;
                        ActionUI.troopCounter.setText("Troops: " + (int)ActionUI.placedTroops);
                    }
                    if (ActionUI.placedTroops <= 0){
                        ActionUI.toggleAction();
                        ActionUI.troopCounter.setText("");
                    }
                } else if (ActionUI.action == Action.ATTACK){

                } else if (ActionUI.action == Action.FORTIFY){
                    // skip for now
                    if (ActionUI.hasFortified){
                        ActionUI.toggleAction();
                        ActionUI.receivedTroops = false;
                        ActionUI.hasFortified = true;
                    }
                }
            }
            // constantly refresh components
            mapPane.repaint();
        }
    }
}
