package main;

import tools.Dice;
import weapons.Bow;
import weapons.Weapon;
import entities.Archer;
import entities.EntityBase;

public class Main {
	public static void main(String[] args) { 
		Bow bow = new Bow("Bow", new Dice(2, 6));
		bow.setRange(30);
		
		final Archer archer1 = new Archer("Archer 1", 10, 1);
		archer1.setStr(10);
		archer1.setDex(13);
		archer1.setAccuracy(6);
		archer1.generateStartingHealth();
		archer1.setWeapon(bow);
		archer1.setCoords(0, 0);
		
		final Archer archer2 = new Archer("Archer 2", 10, 1);
		archer2.setStr(10);
		archer2.setDex(13);
		archer2.setAccuracy(8);
		archer2.generateStartingHealth();
		archer2.setWeapon(bow);
		
		
		final EntityBase eb1 = new EntityBase("Entity 1", 12, 1);
		eb1.generateStartingHealth();
		eb1.setMaxHealth(1000);
		System.out.println(eb1.CURRENT_HEALTH);
		
		while(eb1.isAlive()) { 
			archer1.attackEntity(eb1);
			archer2.attackEntity(eb1);
			System.out.println(eb1.CURRENT_HEALTH);
		}	
	}
}
