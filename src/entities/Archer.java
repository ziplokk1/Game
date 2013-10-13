package entities;

import java.util.Random;

import weapons.Bow;
import weapons.Weapon;

public class Archer extends EntityBase {
	/*
	 * Notes:
	 * Sacrifice accuracy for speed and vice versa.
	 * Strength increases bow range
	 * Dexterity increases bow damage and accuracy
	 */
	
	public Bow weapon;
	public int ACCURACY = 0;
	
	public Archer(String name, int hitDieFaces, int level) { 
		super(name, hitDieFaces, level);
	}
	
	public Archer(String name, int hitDieFaces) {
		super(name, hitDieFaces);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * For every two points in the paramater, accuracy is increased by 1.
	 * For each point in accuracy, this entity has a 5% greater chance to hit it's target.
	 * For each point in the paramater, it subtracts that amount from speed.
	 */
	public void setAccuracy(int a) { 
		int checkSpeed = this.SPEED - a;
		if(checkSpeed > 0) {
			this.SPEED -= a;
			ACCURACY = a / 2;
		} else { 
			try {
				throw new Exception("\'" + this.NAME + "\' doesn't have enough speed to trade for accuracy.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Checks to see if this archer can hit the opposing entity based on its dex mod
	 * and accuracy. In theory, the rand rolls a d20 and if the roll is less than 
	 * this entity's accuracy and dexterity combined, then the arrow has hit.
	 */
	public boolean canHitEntity() { 
		Random r = new Random();
		int totalChance = this.DEX_MOD + ACCURACY;
		int randRoll = r.nextInt(20) + 1;
		return randRoll <= totalChance;
	}
	
	/*
	 * (non-Javadoc)
	 * @see entities.EntityBase#setWeapon(weapons.Weapon)
	 */
	@Override
	public void setWeapon(Weapon w) {
		if(w instanceof Bow) { 
			weapon = (Bow) w;
		} else { 
			try {
				throw new Exception("Weapon must be of type bow");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
	
	/*
	 * Checks to see if opposing entity is in range of this entity's bow.
	 */
	private boolean isInRange(EntityBase eb) { 
		if(getDistance(eb) <= (double) weapon.RANGE + this.STR) { 
			return true;
		}
		return false;
	}
	
	@Override
	public void attackEntity(final EntityBase eb) { //Attack entity with dexterity. Throws error if Dexterity is invalid
		if(DEX == -1) { 
			try {
				throw new Exception("\'" + NAME + "\' has no Dex.");
			} catch(Exception e) { 
				e.printStackTrace();
				System.exit(0);
			}
		}
		int damage = weapon.damage_die.rollDice() + DEX_MOD;
		if(isInRange(eb)) { 
			if(canHitEntity()) {
				eb.damageEntity(damage);
				System.out.println("\'" + NAME + "\' attacks \'" + eb.NAME + 
						"\' with \'" + weapon.name + "\' for " + Integer.toString(damage) + 
						" points of damage.");
			} else { 
				System.out.println("\'" + NAME + "\' missed attack against \'" + eb.NAME + "\'. ACCURACY TOO LOW.");
			}
		} else { 
			System.out.println("\'" + NAME + "\' missed attack against \'" + eb.NAME + "\'. OUT OF RANGE.");
		}
	}
}
