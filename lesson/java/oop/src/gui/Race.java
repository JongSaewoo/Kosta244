package gui;

import java.awt.Canvas;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * 이벤트프로그램 절차 1. EventSource(이벤트발생될 곳)을 결정 ex)btReady 2. 이벤트 종류를 결정
 * ex)ActionEvent 3. EventHandler(이벤트 발생했을 때 할일)을 작성 ex)class MyHandler
 * implements ActionListener{ public void actionPerformed(ActionEvent e){}
 * sysout("준비되었습니다"); } } 4. EventSource와 EventHandler를 연결 ex)btReady.add~~~(new
 * MyHandler());
 * 
 * @author KMJ
 *
 */

//class MyRunHandler implements ActionListener {
//	private JTextField jtf;
//
//	public MyRunHandler(JTextField jtf) {
//		this.jtf = jtf;
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		jtf.setText("달립니다");
//	}
//}

//말3마리가 달리기경주
class Horse extends Canvas implements Runnable { // 상속, thread
	String name;
	int x = 10, y = 10;

	Horse(String name) {
		this.name = name;
	}

	@Override
	public void paint(Graphics g) {
		g.drawString(name, x, y);
	}

//	@Override
//	public void update(Graphics g) { // 새로운 Thread에 의해서 자동추출됨.
//		for (int step = 0; step < 20; step++) {
//			System.out.println("canvase의 update");
//			x += 20;
//			super.update(g); // clear -> paint()자동호출됨
//			long millis =(long)(Math.random()*1000); //0.0<=r<1000.0
//			try {
//				Thread.sleep(millis);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	@Override
	public void run() {
//		this.repaint(); // 호출하면 run메서드가 자동으로 끝남, 종료
		for (int step = 0; step < 20; step++) {
			x += 20;
			this.repaint();
			long millis = (long) (Math.random() * 1000); // 0.0<=r<1000.0
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}

public class Race {
	private JFrame jf;
	private JButton btStart, btReady;
	private JTextField jtf;
	private Horse[] horses;

	class MyRunHandler implements ActionListener {
		private JTextField jtf;

		public MyRunHandler(JTextField jtf) {
			this.jtf = jtf;
		}

		public void actionPerformed(ActionEvent e) {
			jtf.setText("달립니다");
//			for(int step = 0; step<20; step++) {
//				horses[0].repaint();
//			}
//			horses[0].repaint();
//			horses[1].repaint();
//			horses[2].repaint();

			// 스레드 시작
			for (int i = 0; i < horses.length; i++) {
				new Thread(horses[i]).start();
			}
		}
	}

	public Race() {
		jf = new JFrame("달리기"); // 액자
		btStart = new JButton("달려");
		btReady = new JButton("준비");
		jtf = new JTextField(10);

		horses = new Horse[3];
		String[] horseNames = { "태풍", "화랑", "번개" };
		for (int i = 0; i < horses.length; i++) {
			horses[i] = new Horse(horseNames[i]);
		}

		Container c = jf.getContentPane(); // 액자뒷판
//		c.setLayout(new FlowLayout());
//		c.add(btReady);
//		c.add(btStart);
//		c.add(jtf);
		c.setLayout(new GridLayout(5, 1));
		c.add(horses[0]);
		c.add(horses[1]);
		c.add(horses[2]);

		Panel panel = new Panel();
		panel.add(btReady);
		panel.add(btStart);
		c.add(panel);
		c.add(jtf);

//		MyHandler myHandler = new MyHandler();
//		btReady.addActionListener(myHandler);
//		btReady.addActionListener(new ActionListener() { //익명클래스, 인터페이스로 처리
//			public void actionPerformed(ActionEvent e) {
//				// System.out.println("준비되었습니다");
//				jtf.setText("준비되었습니다");
//				for(Horse h: horses) {
//					h.x = 0;
//					h.repaint();
//				}
//			}
//		}
//	);
		// 람다식
		btReady.addActionListener((ActionEvent e) -> {
			jtf.setText("준비되었습니다");
			for (Horse h : horses) {
				h.x = 0;
				h.repaint();
			}

		});

		// btReady.addActionListener(new ActionListener());
		btStart.addActionListener(new MyRunHandler(jtf));

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jf.setSize(500, 300);
		jf.setVisible(true);
	}

	public static void main(String[] args) {
		new Race();
	}
}
