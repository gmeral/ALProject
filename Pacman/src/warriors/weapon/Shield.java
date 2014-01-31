package warriors.weapon;

import warriors.soldier.Soldier;
import warriors.visitor.WarriorsVisitor;

public class Shield extends AbstractWeapon {

	public static final int shieldStrengthBonus = 0;
	public static final int shieldSightRangeBonus = 0;
	public static final int shieldDamageReduction  = 1;
	
	
	public Shield(){
		super(shieldStrengthBonus,shieldDamageReduction, shieldSightRangeBonus);
	}
	

	public ArmedSoldier getDeco(Soldier s) throws TooManyItemsException {
		return new ShieldedSoldier(s, this);
	}


	@Override
	public void accept(WarriorsVisitor visitor) {
		visitor.visit(this);
	}

}
