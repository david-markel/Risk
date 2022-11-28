import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Driver {
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

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        ImagePane map = new ImagePane("Risk_board.png", 0, 0);
        map.setOpaque(true);
        map.setBounds(0, 0, 756, 520);
        mapPane.add(map, Integer.valueOf(0));

//        JLabel example = new JLabel("howdy");
//        example.setOpaque(true);
//        example.setBackground(Color.RED);
//        example.setBounds(100, 100, 200, 200);
//        mapPane.add(example, Integer.valueOf(1));

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
            mapPane.add(Territory.naList.get(i).area, Integer.valueOf(1));
        }
        Territory venezuela = new Territory("Venezuela", Continent.SA, 10, new int[]{}, 158, 276);
        Territory peru = new Territory("Peru", Continent.SA, 11, new int[]{}, 143, 333);
        Territory argentina = new Territory("Argentina", Continent.SA, 12, new int[]{}, 178, 405);
        Territory brazil = new Territory("Brazil", Continent.SA, 13, new int[]{}, 208, 319);
        for (int i = 0; i < Territory.saList.size(); i++){
            mapPane.add(Territory.saList.get(i).area, Integer.valueOf(1));
        }

        Territory iceland = new Territory("Iceland", Continent.EUROPE, 69, new int[]{1, 2, 3}, 296, 75);
        mapPane.add(iceland.area, Integer.valueOf(1));

        frame.add(mapPane);

        frame.pack();
        frame.setVisible(true);
    }
}
