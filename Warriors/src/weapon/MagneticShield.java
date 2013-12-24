package weapon;

import soldier.Soldier;
import visitor.WarriorsVisitor;

public class MagneticShield extends AbstractWeapon {

	public static final int MagneticShieldStrengthBonus = 0;
	public static final int MagneticShieldDamageReduction  = 10;
	
	
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
