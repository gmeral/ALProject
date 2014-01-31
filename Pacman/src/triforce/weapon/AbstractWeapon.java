package triforce.weapon;

import triforce.soldier.Soldier;


public abstract class AbstractWeapon implements Weapon {

	protected int strengthBonus;
	protected int damageReduction;
	protected int sightRangeBonus;
	protected int maxHealthBonus;
	protected int currentHealthBonus;
	
	public AbstractWeapon(int strength, int armor, int sightRange, int maxHealth) {
		strengthBonus = strength;
		damageReduction = armor;
		sightRangeBonus = sightRange;
		maxHealthBonus = maxHealth;
		currentHealthBonus = maxHealth;
	}


	public int getStrenghtBonus() {
		return strengthBonus;
	}

	public int getSightRangeBonus() {
		return sightRangeBonus;
	}

	public int getDamageReduction() {
		return damageReduction;
	}
	
	public int getMaxHealthBonus(){
		return maxHealthBonus;
	}
	public int getCurrentHealthBonus(){
		return currentHealthBonus;
	}

	public abstract ArmedSoldier getDeco(Soldier s) throws TooManyItemsException;

}
