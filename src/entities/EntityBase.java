package entities;

import tools.Dice;
import weapons.Weapon;

public class EntityBase {
	public int CURRENT_HEALTH;
	public int MAX_HEALTH;
	public int[] coords = new int[2];
	
	public Weapon weapon;
	
	public int STR = -1; 
	public int STR_MOD = -4; //Default is -4 since if the parent score is 0, the modifier will be -4
	public int STR_BON = 0;
	
	public int DEX = -1;
	public int DEX_MOD = -4;
	public int DEX_BON = 0;
	
	public int CON = -1;
	public int CON_MOD = -4;
	public int CON_BON = 0;
	
	public int CHA = -1;
	public int CHA_MOD = -4;
	public int CHA_BON = 0;
	
	public int WIS = -1;
	public int WIS_MOD = -4;
	public int WIS_BON = 0;
	
	public int INTEL = -1;
	public int INTEL_MOD = -4;
	public int INTEL_BON = 0;
	
	public String NAME;
	public int SPEED = 30; //Default is 30. Change accordingly.
	public Dice HIT_DIE;
	public int DIE_FACES = -1;
	public int LEVEL = -1;
	
	public EntityBase(String name) { //Constructor with only name
		NAME = name;
	}
	
	public EntityBase(String name, int hitDieFaces, int level) { //Constructor with name, hit die, and level 
		DIE_FACES = hitDieFaces;
		NAME = name;
		LEVEL = level;
	}
	
	public EntityBase(String name, int hitDieFaces) { //Constructor with name and hit die
		DIE_FACES = hitDieFaces;
		NAME = name;
	}
	
	public void setCoords(int a, int b) { 
		coords[0] = a;
		coords[1] = b;
	}
	
	public void setStr(int i) { //set strength
		STR = i;
		STR_MOD = getModifier(STR);
	}
	
	public void setDex(int i) { //Set Dexterity 
		DEX = i;
		DEX_MOD = getModifier(DEX);
	}
	
	public void setCon(int i) { //Set Constitution
		CON = i;
		CON_MOD = getModifier(CON);
	}
	
	public void setCha(int i) { //Set Charisma
		CHA = i;
		CHA_MOD = getModifier(CHA);
	}
	
	public void setWis(int i) { //Set Wisdom
		WIS = i;
		WIS_MOD = getModifier(WIS);
	}
	
	public void setInt(int i) { //Set Intelligence
		INTEL = i;
		INTEL_MOD = getModifier(INTEL);
	}
	
	public int getModifier(int i) { //Returns modifier for any skill (Base 8)
		if(i >= 8) { 
			return (i - 8) / 2;
		} else { 
			return (i - 9) / 2;
		}
	}
	
	public boolean generateHitDie() { //Generates hit die. Returns false if hit die was not able to be generated.
		if(verifyDice()) { 
			HIT_DIE = new Dice(LEVEL, DIE_FACES);
			System.out.println("\'" + NAME + "\' HIT_DIE set to: " + HIT_DIE.getCanonicalName());
			return true;
		}
		return false;
	}
	
	/*
	 * Dependant on generateHitDie(). If true, create MAX_HEALTH and set CURRENT_HEALTH. Otherwise throws exception
	 */
	public void generateStartingHealth() { 
		if(generateHitDie()) { 
			MAX_HEALTH = HIT_DIE.rollDice();
			CURRENT_HEALTH = MAX_HEALTH;
			System.out.println("\'" + NAME + "\' Max Health set to: " + Integer.toString(MAX_HEALTH));
		} else { 
			try {
				throw new Exception("\'" + NAME + "\' Error generating Hit Die.");
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}
	
	public boolean verifyDice() { //Check to make sure all variables are valid when creating a hit die.
		if(DIE_FACES == -1) { 
			try {
				throw new Exception("\'" + NAME + "\' has no DIE_FACES.");
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		if(LEVEL == -1) { 
			try { 
				throw new Exception("\'" + NAME + "\' has no level.");
			} catch (Exception e) { 
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public EntityBase getEntity() { return this; }
	
	public int getStr() { return STR + STR_BON; }
	
	public int getDex() { return DEX + DEX_BON; }
	
	public int getCon() { return CON + CON_BON; }
	
	public int getCha() { return CHA + CHA_BON; }
	
	public int getWis() { return WIS + WIS_BON; }
	
	public int getInt() { return INTEL + INTEL_BON; }
	
	public void attackEntity(EntityBase eb) { //Attack entity with strength. Throws Exception if entity's str is invalid
		if(STR == -1) { 
			try {
				throw new Exception("\'" + NAME + "\' has no Str.");
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		int damage = weapon.damage_die.rollDice() + STR_MOD;
		eb.damageEntity(damage);
		System.out.println("\'" + NAME + "\' attacks \'" + eb.NAME + 
				"\' with \'" + weapon.name + "\' for " + 
				Integer.toString(damage) + " points of damage.");
	}
	
	public double getDistance(EntityBase eb) { 
		int triSideBottomLen = Math.max(eb.coords[0], this.coords[0]) - Math.min(eb.coords[0], this.coords[0]);
		int triSideSideLen = Math.max(eb.coords[1], this.coords[1]) - Math.min(eb.coords[1], this.coords[1]);
		int hypotenuse = (triSideBottomLen * triSideBottomLen) + (triSideSideLen * triSideSideLen);
		return Math.sqrt(hypotenuse);
	}
	
	public void setCurrentHealth(int i) { CURRENT_HEALTH = i; }
	
	public void setWeapon(Weapon w) { weapon = w; }
	
	public boolean isAlive() { return CURRENT_HEALTH > 0; }
	
	public void setStrBon(int i) { STR_BON = i; }
	
	public void setConBon(int i) { CON_BON = i; }
	
	public void setChaBon(int i) { CHA_BON = i; }
	
	public void setWisBon(int i) { WIS_BON = i; }
	
	public void setIntBon(int i) { INTEL_BON = i; }
	
	public void setDexBon(int i) { DEX_BON = i; }
	
	public void healEntity(int i) { CURRENT_HEALTH += i; }
	
	public void damageEntity(int i) { CURRENT_HEALTH -= i; }
	
	public void setLevel(int i) { LEVEL = i; }
	
	public void setHitDie(Dice d) { HIT_DIE = d; }
	
	public void setSpeed(int i) { SPEED = i; }
	
	public void setMaxHealth(int i) { MAX_HEALTH = i; }
}
