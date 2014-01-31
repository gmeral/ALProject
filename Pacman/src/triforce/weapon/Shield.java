package triforce.weapon;

import triforce.soldier.Soldier;
import triforce.visitor.WarriorsVisitor;

public class Shield extends AbstractWeapon {


	public static final int shieldHealth  = 5;
	public static final int shieldStrengthBonus = 0;
	public static final int shieldSightRangeBonus = 0;
	public static final int shieldDamageReduction  = 1;
	public static final int shieldHealthBonus  = 0;
	
	
	public Shield(){
		super(shieldStrengthBonus,
				shieldDamageReduction,
				shieldSightRangeBonus,
				shieldHealthBonus);
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
		return shieldHealth;
	}

}
