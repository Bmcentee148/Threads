import javax.swing.*;
import java.awt.FlowLayout;

public class FlashingText extends JFrame implements Runnable{

	private final int TIME_DELAY_IN_SECONDS = 2;
	private JLabel textJLabel;
	private String words;

	public FlashingText(String words) {
		this.words = words;
		this.textJLabel = new JLabel(words);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(textJLabel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(200,100);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		new Thread(this).start();
	}//end constructor

	public void run() {
		try{
			while(true) {
				if(textJLabel.getText() == null) {
					textJLabel.setText(this.words);
				}
				else{
					textJLabel.setText(null);
				}
				//Put thread to sleep to delay the trasition and create 'flash'
				Thread.sleep(TIME_DELAY_IN_SECONDS * 1000);
			}
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}//end run

	public static void main(String[] args) {
		new FlashingText("Hello, world!");
	}//end main
}