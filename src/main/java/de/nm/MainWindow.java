package de.nm;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	public MainWindow(AppModel model) {
		super("ImageShrinker");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		setLayout(new BorderLayout());
		add(new MainPanel(model), BorderLayout.CENTER);

		setVisible(true);
	}
}
