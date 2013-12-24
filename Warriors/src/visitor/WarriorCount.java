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

public class WarriorCount implements WarriorsVisitor {

	private int nbLightUnit = 0;
	private int nbMountedUnit = 0;
	private int nbSword = 0;
	private int nbShield = 0;
	
	
	@Override
	public void visit(InfantryMan s) {
		++nbLightUnit;

	}

	@Override
	public void visit(HorseMan s) {
		++nbMountedUnit;
	}

	public int getNbSword() {
		return nbSword;
	}

	public int getNbShield() {
		return nbShield;
	}

	public int getNbHorseMan() {
		return nbMountedUnit;
	}

	public void reinitCount() {
		nbLightUnit = 0;
		nbMountedUnit = 0;
	}

	public int getNbInfantryMan() {
		return nbLightUnit;
	}

	@Override
	public void visit(Sword s) {
		++nbSword;
	}

	@Override
	public void visit(Shield s) {
		++nbShield;
	}

	@Override
	public void visit(Squad s) {
		Iterator<Unit> it = null;
		it = s.GetChildren();
		while (it.hasNext()){
			Unit unit = it.next();			
			unit.accept(this);
		}
	}

	@Override
	public void visit(LaserSword laserSword) {
		++nbSword;		
	}

	@Override
	public void visit(MagneticShield magneticShield) {
		++nbShield;		
	}

	@Override
	public void visit(HovercraftMan hovercraftMan) {
		++nbMountedUnit;		
	}

	@Override
	public void visit(Jedi jedi) {
		++nbLightUnit;
	}

}
