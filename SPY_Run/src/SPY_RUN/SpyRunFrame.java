package SPY_RUN;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SpyRunFrame extends JFrame {
	CardLayout card;
	JPanel mainPanel;
	Game g_C;
	GameOver go_C;
	public SpyRunFrame() {
		setFocusable(true);
		setTitle("Spy_Run");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 700);
		setLocationRelativeTo(null);
		setUndecorated(true);

		card = new CardLayout();
		mainPanel = new JPanel(card);

		// 패널 추가,,, 클래스 연결?? Wow...
		StartPage s_C = new StartPage(this);
		How_1 h1_C = new How_1(this);
		How_2 h2_C = new How_2(this);
		g_C = new Game(this);
		go_C = new GameOver(this);
		Game_1 g1_C = new Game_1(this);
		Game_2 g2_C = new Game_2(this);
		Game_3 g3_C = new Game_3(this);
		GameClear clear_C = new GameClear(this);

		// 메인 패널 하나에 클래스를 추가하기 (그 이름은 , " " 으로 추가한다.)
		mainPanel.add(s_C, "StartPage");
		mainPanel.add(h1_C, "How_1");
		mainPanel.add(h2_C, "How_2");
		mainPanel.add(g_C, "Game");
		mainPanel.add(go_C, "GameOver");
		mainPanel.add(g1_C, "Game_1");
		mainPanel.add(g2_C, "Game_2");
		mainPanel.add(g3_C, "Game_3");
		mainPanel.add(clear_C, "GameClear");
		
		// 패널 기본 JFrame에 추가하기
		add(mainPanel);
		setVisible(true);

		addKeyListener(new KeyAdapter () {
			@Override 
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_SPACE) {
					if(g_C.whatJump() == 0){
						g_C.speedSlow();
					}
					else if (g_C.whatJump() == 1) {
						g_C.speedFast();
					}
					else if (g_C.whatJump() == 2) {
						g_C.speedFast();
					}
				}
			}
		});

		addKeyListener(new KeyAdapter () {
			@Override 
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_SHIFT) {
					g_C.sliding();
				}
			}
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_SHIFT) {
					g_C.running();
				}
			}
		});
	}


	// 화면 전환 메서드
	public void showPage(String name) {
		if(name.equals("GameOver")) {
			go_C.getScore(g_C.sendScore());
		}
		// 카드야 보여줘 메인에 다른 페이지를. 그 페이지 이름(=클래스 이름)은 걔가 줄거야~
		card.show(mainPanel, name);
	}

	public static void main(String[] args) {
		new SpyRunFrame();
	}
	public void restart(int level) {
		// 패널 갱신
		g_C.reset(level);
		mainPanel.revalidate();
		mainPanel.repaint();
		showPage("Game");
	}
}

