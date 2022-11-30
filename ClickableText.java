import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickableText extends JLabel {
    int count;
    String name;
    int x;
    int y;

    ClickableText(int c, String n, int X, int Y){
        count = c;
        name = n;
        x = X;
        y = Y;
        setText(name + " "+ String.valueOf(count));
        setBounds(x, y, name.length() * 10, 20);
    }
}
