package soldier;

import visitor.WarriorsVisitor;
import weapon.BrokenItemException;

public interface Soldier {
	
	int parry(int damages) throws BrokenItemException, DeadSoldierException;
	int strike() throws BrokenItemException;
	String toString();
	String getName();
	void accept(WarriorsVisitor visitor);
}
