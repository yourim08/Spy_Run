package SPY_RUN;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game_1 extends JPanel{
	String g1_answer;

	int count = 0;
	Font font = new Font("NanumGothic", Font.PLAIN, 20);

	public Game_1(SpyRunFrame frame) {
		
		setLayout(null);

		setBackground(new Color(0, 0, 0, 150)); // 반투명 배경
		ImageIcon question = new ImageIcon("img/g1_question.png");
		ImageIcon complete = new ImageIcon("img/g1_complete.png");
		ImageIcon submit = new ImageIcon("img/submit.png");
		JTextField g1_input = new JTextField(20);
		JLabel g1_question = new JLabel(question);
		JLabel g1_submit = new JLabel(submit);
		JLabel g1_complete = new JLabel(complete);

		g1_input.setBackground(new Color(44,55,82));
		g1_input.setForeground(Color.WHITE);
		g1_input.setFont(font);

		add(g1_complete);
		add(g1_submit);
		add(g1_input);
		add(g1_question);

		g1_complete.setVisible(false);
		g1_input.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		g1_complete.setBounds(346, 192, 413, 237);
		g1_input.setBounds(476, 401, 100, 25);
		g1_question.setBounds(270, 146, 560, 321);
		g1_submit.setBounds(589, 396, 60, 35);

		// 제출 버튼
		g1_submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				g1_submit.setSize(g1_submit.getWidth()-10, g1_submit.getHeight()-10);
				g1_submit.repaint();
			}

			public void mouseReleased(MouseEvent e) {
				g1_submit.setSize(g1_submit.getWidth()+10, g1_submit.getHeight()+10);
				g1_submit.repaint();
				g1_answer = g1_input.getText();
				if(g1_answer.equals("35")) {
					
					remove(g1_input);
					remove(g1_submit);
					revalidate();
					repaint();
					g1_question.setVisible(false);
					g1_complete.setVisible(true);
					
					revalidate(); 
					repaint();
					
					Timer g1_timer = new Timer();
					TimerTask g1_timertask = new TimerTask() {
						@Override
						public void run() {
							count++;
							if(count == 1) {
								g1_timer.cancel();
								frame.restart(1);
								add(g1_input);
								add(g1_submit);
								revalidate();
								repaint();
								g1_submit.setVisible(true);
								g1_question.setVisible(true);
								g1_complete.setVisible(false);
								
								revalidate(); 
								repaint();
							}
						}
					};
					g1_timer.schedule(g1_timertask, 3000,1000);
					count = 0;
				}
				else {
					frame.showPage("GameOver");
				}
			}
		});
	}
}

