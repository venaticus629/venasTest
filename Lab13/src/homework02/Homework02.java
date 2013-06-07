package homework02;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Homework02 extends JComponent {
	private static final long serialVersionUID = 1L;

	private Image[] frameList;

	private long msPerFrame;
	private volatile int currFrame;

	private Thread internalThread;

	public Homework02(int width, int height, long msPerCycle, int framesPerSec, Color fgColor) {

		setPreferredSize(new Dimension(width, height));

		int framesPerCycle = (int) ((framesPerSec * msPerCycle) / 1000);
		msPerFrame = 1000L / framesPerSec;

		// 구현.. 값 초기화

		Runnable r = new Runnable() {
			public void run() {
				try {
					runWork();
				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		};

		// 구현... 쓰레드 생성
	}

	public void paint(Graphics g) {
		// 구현... g.drawImage() 함수 이용
		// g.drawImage(image, x, y, this);
	}
	
	private void runWork() {
		// 구현...
		// 배열 인덱스 값 변경
	}
	
	private Image[] buildImages(int width, int height, Color color, int count) {

		BufferedImage[] im = new BufferedImage[count];

		for (int i = 0; i < count; i++) {
			im[i] = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

			double xShape = ((double) (i * width)) / (double) count;
			double yShape = ((double) (i * height)) / (double) count;

			double wShape = 2.0 * (width - xShape);
			double hShape = 2.0 * (height - yShape);
			Rectangle2D shape = new Rectangle2D.Double(xShape, yShape, wShape, hShape);

			Graphics2D g2 = im[i].createGraphics();
			g2.setColor(color);
			g2.fill(shape);
			g2.dispose();
		}

		return im;
	}

	public static void main(String[] args) {
		Homework02 redSquish = new Homework02(250, 200, 2500L, 10, Color.red);
		JFrame f = new JFrame();
		f.setLayout(new FlowLayout());
		f.add(redSquish);

		f.setSize(450, 250);
		f.setVisible(true);
	}
}
