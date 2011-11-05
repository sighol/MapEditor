package core;

import static core.FileHandler.openFileChooser;
import static core.FileHandler.saveFileChooser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author sigurd
 */
public class MapLoader {

	public char[][] fetched = null;
	protected File file = null;

	protected FileReader fin;
	protected FileWriter fout;

	protected BufferedReader reader;

	protected BufferedWriter writer;

	public char[][] getFetched() {
		if (fetched == null) {
			throw new NullPointerException();
		}
		return fetched;
	}
	
	public File getFile() {
		return file;
	}

	public String getFileName() {
		return file.getName();
	}

	public void openFile() throws NotAMapFileException {
		fetched = null;

		try {
			file = openFileChooser(getCurrentDirectory());
			setFile(file);
			if (file == null)
				return;
			fin = new FileReader(file);
			reader = new BufferedReader(fin);
			List<String> sl = new ArrayList<String>();

			String line = reader.readLine();
			int cols = line.length();
			while (line != null) {
				if (cols != line.length()) {
					throw new NotAMapFileException(
							"Maps must have same lenght in each line");
				}
				sl.add(line);
				line = reader.readLine();
			}
			fetched = new char[sl.size()][cols];
			for (int i = 0; i < sl.size(); i++) {
				for (int j = 0; j < cols; j++) {
					fetched[i][j] = sl.get(i).charAt(j);
				}
			}
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	public void saveFile(char[][] map) {
		if (file != null) {
			write(map);
			return;
		}
		saveFileAs(map);
	}

	public void saveFileAs(char[][] map) {
		File file = saveFileChooser(getCurrentDirectory());
		if (file != null)
			setFile(file);
		write(map);
	}

	public void setFile(File file) {
		this.file = file;
		// TODO implementere støtte for å endre tittel ved opplasting.
	}

	private File getCurrentDirectory() {
		return file != null ? file.getParentFile() : new File(".");
	}

	private void write(char[][] map) {
		if (file == null)
			return;

		if (map == null) {
			throw new NullPointerException();
		}
		int rows = map.length;
		if ( rows == 0) {
			throw new IllegalArgumentException("map contains 0 rows");
		}
		
		int cols = map[0].length;
		if (cols == 0) {
			throw new IllegalArgumentException("map contains 0 cols");
		}
		
		try {
			fout = new FileWriter(file);
			writer = new BufferedWriter(fout);

			for (int i = 0; i < rows; i++) {
				String s = String.valueOf(map[i]);
				writer.write(s);
				writer.newLine();
			}
			writer.close();
		} catch (IOException ex) {
			ex.getStackTrace();

		}

	}

}
