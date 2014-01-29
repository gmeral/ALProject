package warriors.weapon;

import warriors.soldier.Soldier;
import warriors.visitor.WarriorsVisitor;

public interface Weapon {
	
	public int getStrenghtBonus();
	public int getDamageReduction();
	
	public ArmedSoldier getDeco(Soldier s) throws TooManyItemsException;
	public void accept(WarriorsVisitor visitor);
}