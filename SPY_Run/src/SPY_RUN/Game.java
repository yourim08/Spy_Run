package SPY_RUN;

import java.util.Timer;
import java.util.Arrays;
import java.util.TimerTask;
import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import javax.swing.*;
import java.util.Random;

public class Game extends JPanel {
	Font font = new Font("NanumGothic", Font.PLAIN, 20);
	Random r = new Random();
	int score = 0;
	int x1 = 0;
	int x2 = 2200;
	int x_icon = 25; 
	int x_cone1 = r.nextInt(400)+1100;
	int x_cone2 = r.nextInt(400)+1932;
	int x_cone3 = r.nextInt(400)+2832;
	int x_spike1 = x_cone1+260;
	int x_spike2 = x_cone3+260;
	int x_spike_long = x_cone2+260;
	int status = 0; // 0: run, 1: jump, 2: sliding
	long jumpTime;
	int speed = 1;
	int temp = 0;
	boolean jump = true;
	boolean sliding = true;
	boolean flag_1 = false;
	boolean flag_2 = false;
	boolean flag_3 = false;
	boolean timer_on_off = false;
	
	JLabel g_spy;
	JLabel g_jump;
	JLabel g_sliding;
	JLabel g_cone1;
	JLabel g_cone2;
	JLabel g_cone3;
	JLabel g_spike1;
	JLabel g_spike2;
	JLabel g_back1;
	JLabel g_back2;
	JLabel g_icon;
	JLabel g_bar;
	JLabel jump_fast;
	JLabel g_score;
	JLabel g_spike_long;

	Timer timer;
	Timer gauge_timer;

	SpyRunFrame frame;

	public Game(SpyRunFrame frame) {

		this.frame = frame;
		setLayout(null);

		ImageIcon back1 = new ImageIcon("img/g_back.png");
		ImageIcon back2 = new ImageIcon("img/g_back.png");
		ImageIcon bar = new ImageIcon("img/g_bar.png");
		ImageIcon cone1 = new ImageIcon("img/g_cone.png");
		ImageIcon cone2 = new ImageIcon("img/g_cone.png");
		ImageIcon cone3 = new ImageIcon("img/g_cone.png");
		ImageIcon spike1 = new ImageIcon("img/g_spike.gif");
		ImageIcon spike2 = new ImageIcon("img/g_spike.gif");
		ImageIcon spy = new ImageIcon("img/g_spy.gif");
		ImageIcon jump = new ImageIcon("img/g_jump.gif");
		ImageIcon fastjump = new ImageIcon("img/g_jump_fast.gif");
		ImageIcon icon = new ImageIcon("img/g_icon.png");
		ImageIcon sliding = new ImageIcon("img/g_sliding.png");
		ImageIcon spikelong = new ImageIcon("img/g_spike_long.gif");
		g_score = new JLabel(String.valueOf(score)+"m");


		g_score.setFont(font);
		g_score.setForeground(Color.WHITE);
		g_spike_long = new JLabel(spikelong);
		jump_fast = new JLabel(fastjump);
		g_jump = new JLabel(jump);
		g_sliding = new JLabel(sliding);
		g_cone1 = new JLabel(cone1);
		g_cone2 = new JLabel(cone2);
		g_cone3 = new JLabel(cone3);
		g_spike1 = new JLabel(spike1);
		g_spike2 = new JLabel(spike2);
		g_back1 = new JLabel(back1);
		g_back2 = new JLabel(back2);
		g_bar = new JLabel(bar);
		g_spy = new JLabel(spy);
		g_icon = new JLabel(icon);

		add(g_spike_long);
		add(g_score);
		add(jump_fast);
		add(g_sliding);
		add(g_jump);
		add(g_icon);
		add(g_spy);
		add(g_cone1);
		add(g_cone2);
		add(g_cone3);
		add(g_spike1);
		add(g_spike2);
		add(g_bar);
		add(g_back1);
		add(g_back2);
		g_spike_long.setVisible(false);
		jump_fast.setVisible(false);
		g_jump.setVisible(false);
		g_sliding.setVisible(false);
		g_score.setBounds(75, 70, 100, 100);
		jump_fast.setBounds(200,230,135,320);
		g_jump.setBounds(200,230,135,320);
		g_spy.setBounds(200,360,135,165);
		g_sliding.setBounds(210,390,150,165);
		g_bar.setBounds(55, 10, 1000, 100);
		g_icon.setBounds(x_icon, -22, 135, 165);


		setFocusable(true);
		requestFocusInWindow(); 
		// 움직이는 배경과 랜덤 장애물, 히트 판정 타이머 
		TimerStart();
		timer_on_off = true;
	}   

