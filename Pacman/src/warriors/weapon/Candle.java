package warriors.weapon;

import warriors.soldier.Soldier;
import warriors.visitor.WarriorsVisitor;

public class Candle extends AbstractWeapon {

	public static final int CandleStrengthBonus = 0;
	public static final int CandleDamageReduction  = 0;
	public static final int CandleSightRangeBonus  = 2;
	
	public Candle(){
		super(CandleStrengthBonus,CandleDamageReduction, CandleSightRangeBonus);
	}
	

	public ArmedSoldier getDeco(Soldier s) throws TooManyItemsException {
		return new CandledSoldier(s, this);
	}


	@Override
	public void accept(WarriorsVisitor visitor) {
		visitor.visit(this);
	}

}