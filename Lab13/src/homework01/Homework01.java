package homework01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Homework01 extends JComponent {
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;
	private Dimension imageSize;
	
	private volatile int currOffset;
	private Thread internalThread;

	public Homework01(String text) {
		currOffset = 0;
		buildImage(text);

		setMinimumSize(imageSize);
		setPreferredSize(imageSize);
		setMaximumSize(imageSize);
		setSize(imageSize);

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
		// currOffset 값 변경
	}
	
	private void buildImage(String text) {
		RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		BufferedImage scratchImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

		Graphics2D scratchG2 = scratchImage.createGraphics();
		scratchG2.setRenderingHints(renderHints);

		Font font = new Font("Serif", Font.BOLD | Font.ITALIC, 24);

		FontRenderContext frc = scratchG2.getFontRenderContext();
		TextLayout tl = new TextLayout(text, font, frc);
		Rectangle2D textBounds = tl.getBounds();
		int textWidth = (int) Math.ceil(textBounds.getWidth());
		int textHeight = (int) Math.ceil(textBounds.getHeight());

		int horizontalPad = 10;
		int verticalPad = 6;

		imageSize = new Dimension(textWidth + horizontalPad, textHeight + verticalPad);

		image = new BufferedImage(imageSize.width, imageSize.height, BufferedImage.TYPE_INT_RGB);

		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHints(renderHints);

		int baselineOffset = (verticalPad / 2) - ((int) textBounds.getY());

		g2.setColor(Color.white);
		g2.fillRect(0, 0, imageSize.width, imageSize.height);

		g2.setColor(Color.blue);
		tl.draw(g2, 0, baselineOffset);

		scratchG2.dispose();
		scratchImage.flush();
		g2.dispose();
	}


	public static void main(String[] args) {
		Homework01 st = new Homework01("우리는 스레드로 애니메이션을 할 수 있어요!");

		JPanel p = new JPanel(new FlowLayout());
		p.add(st);

		JFrame f = new JFrame("ScrollText Demo");
		f.setContentPane(p);
		f.setSize(400, 100);
		f.setVisible(true);
	}
}
