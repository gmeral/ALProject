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

}
