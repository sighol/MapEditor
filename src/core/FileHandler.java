package core;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import javax.swing.JFileChooser;

public class FileHandler {
	public static final String CLASS_RESOURCES_PATH = "/resources/";

	public static Image getImage(String filnavn) {
		Image bilde = null;
		URL url;
		try {
			url = getResource(filnavn);
			bilde = Toolkit.getDefaultToolkit().getImage(url);
		} catch (FileNotFoundException e) {
			System.err.println("Finner ikke filen '" + filnavn + "'");
			e.printStackTrace();
		}
		return bilde;
	}

	public static URL getResource(String filnavn) throws FileNotFoundException {
		String filepath = CLASS_RESOURCES_PATH + filnavn;
		URL url = FileHandler.class.getResource(filepath);
		
		if (url == null) {
			throw new FileNotFoundException("Resource not found: '" + filepath + "'");
		}
		return url;
	}

	public static File openFileChooser(File currentDirectory) {
		JFileChooser fc = new JFileChooser(currentDirectory);
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			return file;
		}
		return null;
	}

	public static File saveFileChooser(File currentDirectory) {
		JFileChooser fc = new JFileChooser(currentDirectory);
		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			return file;
		}
		return null;
	}
}