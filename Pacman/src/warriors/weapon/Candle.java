package warriors.weapon;

import warriors.soldier.Soldier;
import warriors.visitor.WarriorsVisitor;

public class Candle extends AbstractWeapon {
	
	public static final int candleHealth  = 1;
	public static final int candleStrengthBonus = 0;
	public static final int candleDamageReduction  = 0;
	public static final int candleSightRangeBonus  = 2;
	public static final int candleHealthBonus  = 0;
	
	public Candle(){
		super(candleStrengthBonus,
				candleDamageReduction,
				candleSightRangeBonus,
				candleHealthBonus);
	}
	

	public ArmedSoldier getDeco(Soldier s) throws TooManyItemsException {
		return new CandledSoldier(s, this);
	}

	@Override
	public void accept(WarriorsVisitor visitor) {
		visitor.visit(this);
	}


	@Override
	public int getHealth() {
		return candleHealth;
	}

}