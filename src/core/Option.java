/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;

/**
 *
 * @author sigurd
 */
public class Option {
	
	private final char value;
	private final Color color;
	private final String description;
	
	public Option(char c, String option, Color color) {
		this.value = c;
		this.description = option;
		this.color = color;		
	}

	public Color getColor() {
		return color;
	}

	public String getDescription() {
		return description;
	}

	public char getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return description;
	}
	
	
	
	
	
	
	
}
