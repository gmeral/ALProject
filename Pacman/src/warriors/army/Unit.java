package warriors.army;

import warriors.observers.Observer;
import warriors.soldier.DeadSoldierException;
import warriors.visitor.WarriorsVisitor;
import warriors.weapon.BrokenItemException;
import warriors.weapon.TooManyItemsException;
import warriors.weapon.Weapon;

public interface Unit {
	//Soldier interface
	int parry(int damages) throws DeadSoldierException, BrokenItemException;
	int strike();
	int getSightRange();
	String toString();
	String getName();

	//Added from Soldier
	void addWeapon(Weapon w) throws TooManyItemsException;
	void remove(Weapon item);
	
	//visitor
	void accept(WarriorsVisitor visitor);
	
	//observer
	void attache(Observer o);
	void detache(Observer o);
}
