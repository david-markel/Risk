import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickableText extends JLabel {
    int count;
    boolean entered = false;
    String name;
    int x;
    int y;

    ClickableText(int c, String n, int X, int Y){
        count = c;
        name = n;
        x = X;
        y = Y;
        setText(name + String.valueOf(count));
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        setBounds(x, y, 70, 20);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (entered){
                    count += 1;
                    setText(name + String.valueOf(count));
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
                entered = true;
                setBorder(border);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                entered = false;
                setBorder(null);
            }
        });
    }
}
