/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MapPanel.java
 *
 * Created on May 23, 2011, 4:33:46 PM
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import core.Map;
import core.MapEditor;
import core.MapListener;
import core.Option;

/**
 *
 * @author sigurd
 */
public class MapPanel extends javax.swing.JPanel implements MapListener {

	private MapEditor editor;
	private MainFrame frame;
	private int cellSize = 16;
	private char[][] mapMatrix;
	private Map map;
	private int rows;
	private int cols;

	/** Creates new form MapPanel */
	public MapPanel() {

	}
	
	public void init(MapEditor editor, MainFrame frame) {
		this.editor = editor;
		this.frame = frame;
		map = editor.getMap();
		mapMatrix = map.toCharMatrix();

		initCellSize();
		map.addMapEditorListener(this);
		initComponents();
		
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (mapMatrix != null) {
			paintMap(g);
		} else {
			System.out
					.println("MapPanel.paintComponent()," + " (mapMatrix == null) = " + (mapMatrix == null));
		}
	}

	private void paintMap(Graphics g) {
		for (int row = 0; row < mapMatrix.length; row++) {
			for (int col = 0; col < mapMatrix[0].length; col++) {
				char c = mapMatrix[row][col];
				g.setColor(Color.gray);
				for (Option p : editor.getOptions()) {
					if (p.getValue() == c) {
						g.setColor(p.getColor());
					}
				}
				g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
				g.setColor(Color.gray);
				g.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
			}
		}

	}

	void setFrame(MainFrame frame) {
		this.frame = frame;
	}

	private void initCellSize() {
		if (mapMatrix == null) {
			return;
		}
		rows = mapMatrix.length;
		cols = mapMatrix[0].length;
		int maxCellHeight = getHeight() / rows;
		int maxCellWidth = getWidth() / cols;

		if (maxCellHeight >= maxCellWidth) {
			cellSize = maxCellWidth;
		} else {
			cellSize = maxCellHeight;
		}
	}

	@Override
	public void mapChanged(Map editor, String property) {
			mapMatrix = this.map.toCharMatrix();
			initCellSize();

		
		repaint();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

	private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
		int row = evt.getY() / cellSize;
		int col = evt.getX() / cellSize;
		Point newPosition = new Point(col,row);
		if (prePosition == null) prePosition = newPosition;
		map.drawLine(prePosition, newPosition);
//		map.insert( row, col);
		prePosition = newPosition;
		
	}//GEN-LAST:event_formMouseDragged

	private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
		formMouseDragged(evt);
	}//GEN-LAST:event_formMousePressed

	private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
		initCellSize();
		repaint();
	}//GEN-LAST:event_formComponentResized
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
	
	Point prePosition;
}
