package Navigation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollBar;
import javax.swing.JSplitPane;
import java.awt.Canvas;

public class DisplayScreen {

	private JFrame frame;
	int count1=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					DisplayScreen window = new DisplayScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DisplayScreen() {
	
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setAutoscrolls(true);
		splitPane.setContinuousLayout(true);
		splitPane.setOneTouchExpandable(true);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		DisplayMap panel = new DisplayMap();
		splitPane.setRightComponent(panel);
		
		Canvas canvas = new Canvas();
		splitPane.setLeftComponent(canvas);
	
	}

}
