package SPY_RUN;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class GameOver extends JPanel{
	Connection con = null;
	Statement st = null;
	String url = "jdbc:mysql://localhost/spyrundb?serverTimezone=Asia/Seoul";
	String user = "root";
	String password = "qwer1357!";
	int go_score = 0;
	int go_best_score = 0;
	JLabel end_score;
	JLabel best_score;
	Font scorefont = new Font("NanumGothic", Font.PLAIN, 30);
	Font bestfont = new Font("NanumGothic", Font.PLAIN, 16);
	public GameOver(SpyRunFrame frame) {

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
		ImageIcon gameover = new ImageIcon("img/go_back.png");
		ImageIcon home = new ImageIcon("img/go_home.png");
		ImageIcon retry = new ImageIcon("img/go_retry.png");
		JLabel g_gameover = new JLabel(gameover);
		JLabel g_home = new JLabel(home);
		JLabel g_retry = new JLabel(retry);
		best_score = new JLabel(String.valueOf(go_best_score));
		best_score.setFont(bestfont);
		best_score.setForeground(new Color(236, 209, 112));

		end_score = new JLabel(String.valueOf(go_score));
		end_score.setFont(scorefont);
		end_score.setForeground(Color.WHITE);

		add(best_score);
		add(end_score);
		add(g_retry);
		add(g_home);
		add(g_gameover);

		best_score.setBounds(552,325,100,60);
		end_score.setBounds(500,265,200,60);
		g_gameover.setBounds(270, 141, 560, 320);
		g_home.setBounds(487, 394, 35, 34);
		g_retry.setBounds(578, 394, 35, 34);


		// 홈
		g_home.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				g_home.setSize(g_home.getWidth()-10, g_home.getHeight()-10);
				g_home.repaint();
			}
			public void mouseReleased(MouseEvent e) {
				g_home.setSize(g_home.getWidth()+10, g_home.getHeight()+10);
				g_home.repaint();
				frame.timerstop();
				frame.showPage("StartPage");
			}
		});

		// 다시시작
		g_retry.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				g_retry.setSize(g_retry.getWidth()-10, g_retry.getHeight()-10);
				g_retry.repaint();
			}

			public void mouseReleased(MouseEvent e) {
				g_retry.setSize(g_retry.getWidth()+10, g_retry.getHeight()+10);
				g_retry.repaint();
				frame.restart(0);
			}
		});
	}

	public void getScore(int score) {
		go_score = score;
		try {
			String best = "SELECT score FROM spy_run where id = 1";
			ResultSet rs = st.executeQuery(best);

			while (rs.next()) {
				if (rs.getInt("score") < go_score) {
					String updateQuery = "UPDATE spy_run SET score = ? where id = 1"; 
					PreparedStatement pstmt = con.prepareStatement(updateQuery);
					pstmt.setInt(1, go_score);
					pstmt.executeUpdate();
					go_best_score = go_score;
				}
			}
		} catch (Exception e) {
			System.out.println("점수 가져오기 실패: " + e.toString());
		}
		end_score.setText(String.valueOf(go_score));
		best_score.setText(String.valueOf(go_best_score));
		
	}
}
