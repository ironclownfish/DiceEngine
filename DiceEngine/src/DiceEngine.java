import java.util.ArrayList;


public class DiceEngine {

	private DiceEngineUI gui;
	private ArrayList<Player> players;
	private Player me, my = me, I = me;
	
	public static byte ALL_DICE = ~0;
	public static byte NO_DICE = 0;
	
	private int POINTS_TO_GET_ON_BOARD = 1000;
	private int POINTS_TO_WIN = 10000;
	
	public static void main(String[] args) {
		DiceEngine engine = new DiceEngine();
		engine.playGame();
	}
	
	public DiceEngine(){
		gui = new DiceEngineUI();
		
		me = new Player();
		players = new ArrayList<Player>();
		players.add(me);
	}
	
	public void playGame(){
		
	}
	
	private void takeTurn(){
		
		byte[] roll = {0,0,0,0,0,0};
		byte diceToKeep = NO_DICE;
		int points = 0;
		boolean lostEverything = false;
		boolean decidedToStop = false;
		
		while(!lostEverything && !decidedToStop) {
			//Get the array of rolled values.
			//Don't roll previously kept dice.
			//Rolled value 0 means not rolled.
			roll = getRoll(~diceToKeep);
			
			//Get a series of bits indicating which dice should be kept from this roll.
			//This is the intelligent step, taking into account risk factors etc.
			diceToKeep = decideWhatToKeep(roll, 0, diceToKeep);
			
			//Calculate the number of points accumulated on this roll.
			points += PointCalculator.pointsFrom(diceToKeep, roll);
			
		}
		
		if (!lostEverything)
			my.score += points;
		
		while (diceToKeep != ALL_DICE){
			roll = getRoll(~diceToKeep);
			diceToKeep = decideWhatToKeep(roll, points, diceToKeep);
		}
	}
	
	private void observeTurn(){
		
	}
	
	private byte decideWhatToKeep(byte[] roll, int points, byte alreadyKept){
		//Loop through all possible keeps except null keep.
		int kept = alreadyKept;
		byte i = 1;
		while (kept < ALL_DICE) {
			kept = alreadyKept | i;
		}
		return (byte)kept;
	}
	
	private byte[] getRoll(int dice){
		byte[] dieVals = new byte[6];
		for (byte i = 0; i < 6; i++)
			if ((dice>>>i)%2 == 1)
				dieVals[i] = gui.requestDieInput(i);
		return dieVals;
	}
	
	private byte die(byte index){
		return (byte)(1<<index);
	}
}