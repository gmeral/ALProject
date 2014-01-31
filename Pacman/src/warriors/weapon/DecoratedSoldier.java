package warriors.weapon;

import warriors.soldier.DeadSoldierException;
import warriors.soldier.Soldier;
import warriors.visitor.WarriorsVisitor;

abstract class DecoratedSoldier implements Soldier, ArmedSoldier{

	public static final int nbMaxItem = 3;
	protected Soldier soldier;
	protected Weapon item;
	protected int weaponHealth;

	protected DecoratedSoldier(Soldier s, Weapon w) throws TooManyItemsException {
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
			weaponHealth = w.getHealth();
		}
	}

	
	public Weapon getWeapon(){
		return item;
	}
	
	public Soldier getSoldier() {
		return soldier;
	}


	public void setSoldier(Soldier soldier) {
		this.soldier = soldier;
	}
	
	public int getSightRange() {
		return item.getSightRangeBonus() + soldier.getSightRange();
	}
	
	@Override
	public int getNbRemainingStrike() {
		return soldier.getNbRemainingStrike();
	}

	public int parry(int damages) throws BrokenItemException, DeadSoldierException {
		int damageReduction;
		int reducedDamages = damages - item.getDamageReduction();
		if (weaponHealth == 0){
			throw new BrokenItemException("Broken Item",soldier, item.getDamageReduction());
		}
		else{
			try{
				damageReduction = soldier.parry((reducedDamages > 0 ? reducedDamages : 0));
			}catch(BrokenItemException e){
				soldier = e.nextItem();
				damageReduction = parry((reducedDamages > 0 ? reducedDamages : 0)+e.getStrength());
			}
		}
		return damageReduction + reducedDamages;
	}

	public int strike() throws BrokenItemException{
		
		if (weaponHealth == 0){
			throw new BrokenItemException("Broken Item",soldier, item.getStrenghtBonus());
		}
		else{
			try {
				int damages = soldier.strike();
				return  damages + item.getStrenghtBonus();
			} catch (BrokenItemException e) {
				soldier = e.nextItem();
				return strike() + e.getStrength();
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
	
	public int itemHealth(){
		return weaponHealth;
	}
	
	public int getMaxHealth(){
		return item.getMaxHealthBonus() + soldier.getMaxHealth();
	}
	
	public int getCurrentHealth(){
		return item.getCurrentHealthBonus() + soldier.getCurrentHealth();
	};
}