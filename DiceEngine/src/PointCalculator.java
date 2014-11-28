
public abstract class PointCalculator {
	
	public static int pointsFrom(byte[] valCounts){
		int ofAKindPoints = 0;
		int onesFivesPts = 0;
		byte pairsFound = 0;
		
		for (int val = 0; val < 7; val++)
		{
			//3 pairs
			if (valCounts[val] == 2) pairsFound++;
			if (pairsFound == 3) return 750;
			
			//3+ of a kind
			if (valCounts[val] > 2)
				ofAKindPoints += 100*val*(1<<(valCounts[val]-3));
			//1s and 5s
			if (val == 1)
				onesFivesPts += 100*valCounts[val];
			else if (val == 5)
				onesFivesPts += 50*valCounts[val];
		}
		
		int points = Math.max(ofAKindPoints, onesFivesPts);

		//If we rolled all the dice but got no points, return 750 points.
		if (points == 0 && valCounts[0] == 0)
			points = 750;
		
		return points;
	}

	public static int pointsFrom(byte diceToKeep, byte[] dieVals) {
		byte[] counts = new byte[7];
		for (byte die = 0; die < 6; die++) {
			counts[dieVals[die]]++;
		}
		return PointCalculator.pointsFrom(counts);
	}
	
}
