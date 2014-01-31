package warriors.weapon;

import warriors.soldier.Soldier;



class SwordedSoldier extends DecoratedSoldier {

	
	public SwordedSoldier(Soldier s, Sword sword) throws TooManyItemsException {
		super(s, sword);
		Soldier iterator = s;
		while (iterator instanceof DecoratedSoldier){
			if(iterator instanceof SwordedSoldier){
				throw new TooManyItemsException("only one shield allowed");
			}
			iterator = ((DecoratedSoldier)iterator).soldier;
		}
	}

	public SwordedSoldier(Soldier s, LaserSword laserSword) throws TooManyItemsException {
		super(s, laserSword);
		Soldier iterator = s;
		while (iterator instanceof DecoratedSoldier){
			if(iterator instanceof SwordedSoldier){
				throw new TooManyItemsException("only one shield allowed");
			}
			iterator = ((DecoratedSoldier)iterator).soldier;
		}
	}

	public int strike() throws BrokenItemException{		
		weaponHealth --;
		return super.strike();
	}
	
	public String toString(){
		return "je porte une épée " + super.toString();
		
	}

	@Override
	public int getNbRemainingStrike() {
		return weaponHealth + super.getNbRemainingStrike();
	}


}
