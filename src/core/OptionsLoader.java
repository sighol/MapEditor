/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author sigurd
 */
public class OptionsLoader {

	File file;
	Scanner input;
	
	


	public OptionsHolder open() throws FileNotFoundException {
		openFileChooser();
		return loadOptions(file);

	}

	public File openFileChooser() {


		JFileChooser fc = new JFileChooser(getCurrentDirectory());
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			return file;
		}
		return null;
	}

	public File getCurrentDirectory() {
		return file != null ? file.getParentFile() : OptionsFolder.DEFAULT_OPTIONS_FILE;
	}

	public OptionsHolder loadOptions(File file) throws FileNotFoundException, NullPointerException {

		input = new Scanner(file);
		List<Option> list = new ArrayList<Option>();

		String line = "";
		while (input.hasNext()) {
			char value;
			String description;
			Color color;
			String[] cs;
			line = input.nextLine();
			String[] prefs = line.split(" ");
			
			// special case for the empty char
			if (prefs.length == 4) {
				value = ' ';
				description = prefs[2];
				cs = prefs[3].split(",");				
			} else {			
				value = prefs[0].charAt(0);
				description = prefs[1];
				cs = prefs[2].split(",");
			}
			

			color = new Color(Integer.valueOf(cs[0]), Integer.valueOf(cs[1]), Integer.valueOf(cs[2]));

			
			Option pref = new Option(value, description, color);
			list.add(pref);
		}
		
		OptionsHolder holder = new OptionsHolder(list, file.getName());
		return holder;

	}
}
