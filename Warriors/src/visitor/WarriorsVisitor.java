package visitor;

import proxy.HorseMan;
import proxy.HovercraftMan;
import proxy.InfantryMan;
import proxy.Jedi;
import weapon.LaserSword;
import weapon.MagneticShield;
import weapon.Shield;
import weapon.Sword;
import army.Squad;

public interface WarriorsVisitor {
	
	public void visit(InfantryMan s);
	public void visit(HorseMan s);
	public void visit(Sword s);
	public void visit(Shield s);
	public void visit(Squad s);
	public void visit(LaserSword laserSword);
	public void visit(MagneticShield magneticShield);
	public void visit(HovercraftMan hovercraftMan);
	public void visit(Jedi jedi);
	
}
