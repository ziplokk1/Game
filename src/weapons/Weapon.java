package weapons;

import tools.Dice;

public class Weapon {
	public Dice damage_die;
	public String type;
	public String name;
	public Modification mod;
	
	public Weapon(String NAME, Dice d) { 
		name = NAME;
		damage_die = d;
	}
	
	public void setDamage(Dice d) { 
		damage_die = d;
	}
	
	public void setType(String s) { 
		type = s;
	}
	
}
