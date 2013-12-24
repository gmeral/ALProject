package weapon;

import soldier.Soldier;

public interface ArmedSoldier extends Soldier{
	
	public Soldier getSoldier();
	public Weapon getWeapon();
	public void setSoldier(Soldier s);
}
