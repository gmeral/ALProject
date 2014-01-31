package warriors.weapon;

import warriors.soldier.Soldier;
import warriors.visitor.WarriorsVisitor;

public interface Weapon {

	public int getStrenghtBonus();
	public int getSightRangeBonus();
	public int getDamageReduction();
	
	public ArmedSoldier getDeco(Soldier s) throws TooManyItemsException;
	public void accept(WarriorsVisitor visitor);
	public int getHealth();
	public int getMaxHealthBonus();
	public int getCurrentHealthBonus();
}
