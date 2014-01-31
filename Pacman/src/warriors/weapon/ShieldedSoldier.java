package warriors.weapon;

import warriors.soldier.DeadSoldierException;
import warriors.soldier.Soldier;



class ShieldedSoldier extends DecoratedSoldier {


	public static final int shieldHealth  = 5;
	public static final int magneticShieldHealth  = 5;

	public ShieldedSoldier(Soldier s, Shield shield) throws TooManyItemsException {
		super(s, shield, shieldHealth);
		Soldier iterator = s;
		while (iterator instanceof DecoratedSoldier){
			if(iterator instanceof ShieldedSoldier){
				throw new TooManyItemsException("only one shield allowed");
			}
			iterator = ((DecoratedSoldier)iterator).soldier;
		}
	}

	public ShieldedSoldier(Soldier s, MagneticShield magneticShield) throws TooManyItemsException {
		super(s, magneticShield, shieldHealth);
		Soldier iterator = s;
		while (iterator instanceof DecoratedSoldier){
			if(iterator instanceof ShieldedSoldier){
				throw new TooManyItemsException("only one shield allowed");
			}
			iterator = ((DecoratedSoldier)iterator).soldier;
		}
	}

	public int parry(int damages) throws BrokenItemException, DeadSoldierException{
		weaponHealth--;
		return super.parry(damages);
	}

	public String toString(){
		return ("je porte un bouclier ") + super.toString();
	}

}
