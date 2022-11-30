import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Driver {
    static Integer zIndex = 1;
    public static void main(String[] args) {
        ContinentArmies africa = new ContinentArmies(Continent.AFRICA, 3);
        ContinentArmies asia = new ContinentArmies(Continent.ASIA, 7);
        ContinentArmies na = new ContinentArmies(Continent.NA, 5);
        ContinentArmies sa = new ContinentArmies(Continent.SA, 2);
        ContinentArmies europe = new ContinentArmies(Continent.EUROPE, 5);
        ContinentArmies australia = new ContinentArmies(Continent.AUSTRALIA, 2);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Risk");
        frame.setPreferredSize(new Dimension(1024,600));

        JLayeredPane mapPane = new JLayeredPane();
        mapPane.setBounds(0, 0, 1024, 600);
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

        ImagePane map = new ImagePane("Risk_board.png", 0, 0);
        map.setOpaque(true);
        map.setBounds(0, 0, 756, 520);
        mapPane.add(map, Integer.valueOf(0));

        Territory alaska = new Territory("Alaska", Continent.NA, 1, new int[]{2, 3, 100}, 20, 77);
        Territory nwTerritory = new Territory("N.W. Territory", Continent.NA, 2, new int[]{1, 3}, 84, 77);
        Territory alberta = new Territory("Alberta", Continent.NA, 3, new int[]{1, 2}, 93, 113);
        Territory wUS = new Territory("W. US", Continent.NA, 4, new int[]{}, 98, 165);
        Territory cAmerica = new Territory("C. America", Continent.NA, 5, new int[]{}, 42, 247);
        Territory eUS = new Territory("E. US", Continent.NA, 6, new int[]{}, 164, 177);
        Territory ontario = new Territory("Ontario", Continent.NA, 7, new int[]{}, 150, 131);
        Territory quebec = new Territory("Quebec", Continent.NA, 8, new int[]{}, 209, 136);
        Territory greenland = new Territory("Greenland", Continent.NA, 9, new int[]{}, 236, 44);
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
        boolean setTeams = false;
        int turn = 0;

        PlayersButton playersButton = new PlayersButton();
        mapPane.add(playersButton, zIndex);

        StartButton startButton = new StartButton();
        mapPane.add(startButton, zIndex);

//        JLabel turnTracker = new JLabel();
//        turnTracker.setBounds(760, 0, 100, 60);
//
//        JLabel actionTracker = new JLabel();
//        actionTracker.setBounds(860, 0, 100, 60);

//        JButton cycleAction = new JButton("Cycle Action");

        ActionUI actionUI;
        while (run){
            if (StartButton.hasStarted && !setTeams){
                setTeams = true;
                if (PlayersButton.players == 3){
                    Player red = new Player(Teams.RED, 35);
                    Player blue = new Player(Teams.BLUE, 35);
                    Player green = new Player(Teams.GREEN, 35);
                } else if (PlayersButton.players == 4){
                    Player red = new Player(Teams.RED, 30);
                    Player blue = new Player(Teams.BLUE, 30);
                    Player green = new Player(Teams.GREEN, 30);
                    Player yellow = new Player(Teams.YELLOW, 30);
                } else if (PlayersButton.players == 5){
                    Player red = new Player(Teams.RED, 25);
                    Player blue = new Player(Teams.BLUE,25 );
                    Player green = new Player(Teams.GREEN, 25);
                    Player yellow = new Player(Teams.YELLOW, 25);
                    Player orange = new Player(Teams.ORANGE, 25);
                }  if (PlayersButton.players == 6){
                    Player red = new Player(Teams.RED, 20);
                    Player blue = new Player(Teams.BLUE,20 );
                    Player green = new Player(Teams.GREEN, 20);
                    Player yellow = new Player(Teams.YELLOW, 20);
                    Player orange = new Player(Teams.ORANGE, 20);
                    Player brown = new Player(Teams.BROWN, 20);
                }
                mapPane.remove(playersButton);
                mapPane.remove(startButton);
                actionUI = new ActionUI(Player.playerList);
                actionUI.setBounds(760, 0, 100, 100);
                mapPane.add(actionUI, zIndex);
//                turnTracker.setText(Player.playerList.get(0).team.name());
//                mapPane.add(turnTracker, Integer.valueOf(0));
//                actionTracker.setText(Action.DEPLOY.name());
//                mapPane.add(actionTracker, Integer.valueOf(0));

            }
            mapPane.repaint();
        }
    }
}
