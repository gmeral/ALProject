package warriors.weapon;

import warriors.soldier.Soldier;
import warriors.visitor.WarriorsVisitor;

public class Sword extends AbstractWeapon{

	public static final int swordStrengthBonus = 1;
	public static final int swordDamageReduction  = 0;
	public static final int swordSightRangeBonus  = 0;
	
	
	public Sword() {
		super(swordStrengthBonus, swordDamageReduction, swordSightRangeBonus);
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
