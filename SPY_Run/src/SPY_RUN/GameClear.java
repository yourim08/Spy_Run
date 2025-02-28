package SPY_RUN;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.*;

public class GameClear extends JPanel{
	Connection con = null;
	Statement st = null;
	String url = "jdbc:mysql://localhost/spyrundb?serverTimezone=Asia/Seoul";
	String user = "root";
	String password = "qwer1357!";
	public GameClear(SpyRunFrame frame) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
		}
		catch (Exception e) { 
			System.out.println("서버 연동 실패"+e.toString());
		}

		setLayout(null);

		setBackground(new Color(0, 0, 0, 150)); // 반투명 배경
		ImageIcon back = new ImageIcon("img/gc_back.png");
		ImageIcon quit = new ImageIcon("img/gc_quit.png");
		ImageIcon retry = new ImageIcon("img/gc_retry.png");
		JLabel gc_back = new JLabel(back);
		JLabel gc_quit = new JLabel(quit);
		JLabel gc_retry = new JLabel(retry);

		add(gc_quit);
		add(gc_retry);
		add(gc_back);

		gc_back.setBounds(0, 0, 1100, 700);
		gc_quit.setBounds(375, 401, 92, 35);
		gc_retry.setBounds(230, 401, 92, 35);

		gc_quit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				gc_quit.setSize(gc_quit.getWidth()-10, gc_quit.getHeight()-10);
				gc_quit.repaint();
			}

			public void mouseReleased(MouseEvent e) {
				gc_quit.setSize(gc_quit.getWidth()+10, gc_quit.getHeight()+10);
				gc_quit.repaint();
				System.exit(0);
			}
		});

		gc_retry.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				gc_retry.setSize(gc_retry.getWidth()-10, gc_retry.getHeight()-10);
				gc_retry.repaint();
			}

			public void mouseReleased(MouseEvent e) {
				gc_retry.setSize(gc_retry.getWidth()+10, gc_retry.getHeight()+10);
				gc_retry.repaint();
				frame.restart(0); 
				frame.showPage("StartPage");
				try {
					String updateQuery = "UPDATE spy_run SET score = ? where id = 1"; 
					PreparedStatement pstmt = con.prepareStatement(updateQuery);
					pstmt.setInt(1, 0);
					pstmt.executeUpdate();
				}
				catch (Exception e1) {
					System.out.println("점수 가져오기 실패: " + e1.toString());
				}
			}
		});
	}
}

