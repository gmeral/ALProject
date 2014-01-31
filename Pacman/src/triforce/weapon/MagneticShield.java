package triforce.weapon;

import triforce.soldier.Soldier;
import triforce.visitor.WarriorsVisitor;

public class MagneticShield extends AbstractWeapon {

	public static final int magneticShieldHealth  = 5;
	public static final int magneticShieldStrengthBonus = 0;
	public static final int magneticShieldSightRangeBonus = 0;
	public static final int magneticShieldDamageReduction  = 2;
	public static final int magneticShieldHealthBonus  = 0;


	public MagneticShield(){
		super(magneticShieldStrengthBonus,
				magneticShieldDamageReduction, 
				magneticShieldSightRangeBonus, 
				magneticShieldHealthBonus);
	}


	public ArmedSoldier getDeco(Soldier s) throws TooManyItemsException {
		return new ShieldedSoldier(s, this);
	}


	@Override
	public void accept(WarriorsVisitor visitor) {
		visitor.visit(this);
	}


	@Override
	public int getHealth() {
		return magneticShieldHealth;
	}

}
