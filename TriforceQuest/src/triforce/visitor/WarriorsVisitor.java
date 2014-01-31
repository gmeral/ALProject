package triforce.visitor;

import triforce.army.Squad;
import triforce.proxy.HorseMan;
import triforce.proxy.HovercraftMan;
import triforce.proxy.InfantryMan;
import triforce.proxy.Jedi;
import triforce.weapon.Candle;
import triforce.weapon.LaserSword;
import triforce.weapon.MagneticShield;
import triforce.weapon.Shield;
import triforce.weapon.Sword;

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
	public void visit(Candle candle);
	
}
