package warriors.weapon;

import warriors.soldier.DeadSoldierException;
import warriors.soldier.Soldier;



class ShieldedSoldier extends DecoratedSoldier {


	public static final int shieldHealth  = 5;
	public static final int magneticShieldHealth  = 5;
	
	public ShieldedSoldier(Soldier s, Shield shield) throws TooManyItemsException {
		super(s, shield, shieldHealth);
	}

	public ShieldedSoldier(Soldier s, MagneticShield magneticShield) throws TooManyItemsException {
		super(s, magneticShield, shieldHealth);
	}

	public int parry(int damages) throws BrokenItemException, DeadSoldierException{
		weaponHealth--;
		return super.parry(damages);
	}

		public String toString(){
			return ("je porte un bouclier ") + super.toString();
		}

}
