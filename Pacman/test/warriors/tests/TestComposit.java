package warriors.tests;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import triforce.army.Squad;
import triforce.army.Unit;
import triforce.proxy.SoldierProxy;
import triforce.soldier.DeadSoldierException;
import triforce.soldier.HorseManImpl;
import triforce.soldier.InfantryManImpl;
import triforce.weapon.BrokenItemException;
import triforce.weapon.Shield;
import triforce.weapon.Sword;
import triforce.weapon.TooManyItemsException;
import triforce.weapon.Weapon;

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
		for(int i=0 ; i<4 ; i++) {
			army.addUnit(new SoldierProxy(new HorseManImpl()));
		}	
		try {
			army.addWeapon(new Sword());
			army.addWeapon(new Sword());
		} catch (TooManyItemsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//a horseman can handle 3 of his own strikes, so does an horseman army
		for(int i=0 ; i<3 ; i++) {
			try {
				try {
					army.parry(army.strike());
				} catch (BrokenItemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (DeadSoldierException e) {
				Assert.fail("too early");
			}
		}
		try {
			try {
				army.parry(army.strike());
			} catch (BrokenItemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			Assert.fail("had to die");
		} catch (DeadSoldierException e) {

		}

	}
}
