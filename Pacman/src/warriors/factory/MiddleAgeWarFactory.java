package warriors.factory;

import warriors.proxy.HorseMan;
import warriors.proxy.InfantryMan;
import warriors.proxy.SoldierProxy;
import warriors.weapon.Shield;
import warriors.weapon.Sword;
import warriors.weapon.Weapon;

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
