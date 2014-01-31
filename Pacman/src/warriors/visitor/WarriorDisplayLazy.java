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

public class WarriorDisplayLazy implements WarriorsVisitor {

	@Override
	public void visit(InfantryMan s) {
		System.out.print("je suis à pied\n");
	}

	@Override
	public void visit(HorseMan s) {
		System.out.print("je suis à cheval\n");
	}

	@Override
	public void visit(Sword s) {
		System.out.print("je porte une épée et ");
	}

	@Override
	public void visit(Shield s) {
		System.out.print("je porte un bouclier et ");
	}


	@Override
	public void visit(Squad s) {
		System.out.println(s.toString());
	}

	@Override
	public void visit(LaserSword laserSword) {
		System.out.println("je porte une épee laser et ");		
	}

	@Override
	public void visit(MagneticShield magneticShield) {
		System.out.println("je porte un bouclier magnétique et ");		
	}

	@Override
	public void visit(HovercraftMan hovercraftMan) {
		System.out.println("je suis sur un hovercraft\n");
	}

	@Override
	public void visit(Jedi jedi) {
		System.out.println("je suis un jedi\n");
	}

	@Override
	public void visit(Candle candle) {
		// TODO Auto-generated method stub
		
	}

}
