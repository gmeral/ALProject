package warriors.weapon;

import warriors.soldier.Soldier;
import warriors.visitor.WarriorsVisitor;

public class LaserSword extends AbstractWeapon {

	public static final int LaserSwordStrengthBonus = 2;
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
