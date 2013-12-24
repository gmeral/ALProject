package tests;


import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import proxy.HorseMan;
import proxy.InfantryMan;
import visitor.WarriorCount;
import visitor.WarriorDisplay;
import visitor.WarriorsVisitor;
import weapon.Shield;
import weapon.Sword;
import weapon.TooManyItemsException;
import weapon.Weapon;
import army.Squad;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestVisitor {

	private Squad alpha = new Squad();

	@Before
	public void initSquad() {
		InfantryMan m = new InfantryMan();

		Weapon w = new Sword();
		try {
			m.addWeapon(w);
		} catch (TooManyItemsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		InfantryMan m2 = new InfantryMan();
		Weapon w2 = new Sword();
		try {
			m2.addWeapon(w2);
		} catch (TooManyItemsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Squad bravo = new Squad();
		bravo.addUnit(m2);

		HorseMan h = new HorseMan();
		Weapon s = new Shield();
		try {
			h.addWeapon(s);
		} catch (TooManyItemsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		alpha.addUnit(m);
		alpha.addUnit(bravo);
		alpha.addUnit(h);

		Weapon sword2 = new Sword();
		try {
			alpha.addWeapon(sword2);
		} catch (TooManyItemsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Test
	public void ATest_SquadDisplay() {
		WarriorsVisitor v = new WarriorDisplay();
		alpha.accept(v);
		String result = ((WarriorDisplay)v).getResult();
		if (result.compareTo(alpha.toString()) != 0)
			Assert.fail("wrong display result");
	}

	@Test
	public void BTest_SquadCount() {
		WarriorsVisitor v = new WarriorCount();
		alpha.accept(v);
		int result = ((WarriorCount)v).getNbHorseMan();
		int result2 = ((WarriorCount)v).getNbInfantryMan();
		if (result != 1 || result2 != 2)
			Assert.fail("wrong count result");
	}

}
