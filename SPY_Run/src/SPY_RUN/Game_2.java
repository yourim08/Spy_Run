package SPY_RUN;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game_2 extends JPanel{
	String g2_answer;

	int count = 0;
	Font font = new Font("NanumGothic", Font.PLAIN, 20);

	public Game_2(SpyRunFrame frame) {
		setLayout(null);

		setBackground(new Color(0, 0, 0, 150)); // 반투명 배경
		ImageIcon question = new ImageIcon("img/g2_question.png");
		ImageIcon complete = new ImageIcon("img/g2_complete.png");
		ImageIcon submit = new ImageIcon("img/submit.png");
		JTextField g2_input = new JTextField(20);
		JLabel g2_question = new JLabel(question);
		JLabel g2_submit = new JLabel(submit);
		JLabel g2_complete = new JLabel(complete);

		g2_input.setBackground(new Color(44,55,82));
		g2_input.setForeground(Color.WHITE);
		g2_input.setFont(font);

		add(g2_complete);
		add(g2_submit);
		add(g2_input);
		add(g2_question);

		g2_complete.setVisible(false);
		g2_input.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		g2_complete.setBounds(346, 192, 413, 237);
		g2_input.setBounds(476, 401, 100, 25);
		g2_question.setBounds(270, 146, 560, 321);
		g2_submit.setBounds(589, 396, 60, 35);

		// 제출 버튼
		g2_submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				g2_submit.setSize(g2_submit.getWidth()-10, g2_submit.getHeight()-10);
				g2_submit.repaint();
			}

			public void mouseReleased(MouseEvent e) {
				g2_submit.setSize(g2_submit.getWidth()+10, g2_submit.getHeight()+10);
				g2_submit.repaint();
				g2_answer = g2_input.getText();
				if(g2_answer.equals("190000")) {
					g2_input.setVisible(false);
					g2_submit.setVisible(false);
					g2_question.setVisible(false);
					g2_complete.setVisible(true);
					Timer g2_timer = new Timer();
					TimerTask g2_timertask = new TimerTask() {
						@Override
						public void run() {
							count++;
							if(count == 1) {
								g2_timer.cancel();
								frame.restart(2);
								g2_input.setText(""); 
								g2_input.setVisible(true);
								g2_submit.setVisible(true);
								g2_question.setVisible(true);
								g2_complete.setVisible(false);
							}
						}
					};
					g2_timer.schedule(g2_timertask, 3000,1000);
					count = 0;
				}
				else {
					frame.showPage("GameOver");
				}
			}
		});
	}
}

