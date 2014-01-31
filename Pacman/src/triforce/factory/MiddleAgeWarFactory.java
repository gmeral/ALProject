package triforce.factory;

import triforce.proxy.HorseMan;
import triforce.proxy.InfantryMan;
import triforce.proxy.SoldierProxy;
import triforce.weapon.Shield;
import triforce.weapon.Sword;
import triforce.weapon.Weapon;

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
