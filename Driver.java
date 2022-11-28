import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

        JLabel example = new JLabel("howdy");
        example.setOpaque(true);
        example.setBackground(Color.RED);
        example.setBounds(100, 100, 200, 200);
//        mapPane.add(example, Integer.valueOf(1));

        Territory iceland = new Territory("Iceland", Teams.RED, Continent.EUROPE, 1, new int[]{1, 2, 3}, new ClickableText(0, "Iceland", 296, 75));
        mapPane.add(iceland.area, Integer.valueOf(1));
        frame.add(mapPane);

        frame.pack();
        frame.setVisible(true);
    }
}
