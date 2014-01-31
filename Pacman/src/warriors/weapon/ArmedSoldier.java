package warriors.weapon;

import warriors.soldier.Soldier;

public interface ArmedSoldier extends Soldier{
	
	public Soldier getSoldier();
	public Weapon getWeapon();
	public void setSoldier(Soldier s);
	public int itemHealth();
	public int getNbRemainingStrike();
}
