package weapons;

import tools.Dice;

public class Bow extends Weapon {
	
	public int RANGE;
	
	public Bow(String NAME, Dice d) {
		super(NAME, d);
		// TODO Auto-generated constructor stub
	}
	
	public void setRange(int i) { RANGE = i; }
}
