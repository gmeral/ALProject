package warriors.weapon;

import warriors.soldier.Soldier;
import warriors.visitor.WarriorsVisitor;

public class MagneticShield extends AbstractWeapon {

	public static final int magneticShieldStrengthBonus = 0;
	public static final int magneticShieldSightRangeBonus = 0;
	public static final int magneticShieldDamageReduction  = 2;
	
	
	public MagneticShield(){
		super(magneticShieldStrengthBonus,magneticShieldDamageReduction, magneticShieldSightRangeBonus);
	}
	

	public ArmedSoldier getDeco(Soldier s) throws TooManyItemsException {
		return new ShieldedSoldier(s, this);
	}


	@Override
	public void accept(WarriorsVisitor visitor) {
		visitor.visit(this);
	}

}
