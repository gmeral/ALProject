package weapon;

import soldier.Soldier;
import visitor.WarriorsVisitor;

public class Shield extends AbstractWeapon {

	public static final int ShieldStrengthBonus = 0;
	public static final int ShieldDamageReduction  = 10;
	
	
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
