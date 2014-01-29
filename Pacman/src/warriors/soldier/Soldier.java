package warriors.soldier;

import warriors.visitor.WarriorsVisitor;
import warriors.weapon.BrokenItemException;

public interface Soldier {
	
	int parry(int damages) throws BrokenItemException, DeadSoldierException;
	int strike() throws BrokenItemException;
	String toString();
	String getName();
	void accept(WarriorsVisitor visitor);
	int getSightRange();
}
