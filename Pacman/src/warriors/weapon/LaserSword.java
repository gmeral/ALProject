package warriors.weapon;

import warriors.soldier.Soldier;
import warriors.visitor.WarriorsVisitor;

public class LaserSword extends AbstractWeapon {

	public static final int laserSwordStrengthBonus = 2;
	public static final int laserSwordDamageReduction  = 0;
	public static final int laserSwordsightRangeBonus  = 0;
	public static final int laserSwordHealth = 5;
	
	
	public LaserSword() {
		super(laserSwordStrengthBonus, laserSwordDamageReduction, laserSwordsightRangeBonus);
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


	@Override
	public int getHealth() {
		return laserSwordHealth;
	}
}
