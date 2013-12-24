package factory;

import proxy.SoldierProxy;
import weapon.Weapon;

public abstract class AbstractWarFactory {
	public abstract SoldierProxy createLightUnit();
	public abstract SoldierProxy createMountedUnit();
	public abstract Weapon createDefenseItem();
	public abstract Weapon createOffenseItem();

}
