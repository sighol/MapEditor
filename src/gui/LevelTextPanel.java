/*
 * LevelTextPanel.java
 *
 * Created on May 22, 2011, 3:21:39 PM
 */
package gui;

import core.Map;
import core.MapListener;
import core.MapEditor;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

/**
 *
 * @author sigurd
 */
public class LevelTextPanel extends javax.swing.JPanel implements MapListener {

    MapEditor editor;
    Map map;

    public LevelTextPanel() {
        initComponents();
    }

    public void setMapeditor(MapEditor editor) {
        this.editor = editor;
        map = editor.getMap();
        map.addMapEditorListener(this);
        write();
    }

    public void write() {
    	char[][] map = editor.getMap().toCharMatrix();
        textArea.setText("");
        for (int row = 0; row < map.length; row++) {
            String s = String.valueOf(map[row]);
            textArea.append(s + "\n");
        }
    }

    public void copy() {
		textArea.selectAll();
		textArea.copy();
   }

    @Override
    public void mapChanged(Map map, String property) {
        write();
    }

    private void load() {
		StringTokenizer st = new StringTokenizer(textArea.getText(), "\n");
        ArrayList<String> stringMap = new ArrayList<String>();
        int cols;
		if (st.hasMoreTokens()) {
			String firstToken = st.nextToken();
			stringMap.add(firstToken);
        	cols = firstToken.length();
        } else {
        	JOptionPane.showMessageDialog(this, "Map is empty");
        	return;
        }
        while (st.hasMoreTokens()) {
            String nextToken = st.nextToken();
            if (nextToken.length() != cols) {
            	String message = "The rows doesn't have same the lenght\n" +
            			"Line: "+ (stringMap.size() + 1) + " has " + nextToken.length() + " lines\n" +
            			"Line: 1 has " + cols + " lines";
            	JOptionPane.showMessageDialog(this, message, "Illegal row lenght",0);
            	return;            	
            }
			stringMap.add(nextToken);
        }
		map.setMap(stringMap);
	}

    /** Creates new form LevelTextPanel */
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        copyButton = new javax.swing.JButton();
        loadThisMapButton = new javax.swing.JButton();

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        copyButton.setText("Copy");
        copyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyButtonActionPerformed(evt);
            }
        });

        loadThisMapButton.setText("Load");
        loadThisMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onLoadThisMapButton(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loadThisMapButton)
                        .addGap(18, 18, 18)
                        .addComponent(copyButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(copyButton)
                    .addComponent(loadThisMapButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void onLoadThisMapButton(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onLoadThisMapButton
        load();
    }//GEN-LAST:event_onLoadThisMapButton

	

	private void copyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyButtonActionPerformed
		copy();		// TODO add your handling code here:
	}//GEN-LAST:event_copyButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton copyButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadThisMapButton;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}
