package warriors.weapon;

import warriors.soldier.Soldier;
import warriors.visitor.WarriorsVisitor;

public class MagneticShield extends AbstractWeapon {

	public static final int MagneticShieldStrengthBonus = 0;
	public static final int MagneticShieldDamageReduction  = 2;
	
	
	public MagneticShield(){
		super(MagneticShieldStrengthBonus,MagneticShieldDamageReduction);
	}
	

	public ArmedSoldier getDeco(Soldier s) throws TooManyItemsException {
		return new ShieldedSoldier(s, this);
	}


	@Override
	public void accept(WarriorsVisitor visitor) {
		visitor.visit(this);
	}

}
