package triforce.weapon;

import triforce.soldier.Soldier;
import triforce.visitor.WarriorsVisitor;

public class Sword extends AbstractWeapon{


	public static final int swordHealth = 5;
	public static final int swordStrengthBonus = 1;
	public static final int swordDamageReduction  = 0;
	public static final int swordSightRangeBonus  = 0;
	public static final int swordHealthBonus  = 0;
	
	
	public Sword() {
		super(swordStrengthBonus, 
				swordDamageReduction,
				swordSightRangeBonus,
				swordHealthBonus);	
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
		return swordHealth;
	}

}
