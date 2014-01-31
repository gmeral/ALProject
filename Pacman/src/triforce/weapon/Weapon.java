package triforce.weapon;

import triforce.soldier.Soldier;
import triforce.visitor.WarriorsVisitor;

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
