package triforce.army;

import triforce.observers.Observer;
import triforce.soldier.DeadSoldierException;
import triforce.visitor.WarriorsVisitor;
import triforce.weapon.BrokenItemException;
import triforce.weapon.TooManyItemsException;
import triforce.weapon.Weapon;

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
