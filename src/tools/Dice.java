package tools;

import java.util.Random;

public class Dice {
	
	private int FACES, AMOUNT;
	
	public Dice(int amount, int faces) { 
		FACES = faces;
		AMOUNT = amount;
	}
	
	public int getFaces() { return FACES; }
	
	public int getAmount() { return AMOUNT; }
	
	/*
	 * Returns a string in the standard XdX format. So two 6 sided dice will be returned as, 2d6.
	 */
	public String getCanonicalName() { return Integer.toString(AMOUNT) + "d" + Integer.toString(FACES); }
	
	/*
	 * Rolls dice according to the amount of dice and faces.
	 * 2d6 will roll one 6 sided die, 2 times.
	 * Returns total score of rolls.
	 */
	public int rollDice() { 
		Random r = new Random();
		int total = 0;
		for(int i = 0; i < AMOUNT; i++) { 
			total += r.nextInt(FACES) + 1;
		}
		return total;
	}
}
