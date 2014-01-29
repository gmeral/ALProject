package warriors.tests;


import org.junit.Assert;
import org.junit.Test;

import warriors.army.Squad;
import warriors.army.Unit;
import warriors.factory.AbstractWarFactory;
import warriors.factory.MiddleAgeWarFactory;
import warriors.factory.SFWarFactory;
import warriors.weapon.TooManyItemsException;
import warriors.weapon.Weapon;

public class TestAbstractFactory {
	@Test
	public void testA_UpgradeSquadMA() {
		AbstractWarFactory factoryMA = new MiddleAgeWarFactory();

		Unit soldierA = factoryMA.createLightUnit();
		Unit soldierB = factoryMA.createMountedUnit();
		Squad alpha = new Squad();
		alpha.addUnit(soldierA);
		alpha.addUnit(soldierB);
		Weapon sword = factoryMA.createOffenseItem();
		Weapon shield = factoryMA.createDefenseItem();
		try {
			alpha.addWeapon(shield);
			alpha.addWeapon(sword);
		} catch (TooManyItemsException e) {
			Assert.fail("thinks it has too many weapon but it hasn't");
		}
		String result = "je suis une squad et voici qui me compose :\n    je porte une épée et je porte un bouclier et je suis à pied\n    je porte une épée et je porte un bouclier et je suis à cheval\n";
		if (alpha.toString().compareTo(result) != 0){
			Assert.fail("wrong statement");
		}

	}

	public void testB_UpgradeSquadSF() {
		AbstractWarFactory factorySF = new SFWarFactory();
		
		Unit soldierA = factorySF.createLightUnit();
		Unit soldierB = factorySF.createMountedUnit();
		Squad alpha = new Squad();
		alpha.addUnit(soldierA);
		alpha.addUnit(soldierB);
		Weapon sword = factorySF.createOffenseItem();
		Weapon shield = factorySF.createDefenseItem();
		try {
			alpha.addWeapon(shield);
			alpha.addWeapon(sword);
		} catch (TooManyItemsException e) {
			Assert.fail("thinks it has too many weapon but it hasn't");
		}
		String result = "je suis une squad et voici qui me compose :\n    je porte une épée laser et je porte un bouclier magnétique et je suis un jedi\n    je porte une épée laser et je porte un bouclier magnétique et je suis sur un hovercraft\n";
		if (alpha.toString().compareTo(result) != 0){
			Assert.fail("wrong statement");
		}

	}

}
