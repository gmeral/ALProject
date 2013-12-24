package weapon;

import soldier.Soldier;
import visitor.WarriorsVisitor;

public class LaserSword extends AbstractWeapon {

	public static final int LaserSwordStrengthBonus = 10;
	public static final int LaserSwordDamageReduction  = 0;
	
	
	public LaserSword() {
		super(LaserSwordStrengthBonus, LaserSwordDamageReduction);
	}


	public ArmedSoldier getDeco(Soldier s) throws TooManyItemsException {
		return new SwordedSoldier(s, this);
	}


	public void parry() {
	}

	@Override
	public void accept(WarriorsVisitor visitor) {
		visitor.visit(this);
	}
}
