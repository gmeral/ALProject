package triforce.weapon;

import triforce.soldier.Soldier;
import triforce.visitor.WarriorsVisitor;

public class LaserSword extends AbstractWeapon {

	public static final int laserSwordStrengthBonus = 2;
	public static final int laserSwordDamageReduction  = 0;
	public static final int laserSwordsightRangeBonus  = 0;
	public static final int laserSwordHealth = 5;
	public static final int laserSwordHealthBonus  = 0;
	
	
	public LaserSword() {
		super(laserSwordStrengthBonus,
				laserSwordDamageReduction, 
				laserSwordsightRangeBonus, 
				laserSwordHealthBonus);
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
