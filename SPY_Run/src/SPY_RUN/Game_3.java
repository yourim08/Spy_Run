package SPY_RUN;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game_3 extends JPanel{
	String g3_answer;

	int count = 0;
	Font font = new Font("NanumGothic", Font.PLAIN, 20);

	public Game_3(SpyRunFrame frame) {
		setLayout(null);

		setBackground(new Color(0, 0, 0, 150)); // 반투명 배경
		ImageIcon question = new ImageIcon("img/g3_question.png");
		ImageIcon complete = new ImageIcon("img/g3_complete.png");
		ImageIcon submit = new ImageIcon("img/submit.png");
		JTextField g3_input = new JTextField(20);
		JLabel g3_question = new JLabel(question);
		JLabel g3_submit = new JLabel(submit);
		JLabel g3_complete = new JLabel(complete);

		g3_input.setBackground(new Color(44,55,82));
		g3_input.setForeground(Color.WHITE);
		g3_input.setFont(font);

		add(g3_complete);
		add(g3_submit);
		add(g3_input);
		add(g3_question);

		g3_complete.setVisible(false);
		g3_input.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		g3_complete.setBounds(346, 192, 413, 237);
		g3_input.setBounds(476, 401, 100, 25);
		g3_question.setBounds(270, 146, 560, 321);
		g3_submit.setBounds(589, 396, 60, 35);

		// 제출 버튼
		g3_submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				g3_submit.setSize(g3_submit.getWidth()-10, g3_submit.getHeight()-10);
				g3_submit.repaint();
			}

			public void mouseReleased(MouseEvent e) {
				g3_submit.setSize(g3_submit.getWidth()+10, g3_submit.getHeight()+10);
				g3_submit.repaint();
				g3_answer = g3_input.getText();
				if(g3_answer.equals("김현수")) {
					g3_input.setVisible(false);
					g3_submit.setVisible(false);
					g3_question.setVisible(false);
					g3_complete.setVisible(true);
					Timer g3_timer = new Timer();
					TimerTask g3_timertask = new TimerTask() {
						@Override
						public void run() {
							count++;
							if(count == 1) {
								g3_timer.cancel();
								frame.restart(3);
								g3_input.setText(""); 
								g3_input.setVisible(true);
								g3_submit.setVisible(true);
								g3_question.setVisible(true);
								g3_complete.setVisible(false);
							}
						}
					};
					g3_timer.schedule(g3_timertask, 3000,1000);
					count = 0;
				}
				else {
					frame.showPage("GameOver");
				}
			}
		});
	}
}

