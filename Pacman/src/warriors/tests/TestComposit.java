package warriors.tests;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import warriors.army.Squad;
import warriors.army.Unit;
import warriors.proxy.SoldierProxy;
import warriors.soldier.DeadSoldierException;
import warriors.soldier.HorseManImpl;
import warriors.soldier.InfantryManImpl;
import warriors.weapon.Shield;
import warriors.weapon.Sword;
import warriors.weapon.TooManyItemsException;
import warriors.weapon.Weapon;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestComposit {

	@Test
	public void testA_UpgradeSquad() {
		Unit soldierA = new SoldierProxy(new InfantryManImpl());
		Unit soldierB = new SoldierProxy(new InfantryManImpl());
		Squad alpha = new Squad();
		alpha.addUnit(soldierA);
		alpha.addUnit(soldierB);
		Weapon sword = new Sword();
		Weapon shield = new Shield();
		try {
			alpha.addWeapon(shield);
			alpha.addWeapon(sword);
		} catch (TooManyItemsException e) {
			Assert.fail("thinks it has too many weapon but it hasn't");
		}
		String result = "je suis une squad et voici qui me compose :\n    je porte une épée et je porte un bouclier et je suis à pied\n    je porte une épée et je porte un bouclier et je suis à pied\n";
		if (alpha.toString().compareTo(result) != 0){
			Assert.fail("wrong statement");
		}
	}	

	@Test
	public void testB_DeadSoldierException() {
		Squad army = new Squad();
		for(int i=0 ; i<5 ; i++) {
			army.addUnit(new SoldierProxy(new HorseManImpl()));
		}	

		//a horseman can handle 6 of his own strikes, so does an horseman army
		for(int i=0 ; i<6 ; i++) {
			try {
				army.parry(army.strike());
			} catch (DeadSoldierException e) {
				Assert.fail("too early");
			}
		}
		try {
			army.parry(army.strike());
			Assert.fail("had to die");
		} catch (DeadSoldierException e) {

		}

	}
}
