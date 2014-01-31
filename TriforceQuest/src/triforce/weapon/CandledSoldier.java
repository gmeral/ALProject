package triforce.weapon;

import triforce.soldier.Soldier;

public class CandledSoldier extends DecoratedSoldier{


	
	public CandledSoldier(Soldier s, Weapon w) throws TooManyItemsException {
		super(s, w);
		Soldier iterator = s;
		while (iterator instanceof DecoratedSoldier){
			if(iterator instanceof CandledSoldier){
				throw new TooManyItemsException("only one candle allowed");
			}
			iterator = ((DecoratedSoldier)iterator).soldier;
		}
	}

	@Override
	public int getNbRemainingStrike() {
		return super.getNbRemainingStrike();
	}

}
