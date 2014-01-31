package triforce.soldier;

import triforce.visitor.WarriorsVisitor;
import triforce.weapon.BrokenItemException;

public interface Soldier {
	
	int parry(int damages) throws BrokenItemException, DeadSoldierException;
	int strike() throws BrokenItemException;
	String toString();
	String getName();
	void accept(WarriorsVisitor visitor);
	int getSightRange();
	int getMaxHealth();
	int getCurrentHealth();
	int getNbRemainingStrike();
}
