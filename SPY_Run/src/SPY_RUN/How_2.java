package SPY_RUN;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class How_2 extends JPanel {
    public How_2(SpyRunFrame frame) {
        setLayout(null);

        ImageIcon how2 = new ImageIcon("img/h2_back.png");
        ImageIcon how2_s = new ImageIcon("img/h2_start.png");
        JLabel h2_back = new JLabel(how2);
        JLabel h2_start = new JLabel(how2_s);
        
        add(h2_start);
        add(h2_back);

        h2_start.setBounds(500, 270, 100, 200);
        h2_back.setBounds(0, 0, 1100, 700);
        
        
        // next
        h2_start.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            h2_start.setSize(h2_start.getWidth()-10, h2_start.getHeight()-10);
            h2_start.repaint();
         }

         public void mouseReleased(MouseEvent e) {
            h2_start.setSize(h2_start.getWidth()+10, h2_start.getHeight()+10);
            h2_start.repaint();
            frame.showPage("Game");
         }
      });
    }
}
