package warriors.proxy;

import java.lang.reflect.InvocationTargetException;

import warriors.army.AbstractObservableUnit;
import warriors.soldier.DeadSoldierException;
import warriors.soldier.Soldier;
import warriors.visitor.WarriorsVisitor;
import warriors.weapon.ArmedSoldier;
import warriors.weapon.BrokenItemException;
import warriors.weapon.TooManyItemsException;
import warriors.weapon.Weapon;



public class SoldierProxy extends AbstractObservableUnit {

	private Soldier soldier;

	public SoldierProxy(Soldier s){
		soldier = s;
	}
	
	@Override
	public int parry(int damages) throws DeadSoldierException, BrokenItemException{
		int damageReduced = 0;
		try {
			damageReduced = soldier.parry(damages);
		} catch (BrokenItemException e) {
			notifyParry(damageReduced);
			throw(e);
		} catch(DeadSoldierException e){
			notifyParry(damageReduced);
			notifyDeath();
			throw e;
		}
		notifyParry(damageReduced);
		return damageReduced;
	}

	@Override
	public int strike() {
		int dmg;
		try {
			dmg = soldier.strike();
		} catch (BrokenItemException e) {
			soldier = e.nextItem();
			dmg = strike();
		}
		//notifyStrike(dmg);
		return dmg;
	}



	public String toString(){
		return soldier.toString();
	}

	@Override
	public void addWeapon(Weapon w) throws TooManyItemsException {
		soldier = w.getDeco(soldier);
	}

	@Override
	public void remove(Weapon item) {
		Soldier father = null;
		Soldier it = soldier;
		while((it instanceof ArmedSoldier) && (((ArmedSoldier) it).getWeapon() != item)){
			father = it;
			it = ((ArmedSoldier)it).getSoldier();
		}

		if(it instanceof ArmedSoldier){
			if(father == null)
				this.soldier = ((ArmedSoldier)it).getSoldier();
			else
				((ArmedSoldier)father).setSoldier(((ArmedSoldier)it).getSoldier());

		}
	}


	@Override
	public void accept(WarriorsVisitor visitor) {
		soldier.accept(visitor);
	}



	@Override
	public String getName() {
		return soldier.getName();
	}


	@Deprecated
	public ArmedSoldier upgrade(Class<? extends ArmedSoldier> c) throws TooManyItemsException{

		Object params[] = {soldier};
		ArmedSoldier newSoldier;
		try {
			newSoldier = c.getDeclaredConstructor(Soldier.class).newInstance(params);
			soldier = newSoldier;
			return newSoldier;
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}


		return null;
	}

	@Deprecated
	public void remove(Class<? extends ArmedSoldier> c){
		Soldier iterator = soldier;
		Soldier father = null;
		try {
			while (!(iterator.getClass().equals(c)) && !(Class.forName("soldier.AbstractSoldier").isAssignableFrom(iterator.getClass()))){
				father = iterator;
				iterator = ((ArmedSoldier) soldier).getSoldier();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (iterator.getClass().equals(c)){
			if (father == null)
				soldier = ((ArmedSoldier)iterator).getSoldier();
			else
				((ArmedSoldier)father).setSoldier(((ArmedSoldier)iterator).getSoldier());
		}
	}

	@Override
	public int getSightRange() {
		return soldier.getSightRange();
	}

}
