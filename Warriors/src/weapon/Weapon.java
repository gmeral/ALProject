package weapon;

import soldier.Soldier;
import visitor.WarriorsVisitor;

public interface Weapon {
	
	public int getStrenghtBonus();
	public int getDamageReduction();
	
	public ArmedSoldier getDeco(Soldier s) throws TooManyItemsException;
	public void accept(WarriorsVisitor visitor);
}
