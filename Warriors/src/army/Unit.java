package army;

import soldier.DeadSoldierException;
import visitor.WarriorsVisitor;
import weapon.TooManyItemsException;
import weapon.Weapon;
import Observers.Observer;

public interface Unit {
	//Soldier interface
	int parry(int damages) throws DeadSoldierException;
	int strike();
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
