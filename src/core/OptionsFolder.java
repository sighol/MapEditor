/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author sigurd
 */
public class OptionsFolder implements Iterable<OptionsHolder> {

	private List<OptionsHolder> list = new ArrayList<OptionsHolder>();

	// TODO:
	public static final File DEFAULT_OPTIONS_FILE = new File(
			MapEditor.OPTIONS_PATH);
	private static final File DEFAULT_OPTIONS_FILE_ABSOLUTE = new File(
			"/home/sigurd/workspace/MapEditor/options");

	private File folder;

	public OptionsFolder(File folder) {
		this.folder = folder;
		if (!folder.isDirectory()) {
			folder = DEFAULT_OPTIONS_FILE_ABSOLUTE;
			if (!folder.isDirectory()) {
				OptionsLoader l = new OptionsLoader();
				folder = l.openFileChooser().getParentFile();
			}
		}
		File[] files = folder.listFiles();
		if (files == null)
			throw new IllegalStateException("folder was empty,"
					+ " even though it should have been checked: "
					+ folder.getAbsolutePath());

		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			OptionsLoader p = new OptionsLoader();
			try {
				OptionsHolder options = p.loadOptions(f);
				list.add(options);
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
		}

	}

	public OptionsHolder get(int i) {
		return list.get(i);
	}

	@Override
	public Iterator<OptionsHolder> iterator() {
		return list.iterator();
	}

	public String getAbsolutePath() {
		return folder.getAbsolutePath();
	}

	public File getAbsoluteFile() {
		return folder.getAbsoluteFile();
	}

	public boolean exists() {
		return folder.exists();
	}

	public int size() {
		return list.size();
	}

}
