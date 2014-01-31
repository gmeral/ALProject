package warriors.weapon;

import warriors.soldier.Soldier;

public class CandledSoldier extends DecoratedSoldier{

	public static final int candleHealth  = 5;
	
	public CandledSoldier(Soldier s, Weapon w) throws TooManyItemsException {
		super(s, w, candleHealth);
		Soldier iterator = s;
		while (iterator instanceof DecoratedSoldier){
			if(iterator instanceof CandledSoldier){
				throw new TooManyItemsException("only one candle allowed");
			}
			iterator = ((DecoratedSoldier)iterator).soldier;
		}
	}

}
