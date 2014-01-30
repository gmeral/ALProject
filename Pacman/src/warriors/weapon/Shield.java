package warriors.weapon;

import warriors.soldier.Soldier;
import warriors.visitor.WarriorsVisitor;

public class Shield extends AbstractWeapon {

	public static final int ShieldStrengthBonus = 0;
	public static final int ShieldDamageReduction  = 1;
	
	
	public Shield(){
		super(ShieldStrengthBonus,ShieldDamageReduction);
	}
	

	public ArmedSoldier getDeco(Soldier s) throws TooManyItemsException {
		return new ShieldedSoldier(s, this);
	}


	@Override
	public void accept(WarriorsVisitor visitor) {
		visitor.visit(this);
	}

}
