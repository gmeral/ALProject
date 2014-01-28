package warriors.weapon;

import warriors.soldier.Soldier;


public abstract class AbstractWeapon implements Weapon {

	protected int strengthBonus;
	protected int damageReduction;
	
	public AbstractWeapon(int strength, int armor) {
		strengthBonus = strength;
		damageReduction = armor;
	}


	public int getStrenghtBonus() {
		return strengthBonus;
	}


	public int getDamageReduction() {
		return damageReduction;
	}

	public abstract ArmedSoldier getDeco(Soldier s) throws TooManyItemsException;

}
