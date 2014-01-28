package warriors.weapon;

import warriors.soldier.DeadSoldierException;
import warriors.soldier.Soldier;
import warriors.visitor.WarriorsVisitor;

abstract class DecoratedSoldier implements Soldier, ArmedSoldier{

	public static final int nbMaxItem = 2;
	protected Soldier soldier;
	public Soldier getSoldier() {
		return soldier;
	}


	public void setSoldier(Soldier soldier) {
		this.soldier = soldier;
	}


	protected Weapon item;
	protected int weaponHealth;

	protected DecoratedSoldier(Soldier s, Weapon w, int health) throws TooManyItemsException {
		int nbItems = 0;
		Soldier iterator = s;
		while (iterator instanceof DecoratedSoldier){
			nbItems++;
			iterator = ((DecoratedSoldier)iterator).soldier;
		}
		if (nbItems >= nbMaxItem)
			throw( new TooManyItemsException("Too many Items"));
		else{
			soldier = s;
			item = w;
			weaponHealth = health;
		}
	}


	public Weapon getWeapon(){
		return item;
	}


	public int parry(int damages) throws BrokenItemException, DeadSoldierException {
		int damageReduction;
		int reducedDamages = damages - item.getDamageReduction();
		if (weaponHealth == 0){
			throw new BrokenItemException("Broken Item",soldier);
		}
		else{
			try{
				damageReduction = soldier.parry((reducedDamages > 0 ? reducedDamages : 0));
			}catch(BrokenItemException e){
				soldier = e.nextItem();
				damageReduction = parry((reducedDamages > 0 ? reducedDamages : 0));
			}
		}
		return damageReduction + reducedDamages;
	}

	public int strike() throws BrokenItemException{
		if (weaponHealth == 0){
			throw new BrokenItemException("Broken Item",soldier);
		}
		else{
			try {
				int damages = soldier.strike();
				return  damages + item.getStrenghtBonus();
			} catch (BrokenItemException e) {
				soldier = e.nextItem();
				return strike();
			}
		}
	}

	public String toString(){
		return "et " + soldier.toString();
	}


	@Override
	public void accept(WarriorsVisitor visitor) {
		item.accept(visitor);
		soldier.accept(visitor);
	}

	public String getName() {
		return soldier.getName();
	}

}