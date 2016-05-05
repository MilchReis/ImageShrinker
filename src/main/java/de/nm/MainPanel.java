package de.nm;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private AppModel appmodel;
	
	private JButton source, go;
	private JCheckBox override;
	
	public MainPanel(AppModel model) {
		super(new GridLayout(3, 1));
		this.appmodel = model;
		
		source = new JButton("1. Bildordner wählen");
		source.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	appmodel.setSource(fileChooser.getSelectedFile());
			    }
			}
		});
		
		override = new JCheckBox("2. Bilder überschreiben");
		override.setSelected(false);
		override.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				appmodel.setOverride(override.isSelected());
			}
		});
		
		go = new JButton("3. Bilder schrumpfen");
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appmodel.shrinkImages();
			}
		});
		
		add(source);
		add(override);
		add(go);
	}
}
