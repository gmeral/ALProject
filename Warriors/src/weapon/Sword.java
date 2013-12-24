package weapon;

import soldier.Soldier;
import visitor.WarriorsVisitor;

public class Sword extends AbstractWeapon{

	public static final int SwordStrengthBonus = 10;
	public static final int SwordDamageReduction  = 0;
	
	
	public Sword() {
		super(SwordStrengthBonus, SwordDamageReduction);
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
