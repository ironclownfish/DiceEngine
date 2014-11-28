import java.io.IOException;

import javax.swing.JFrame;


public class DiceEngineUI extends JFrame{

	public DiceEngineUI(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//setVisible(true);
	}
	
	public byte requestDieInput(byte index){
		System.out.println("Enter " + index + (index==1?"st":index==2?"nd":"th") + " die value.");
		int in = 0;
		try {
			in = System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (byte)in;
	}
	
	public void showScore(int score){
		System.out.println("My score is " + score);
	}
}
