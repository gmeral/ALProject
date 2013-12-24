package visitor;

import java.util.Iterator;

import proxy.HorseMan;
import proxy.HovercraftMan;
import proxy.InfantryMan;
import proxy.Jedi;
import weapon.LaserSword;
import weapon.MagneticShield;
import weapon.Shield;
import weapon.Sword;
import army.Squad;
import army.Unit;

public class WarriorDisplay implements WarriorsVisitor {

	private int SquadDeep = 0;
	private StringBuffer result = new StringBuffer();


	public String getResult() {
		return result.toString();
	}

	public void reinitResult()  {
		result = new StringBuffer();
	}

	@Override
	public void visit(InfantryMan s) {
		result.append("je suis à pied\n");
	}

	@Override
	public void visit(HorseMan s) {
		result.append("je suis à cheval\n");
	}

	@Override
	public void visit(Sword s) {
		result.append("je porte une épée et ");
	}

	@Override
	public void visit(Shield s) {
		result.append("je porte un bouclier et ");
	}

	@Override
	public void visit(Squad s) {
		result.append("je suis une squad et voici qui me compose :\n");
		SquadDeep++;
		Iterator<Unit> it = null;
		it = s.GetChildren();
		while (it.hasNext()){
			Unit unit = it.next();			
			tabulate();
			unit.accept(this);
		}
		SquadDeep--;
	}

	private void tabulate(){
		for(int i=0 ; i<SquadDeep ; i++){
			result.append("    ");
		}
	}

	@Override
	public void visit(LaserSword laserSword) {
		result.append("je porte une épee laser et ");
	}

	@Override
	public void visit(MagneticShield magneticShield) {
		result.append("je porte un bouclier magnétique et ");		
	}

	@Override
	public void visit(HovercraftMan hovercraftMan) {
		result.append("je suis sur un hovercraft\n");	
	}

	@Override
	public void visit(Jedi jedi) {
		result.append("je suis un jedi\n");		
	}
}
