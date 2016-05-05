package de.nm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ProgressDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private JLabel message;
	private JTextArea output;
	private JButton close;
	
	public ProgressDialog() {
		setSize(450, 300);
		setUndecorated(false);
		setLayout(new BorderLayout());
		setLocationRelativeTo( null );
		
		output = new JTextArea();
		output.setEditable(false);
		
		close = new JButton("Schließen");
		close.setEnabled(false);
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProgressDialog.this.dispose();
			}
		});
		
		message = new JLabel("Bitte warten ...");
		
		add(message, BorderLayout.NORTH);
		add(new JScrollPane(output), BorderLayout.CENTER);
		add(close, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public void update(File f) {
		String s = output.getText();
		output.setText(f.getAbsolutePath() + "\n" + s);
	}

	public void finish() {
		message.setText("Fertig");
		close.setEnabled(true);
	}
}
