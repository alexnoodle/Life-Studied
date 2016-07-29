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

	static ArrayList<int[][]> log = new ArrayList<int[][]>();
	
	public Tester(ArrayList<int[][]> a){
		log = a;
		dim = log.get(0).length;
		this.go();
		//this.openMap();
	}

	private void go() {
		frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawPanel = new DrawPanel();

		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize((dim * 10) + 50, (dim * 10) + 50);
		frame.setLocation(50, 0);
		moveIt();
	}

	class DrawPanel extends JPanel {
		public void paintComponent(Graphics g) {
			for (int i = 0; i < dim * 10; i += 10) {
				for (int j = 0; j < dim * 10; j += 10) {
					g.drawRect(i, j, 10, 10);
				}
			}
			for (int k = 0; k < dim; k++) {
				for (int l = 0; l < dim; l++) {
					//System.out.println(log.size());
					if (oneX < log.size()) {
						if (log.get(oneX)[k][l] == 1) {
							g.fillOval(k * 10, l * 10, 10, 10);
						}
					}

				}
			}
		}
	}
	
	public void openMap(){
		try{
			Scanner openFile = null;
			openFile = new Scanner(new File("SavedRun.txt"));
			dim = openFile.nextInt();
			int[][] blank = new int[dim][dim];
			while(openFile.hasNextLine()){
				int x;
				int y;
				int next;
				next = openFile.nextInt();
				if(!(next == -1)){
					x = next;
					y = openFile.nextInt();
					blank[x][y] = 1;
				}
				else{
					log.add(blank);
					blank = new int[dim][dim];
				}
			}
			openFile.close();
		}
		catch(Exception e){
			System.out.println("Opening didn't work");
		}
		this.go();
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
}