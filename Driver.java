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
                int x=e.getX();
                int y=e.getY();
                System.out.println("Cords of mouse helper " +x+","+y);//these co-ords are relative to the component
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
        Territory alaska = new Territory("Alaska", Continent.NA, 1, new int[]{}, 20, 77);
        Territory nwTerritory = new Territory("N.W. Territory", Continent.NA, 2, new int[]{1, 3}, 84, 77);
        Territory alberta = new Territory("Alberta", Continent.NA, 3, new int[]{}, 93, 113);
        Territory wUS = new Territory("W. US", Continent.NA, 4, new int[]{}, 98, 165);
        Territory cAmerica = new Territory("C. America", Continent.NA, 5, new int[]{}, 42, 247);
        Territory eUS = new Territory("E. US", Continent.NA, 6, new int[]{}, 164, 177);
        Territory ontario = new Territory("Ontario", Continent.NA, 7, new int[]{}, 150, 131);
        Territory quebec = new Territory("Quebec", Continent.NA, 8, new int[]{}, 209, 136);
        Territory greenland = new Territory("Greenland", Continent.NA, 9, new int[]{}, 236, 44);
        // in constructor, automatically added to continent list, so loop through it to add to mapPane
        for (int i = 0; i < Territory.naList.size(); i++){
            mapPane.add(Territory.naList.get(i).area, zIndex);
        }

        Territory venezuela = new Territory("Venezuela", Continent.SA, 10, new int[]{}, 158, 276);
        Territory peru = new Territory("Peru", Continent.SA, 11, new int[]{}, 143, 333);
        Territory argentina = new Territory("Argentina", Continent.SA, 12, new int[]{}, 178, 405);
        Territory brazil = new Territory("Brazil", Continent.SA, 13, new int[]{}, 208, 319);
        for (int i = 0; i < Territory.saList.size(); i++){
            mapPane.add(Territory.saList.get(i).area, zIndex);
        }
        
        Territory iceland = new Territory("Iceland", Continent.EUROPE, 14, new int[]{}, 296, 75);
        Territory greatBritain = new Territory("Great Britain", Continent.EUROPE, 15, new int[]{}, 288, 159);
        Territory wEurope = new Territory("W. Europe", Continent.EUROPE, 16, new int[]{}, 302, 228);
        Territory scandanavia = new Territory("Scandanavia", Continent.EUROPE, 17, new int[]{}, 369, 91);
        Territory nEurope = new Territory("N. Europe", Continent.EUROPE, 18, new int[]{}, 348, 165);
        Territory sEurope = new Territory("S. Europe", Continent.EUROPE, 19, new int[]{}, 358, 216);
        Territory ukraine = new Territory("Ukraine", Continent.EUROPE, 20, new int[]{}, 422, 129);
        for (int i = 0; i < Territory.europeList.size(); i++){
            mapPane.add(Territory.europeList.get(i).area, zIndex);
        }
        Territory nAfrica = new Territory("N. Africa", Continent.AFRICA, 21, new int[]{}, 320, 301);
        Territory congo = new Territory("Congo", Continent.AFRICA, 22, new int[]{}, 380, 371);
        Territory sAfrica = new Territory("S. Africa", Continent.AFRICA, 23, new int[]{}, 389, 422);
        Territory egypt = new Territory("Egypt", Continent.AFRICA, 24, new int[]{}, 387, 277);
        Territory eAfrica = new Territory("E. Africa", Continent.AFRICA, 25, new int[]{}, 444, 338);
        Territory madagascar = new Territory("Madagascar", Continent.AFRICA, 26, new int[]{}, 476, 431);
        for (int i = 0; i < Territory.africaList.size(); i++){
            mapPane.add(Territory.africaList.get(i).area, zIndex);
        }
        Territory middleEast = new Territory("Middle East", Continent.ASIA, 14, new int[]{}, 438, 246);
        Territory ural = new Territory("Ural", Continent.ASIA, 15, new int[]{}, 498, 107);
        Territory afghanistan = new Territory("Afghanistan", Continent.ASIA, 16, new int[]{}, 480, 180);
        Territory india = new Territory("India", Continent.ASIA, 17, new int[]{}, 520, 245);
        Territory siberia = new Territory("Siberia", Continent.ASIA, 18, new int[]{}, 525, 69);
        Territory china = new Territory("China", Continent.ASIA, 19, new int[]{}, 565, 219);
        Territory siam = new Territory("Siam", Continent.ASIA, 20, new int[]{}, 596, 280);
        Territory yakutsk = new Territory("Yakutsk", Continent.ASIA, 16, new int[]{}, 594, 62);
        Territory irkutsk = new Territory("Irkutsk", Continent.ASIA, 17, new int[]{}, 584, 134);
        Territory mongolia = new Territory("Mongolia", Continent.ASIA, 18, new int[]{}, 595, 171);
        Territory kamchatka = new Territory("Kamchatka", Continent.ASIA, 19, new int[]{}, 648, 67);
        Territory japan = new Territory("Japan", Continent.ASIA, 20, new int[]{}, 676, 180);
        for (int i = 0; i < Territory.asiaList.size(); i++){
            mapPane.add(Territory.asiaList.get(i).area, zIndex);
        }
        Territory indonesia = new Territory("Indonesia", Continent.AUSTRALIA, 14, new int[]{}, 592, 359);
        Territory wAustralia = new Territory("W. Australia", Continent.AUSTRALIA, 15, new int[]{}, 627, 433);
        Territory nGuinea = new Territory("N. Guinea", Continent.AUSTRALIA, 16, new int[]{}, 673, 333);
        Territory eAustralia = new Territory("E. Australia", Continent.AUSTRALIA, 17, new int[]{}, 686, 405);
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

        while (run){
            if (ActionUI.playerList.size() > 0){
                Territory.currentPlayer = ActionUI.playerList.get(ActionUI.turn);
                Territory.currentAction = ActionUI.action;
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
                actionUI = new ActionUI(Player.playerList, 3); // put in initialTroops, just 5 for testing
                actionUI.setBounds(760, 0, 100, 100);
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
                    actionUI.add(ActionUI.cycleAction);
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
                    }
                } else if (ActionUI.action == Action.ATTACK){

                } else if (ActionUI.action == Action.FORTIFY){
                    // skip for now
                    ActionUI.toggleAction();
                    ActionUI.receivedTroops = false;
                }
            }
            // constantly refresh components
            mapPane.repaint();
        }
    }
}
