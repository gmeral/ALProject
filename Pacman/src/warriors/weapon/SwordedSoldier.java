package warriors.weapon;

import warriors.soldier.Soldier;



class SwordedSoldier extends DecoratedSoldier {

	public static final int nbMaxShield = 1;
	public static final int swordHealth = 5;
	public static final int laserSwordHealth = 5;
	
	public SwordedSoldier(Soldier s, Sword sword) throws TooManyItemsException {
		super(s, sword, swordHealth);
		Soldier iterator = s;
		while (iterator instanceof DecoratedSoldier){
			if(iterator instanceof SwordedSoldier){
				throw new TooManyItemsException("only one shield allowed");
			}
			iterator = ((DecoratedSoldier)iterator).soldier;
		}
	}

	public SwordedSoldier(Soldier s, LaserSword laserSword) throws TooManyItemsException {
		super(s, laserSword, laserSwordHealth);
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


}
