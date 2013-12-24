package factory;

import proxy.Jedi;
import proxy.HovercraftMan;
import proxy.SoldierProxy;
import weapon.LaserSword;
import weapon.MagneticShield;
import weapon.Weapon;

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
