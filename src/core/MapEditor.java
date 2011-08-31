package core;

import gui.MainFrame;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author sigurd
 */
public class MapEditor {

	public static final String OPTIONS_PATH = "options/";

	private MainFrame frame;
	
	private OptionsHolder options;

	Map map = new Map();

	public Map getMap() {
		return map;
	}

	public MapEditor() {
		initOptions();

		options = new OptionsHolder();

		frame = new MainFrame(this);

	}

	public MainFrame getFrame() {
		return frame;
	}

	OptionsFolder optionsFolder;

	public OptionsFolder getOptionsFolder() {
		return optionsFolder;
	}

	private void initOptions() {
		optionsFolder = new OptionsFolder(new File(OPTIONS_PATH));
		options = optionsFolder.get(0);
		map.setSelectedOptionValue(options.get(0).getValue());
		map.clear();
	}

	public OptionsHolder getOptions() {
		return options;
	}

	public void setOptions(OptionsHolder options) {
		this.options = options;
		//fireMapChange("newOptions");
	}

}
