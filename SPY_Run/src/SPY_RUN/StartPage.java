package SPY_RUN;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartPage extends JPanel {
   public StartPage(SpyRunFrame frame) {
      setLayout(null);

      ImageIcon startBG = new ImageIcon("img/s_back.png");
      ImageIcon start = new ImageIcon("img/s_start.png");
      ImageIcon quit = new ImageIcon("img/s_quit.png");

      JLabel backL = new JLabel(startBG);
      JLabel sL = new JLabel(start);
      JLabel qL = new JLabel(quit);

      // 컴포넌트 추가
      add(sL);
      add(qL);
      add(backL);

      sL.setBounds(90, 230, 500, 100);
      qL.setBounds(95, 320, 500, 100);
      backL.setBounds(0, 0, 1100, 700);

      // Quit 
      qL.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            qL.setSize(qL.getWidth()-10, qL.getHeight()-10);
            // 젹용 시키기 위해 reapaint
            qL.repaint();
         }

         public void mouseReleased(MouseEvent e) {
            qL.setSize(qL.getWidth()+10, qL.getHeight()+10);
            qL.repaint();
            System.exit(0);
         }
      });

      // Start 
      moveButton(sL, "How_1", frame);
   }

   // 버튼 메소드 (SpyRunFrame 하나를 다같이 공유. 이래야 페이지 이동이 가능)
   public static void moveButton (JLabel j, String PN, SpyRunFrame frame) {
      j.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            j.setSize(j.getWidth() - 10, j.getHeight() - 10); 
            j.repaint(); 
         }

         @Override
         public void mouseReleased(MouseEvent e) {
            j.setSize(j.getWidth() + 10, j.getHeight() + 10); 
            j.repaint();

            frame.showPage(PN);
         }
      });
   }
}
