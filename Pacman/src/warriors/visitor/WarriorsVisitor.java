package warriors.visitor;

import warriors.army.Squad;
import warriors.proxy.HorseMan;
import warriors.proxy.HovercraftMan;
import warriors.proxy.InfantryMan;
import warriors.proxy.Jedi;
import warriors.weapon.Candle;
import warriors.weapon.LaserSword;
import warriors.weapon.MagneticShield;
import warriors.weapon.Shield;
import warriors.weapon.Sword;

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