	public void TimerStart() {

		// 1. 움직이는 배경
		timer = new Timer();
		TimerTask timertask = new TimerTask(){
			@Override
			public void run () {

				// 끝까지 가면 다시 x좌표 초기화!
				if(x1 == -2200) {
					x1+=4400;
				}
				if(x2 == -2200) {
					x2+=4400;
				}
				x1-=1;
				x2-=1;
				g_back1.setBounds(x1,0,2200,700);
				g_back2.setBounds(x2,0,2200,700);


				// 2. 랜덤으로 나타나는 장애물

				if(x_cone1<-100) {
					x_cone1 = x_cone3+r.nextInt(200)+700;
					flag_1 = true;
				} 
				if(x_cone2<-100) {
					x_cone2 = x_cone1+r.nextInt(200)+700;
					flag_2 = true;
				} 
				if(x_cone3<-100) {
					x_cone3 = x_cone2+r.nextInt(200)+700;
					flag_3 = true;
				} 

				if(x_spike1<-100) {
					x_spike1 = x_cone1+300;
				}
				if(x_spike2<-100) {
					x_spike2 = x_cone3+300;
				}
				if(x_spike_long<-100) {
					x_spike_long = x_cone2+400;
				}
				flag_1 = false;
				flag_2 = false;
				flag_3 = false;

				x_spike1-=speed;
				x_spike2-=speed;
				x_spike_long-=speed;
				x_cone1-=speed;
				x_cone2-=speed;
				x_cone3-=speed;


				g_spike1.setBounds(x_spike1, 180, 110, 250);
				g_spike2.setBounds(x_spike2, 180, 110, 250);
				g_spike_long.setBounds(x_spike_long, 180, 200, 250);
				g_cone1.setBounds(x_cone1, 445, 100, 100);
				g_cone2.setBounds(x_cone2, 445, 100, 100);
				g_cone3.setBounds(x_cone3, 445, 100, 100);


				// 3. 죽음 판정
				if ((x_cone1 <= 290 && x_cone1 >= 210) || (x_cone2 <= 290 && x_cone2 >= 210) || (x_cone3 <= 290 && x_cone3 >= 210)) {
					if(status != 1){
						timer.cancel();
						timer_on_off = false;
						g_spy.setVisible(false);
						gauge_timer.cancel();
						frame.showPage("GameOver");
						
					}
				}
				if ((x_spike1 <= 290 && x_spike1 >= 210) || (x_spike2 <= 290 && x_spike2 >= 210)) {
					if(status != 2){
						timer.cancel();
						timer_on_off = false;
						g_spy.setVisible(false);
						gauge_timer.cancel();
						frame.showPage("GameOver");
						
					}
				}
				if(x_icon>488) {
					g_spike_long.setVisible(true);
					if((x_spike_long <= 300 && x_spike_long >= 100)) {
						if(status != 2){
							timer.cancel();
							timer_on_off = false;
							gauge_timer.cancel();
							g_spy.setVisible(false);
							frame.showPage("GameOver");
							
						}
					}
				}
			}
		};
		timer.schedule(timertask, 10,7);


		gauge_timer = new Timer();
		gauge_timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (x_icon == 250) {
					timer.cancel();
					gauge_timer.cancel();
					timer_on_off = false;
					g_spy.setVisible(false);
					frame.showPage("Game_1");
					revalidate(); 
					repaint();
				}
				if (x_icon == 488) {
					timer.cancel();
					gauge_timer.cancel();
					timer_on_off = false;
					g_spy.setVisible(false);
					frame.showPage("Game_2");
				}
				if (x_icon == 728) {
					timer.cancel();
					gauge_timer.cancel();
					timer_on_off = false;
					g_spy.setVisible(false);
					frame.showPage("Game_3");
				}
				if (x_icon == 946) {
					timer.cancel();
					gauge_timer.cancel();
					timer_on_off = false;
					g_spy.setVisible(false);
					frame.showPage("GameClear");
				}
				x_icon += 1;
				score += 1;
				g_score.setText(String.valueOf(score) + "m");
				g_icon.setBounds(x_icon, -22, 135, 165);
			}
		}, 3000, 100); 

	}


	// 4. 점프와 슬라이딩

	public void jump() {
		jumpTime = System.currentTimeMillis(); 
		if(sliding) {
			Timer j_time = new Timer();
			TimerTask j_task = new TimerTask() {
				@Override
				public void run() {
					long j_Time = System.currentTimeMillis() - jumpTime; 

					jump = false;
					if(j_Time < 1400 ) {
						g_spy.setVisible(false);
						status =1;
						g_jump.setVisible(true);
					}
					else {
						status = 0;
						g_spy.setVisible(true);
						g_jump.setVisible(false);
						j_time.cancel();
						jump = true;
					}
				}
			};

			j_time.schedule(j_task, 10, 100);
		}
	}

	public void jumpFast() {
		jumpTime = System.currentTimeMillis(); 
		if(sliding) {
			Timer j_time = new Timer();
			TimerTask j_task = new TimerTask() {
				@Override
				public void run() {
					long j_Time = System.currentTimeMillis() - jumpTime; 

					jump = false;
					if(j_Time < 800 ) {
						g_spy.setVisible(false);
						status =1;
						jump_fast.setVisible(true);
					}
					else {
						status = 0;
						g_spy.setVisible(true);
						jump_fast.setVisible(false);
						j_time.cancel();
						jump = true;
					}

				}
			};

			j_time.schedule(j_task, 10, 100);
		}
	}

	public void sliding() {
		if(jump) {
			sliding = false;
			g_spy.setVisible(false);
			status = 2;
			g_sliding.setVisible(true);
		}
	}

	public void running() {
		if(jump) {
			g_spy.setVisible(true);
			status = 0;
			g_sliding.setVisible(false);
			sliding = true;
		}
	}

	public void reset(int level) {
		if(level == 0){
			x_icon = 24;
			speed = 1;
			score = 0;
		}
		else if(level == 1) {
			speed = 2;
			x_icon = 252;
		}
		else if(level == 2){
			x_icon = 490;
			speed = 2;
		}
		else if(level == 3) {
			x_icon = 730;
			speed = 2;
		}

		if (x_icon>946) {
			score = 0;
		}
		x1 = 0;
		x2 = 2200;
		x_cone1 = r.nextInt(400)+1100;
		x_cone2 = r.nextInt(400)+1932;
		x_cone3 = r.nextInt(400)+2732;
		x_spike1 = x_cone1+260;
		x_spike2 = x_cone3+260;
		x_spike_long = x_cone2+260;
		status = 0;

		jump = true;
		sliding = true;
		flag_1 = false;
		flag_2 = false;
		flag_3 = false;
		
		g_jump.setVisible(false);
		g_sliding.setVisible(false);
		g_spike_long.setVisible(false);

		g_cone1.setBounds(x_cone1, 445, 100, 100);
		g_cone2.setBounds(x_cone2, 445, 100, 100);
		g_cone3.setBounds(x_cone3, 445, 100, 100);
		g_spike1.setBounds(x_spike1, 180, 110, 250);
		g_spike2.setBounds(x_spike2, 180, 110, 250);

		// 새 타이머 시작
		TimerStart();
		timer_on_off = true;
		g_spy.setVisible(true);
	}

	public void speedFast() {
		jumpFast();
	}
	public void speedSlow() {
		jump();
	}

	public int whatJump() {
		if(x_icon<250) {
			return 0; 
		}
		else if(x_icon>250){
			return 1; 
		}
		else if(x_icon>488) {
			return 2;
		}
		return score;
	}
	
	public int sendScore() {
		return score;
	}

	public void TimerStop() {
		timer.cancel();
		gauge_timer.cancel();
	}

	public boolean timercheck() {
		return timer_on_off;
	}
}