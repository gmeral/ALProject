package triforce.factory;

import triforce.proxy.HovercraftMan;
import triforce.proxy.Jedi;
import triforce.proxy.SoldierProxy;
import triforce.weapon.LaserSword;
import triforce.weapon.MagneticShield;
import triforce.weapon.Weapon;

public class SFWarFactory extends AbstractWarFactory {

	@Override
	public SoldierProxy createLightUnit() {
		return new Jedi();
	}

	@Override
	public SoldierProxy createMountedUnit() {
		return new HovercraftMan();
	}

	@Override
	public Weapon createDefenseItem() {
		return new MagneticShield();
	}

	@Override
	public Weapon createOffenseItem() {
		return new LaserSword();
	}

}
