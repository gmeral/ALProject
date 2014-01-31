package triforce.visitor;

import java.util.Iterator;

import triforce.army.Squad;
import triforce.army.Unit;
import triforce.proxy.HorseMan;
import triforce.proxy.HovercraftMan;
import triforce.proxy.InfantryMan;
import triforce.proxy.Jedi;
import triforce.weapon.Candle;
import triforce.weapon.LaserSword;
import triforce.weapon.MagneticShield;
import triforce.weapon.Shield;
import triforce.weapon.Sword;

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

	@Override
	public void visit(Candle candle) {
		// TODO Auto-generated method stub
		
	}

}
