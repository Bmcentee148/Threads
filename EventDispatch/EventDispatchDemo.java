/** Most of the time it is okay to launch a GUI app directly from the main method.
	This causes it to be created on the main Thread, although it will still run
	on the AWT.Event Thread. If your GUI app is complex and takes a long time to
	start up problems can arise. If this is the case, you can launch you app from
	the AWT.Event Thread using the SwingUtilities class as demonstrated. */

import javax.swing.*;

public class EventDispatchDemo {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Test");
				frame.setSize(200,100);
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}