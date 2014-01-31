package triforce.weapon;

import triforce.soldier.Soldier;

public interface ArmedSoldier extends Soldier{
	
	public Soldier getSoldier();
	public Weapon getWeapon();
	public void setSoldier(Soldier s);
	public int itemHealth();
	public int getNbRemainingStrike();
}
