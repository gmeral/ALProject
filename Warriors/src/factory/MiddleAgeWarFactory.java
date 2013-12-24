package factory;

import proxy.HorseMan;
import proxy.InfantryMan;
import proxy.SoldierProxy;
import weapon.Shield;
import weapon.Sword;
import weapon.Weapon;

public class MiddleAgeWarFactory extends AbstractWarFactory {

	@Override
	public SoldierProxy createLightUnit() {
		return new InfantryMan();
	}

	@Override
	public SoldierProxy createMountedUnit() {
		return new HorseMan();
	}

	@Override
	public Weapon createDefenseItem() {
		return new Shield();
	}

	@Override
	public Weapon createOffenseItem() {
		return new Sword();
	}

}
