package warriors.factory;

import warriors.proxy.HovercraftMan;
import warriors.proxy.Jedi;
import warriors.proxy.SoldierProxy;
import warriors.weapon.LaserSword;
import warriors.weapon.MagneticShield;
import warriors.weapon.Weapon;

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
