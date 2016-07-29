import java.util.ArrayList;
import java.util.Scanner;

public class Builder extends LoadSave {
	private String path;
	private ArrayList<int[][]> map;
	private ArrayList<Shape> shapes;

	public Builder() {
		 
	}

	public Builder(String a) {
		path = a;
		map = load(a);
	}

	public Builder(String path, ArrayList<int[][]> map, ArrayList<Shape> shapes) {
		super();
		this.path = path;
		this.map = map;
		this.shapes = shapes;
	}

	public void load() {
		map = super.load(path);
	}

	public void save() {
		super.save(map, path);
	}

	public int[][] newMap(int dimension) {
		if (map != null) {
			System.out.println("Overwrite?: y/n");
			Scanner scanner1 = new Scanner(System.in);
			String response = scanner1.next();
			if (response.equals("n")) {
				return null;
			}
		}
		map = new ArrayList<int[][]>();
		map.add(new int[dimension][dimension]);

		return map.get(0);
	}

	public void launchBuilder(Shape[] shapes) {
		Scanner scanner1 = new Scanner(System.in);
		System.out.println("New map or Current map?: n/m");
		String response = scanner1.next();
		if (response.equals("n")) {
			System.out.println("Specify Dimensions: #");
			response = scanner1.next();
			newMap(Integer.parseInt(response));
		}
		while (true) {
			System.out.println("Quit or Add?: q/a");
			response = scanner1.next();
			if (response.equals("q"))
				return;

			int x;
			int y;
			System.out.println("X position: # < " + map.get(0).length);
			response = scanner1.next();
			x = Integer.parseInt(response);

			System.out.println("Y position: # < " + map.get(0).length);
			response = scanner1.next();
			y = Integer.parseInt(response);

			System.out.println("Point or Shape?: p/n");
			response = scanner1.next();
			if (response.equals("p")) {
				System.out.println("Set to?: #");
				response = scanner1.next();
				setPoint(x, y, Integer.parseInt(response));
			} else {
				String out = "";
				for (int i = 0; i < shapes.length; i++) {
					out += i;
					out += " " + shapes[i].getName() + " ";
				}
				System.out.println("Shape ID: #");

				response = scanner1.next();
				setShape(x, y, Integer.parseInt(response), 1);
			}
		}
		

	}

	public void setPoint(int x, int y, int toggle) {
		map.get(0)[x][y] = toggle;
	}

	public void setShape(int x, int y, int shape, int toggle) {

		for (int i = 0; i < shapes.get(shape).getxDeviation().size(); i++) {
			map.get(0)[shapes.get(shape).getxDeviation().get(i)][shapes
					.get(shape).getyDeviation().get(i)] = toggle;
		}

	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ArrayList<int[][]> getMap() {
		return map;
	}

	public void setMap(ArrayList<int[][]> map) {
		this.map = map;
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

}
