package SPY_RUN;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class How_1 extends JPanel {
    public How_1(SpyRunFrame frame) {
        setLayout(null);

        ImageIcon how = new ImageIcon("img/h1_back.png");
        ImageIcon how_n = new ImageIcon("img/h1_next.png");
        JLabel h_back = new JLabel(how);
        JLabel h_next = new JLabel(how_n);
        
        add(h_next);
        add(h_back);

        h_next.setBounds(500, 270, 100, 200);
        h_back.setBounds(0, 0, 1100, 700);
        
        
        // next
        h_next.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            h_next.setSize(h_next.getWidth()-10, h_next.getHeight()-10);
            h_next.repaint();
         }

         public void mouseReleased(MouseEvent e) {
            h_next.setSize(h_next.getWidth()+10, h_next.getHeight()+10);
            h_next.repaint();
            frame.showPage("How_2");
         }
      });
    }
}
