package core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Map {
	private int cols = 30;
	private int rows = 15;
	private char selectedOptionValue = ' ';
	private MapLoader mapLoader = new MapLoader();
	private List<MapListener> listeners = new ArrayList<MapListener>();
	private char[][] map;

	public Map() {
		map = new char[rows][cols];
		clear();
		fireMapChange("all");
	}

	public void saveAs() {
		mapLoader.saveFileAs(map);
	}

	public void save() {
		mapLoader.saveFile(map);
	}

	public void openFile() throws NotAMapFileException {
		mapLoader.openFile();
	}

	public void saveFile() {
		mapLoader.saveFile(map);
	}

	public void saveFileAs() {
		mapLoader.saveFileAs(map);
	}

	public boolean addMapEditorListener(MapListener e) {
		return listeners.add(e);
	}

	public void clear() {
		map = new char[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				map[i][j] = selectedOptionValue;
			}
		}

		fireMapChange("all");

	}

	private void fireMapChange(String property) {
		for (MapListener m : listeners) {
			m.mapChanged(this, property);
		}

	}

	/**
	 * @param args
	 *            the command line arguments
	 */

	public void insert(int row, int col) {
		if (row < 0 || col < 0 || row >= map.length || col >= map[0].length) {
			System.out.println("Map.insert(), out of range" + " row = " + row
					+ " col = " + col);
			return;
		}
		map[row][col] = getSelectedOptionValue();
		fireMapChange("change");
	}

	public void setMap(char[][] map) {
		this.map = map;
		cols = map[0].length;
		rows = map.length;
		fireMapChange("all");
	}

	public void setMap(List<String> list) {
		int cols = list.get(0).length();
		int rows = list.size();
		char[][] map = new char[rows][cols];
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			map[i] = s.toCharArray();
		}
		setMap(map);
	}

	public int getCols() {
		return cols;
	}

	public int getRows() {
		return rows;
	}

	public char getSelectedOptionValue() {
		return selectedOptionValue;
	}

	public void setSelectedOptionValue(char selectedOptionValue) {
		this.selectedOptionValue = selectedOptionValue;
	}

	public MapLoader getMapLoader() {
		return mapLoader;
	}

	public void setMapLoader(MapLoader mapLoader) {
		this.mapLoader = mapLoader;
	}

	public List<MapListener> getListeners() {
		return listeners;
	}

	public char[][] toCharMatrix() {
		return map.clone();
	}

	public void reset(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		clear();

	}

	public String toString() {
		String out = "";
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				out += map[i][j];
			}
			out += "\n";
		}

		return out;
	}

	public void drawLine(Point from, Point to) {
		insert(to.y, to.x);
		int col = from.x;
		int row = from.y;
		if (from.x == to.x) {			
			while (true) {
				System.out.println("Map.drawLine()," + " row = " + row + " col = "
						+ col);
				insert(row,col);
				if ( row  == to.y) break;
				row += Math.signum(to.y - row);
			}

		} else if (from.y == to.y) {
			while (true) {
				System.out.println("Map.drawLine()," + " row = " + row + " col = "
						+ col);
				insert(row,col);
				if ( col  == to.x) break;
				col += Math.signum(to.x - col);
			}
		}
	}

}