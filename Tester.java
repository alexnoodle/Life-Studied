import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

final public class Tester {

	JFrame frame;
	DrawPanel drawPanel;

	private int oneX = 0;

	private static int dim;
	private static int dim2;

	static ArrayList<int[][]> log = new ArrayList<int[][]>();

	public Tester() {
		// this.openMap();
	}

	public Tester(ArrayList<int[][]> a) throws FileNotFoundException,
			UnsupportedEncodingException {
		log = a;
		dim = log.get(0).length;
		this.go();
	}

	public void go() {
		frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawPanel = new DrawPanel();

		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize((dim * 10) + 50, (dim2 * 10) + 50);
		frame.setLocation(50, 0);
		moveIt();
	}

	class DrawPanel extends JPanel {
		public void paintComponent(Graphics g) {
			for (int i = 0; i < dim * 10; i += 10) {
				for (int j = 0; j < dim2 * 10; j += 10) {
					g.drawRect(i, j, 10, 10);
				}
			}
			for (int k = 0; k < dim; k++) {
				for (int l = 0; l < dim2; l++) {
					// System.out.println(log.size());
					if (oneX < log.size()) {
						if (log.get(oneX)[k][l] == 1) {
							g.fillOval(k * 10, l * 10, 10, 10);
						}
					}

				}
			}
		}
	}

	
	private void moveIt() {

		for (oneX = 0; oneX < log.size(); oneX++) {

			try {
				Thread.sleep(1000);
			} catch (Exception exc) {
			}
			frame.repaint();
		}

	}

	public static ArrayList<int[][]> getLog() {
		return log;
	}

	public static void setLog(ArrayList<int[][]> log) {
		Tester.log = log;
	}
}
