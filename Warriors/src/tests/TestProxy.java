package tests;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import proxy.SoldierProxy;
import soldier.InfantryManImpl;
import weapon.Shield;
import weapon.Sword;
import weapon.TooManyItemsException;
import weapon.Weapon;
import army.Unit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProxy {

	@Test
	public void testA_UpgradeSoldier() {
		Unit soldier = new SoldierProxy(new InfantryManImpl());
		Weapon sword = new Sword();
		Weapon shield = new Shield();
		try {
			soldier.addWeapon(shield);
			soldier.addWeapon(sword);
		} catch (TooManyItemsException e) {
			Assert.fail("thinks it has too many weapon but it hasn't");
		}
		String result = "je porte une épée et je porte un bouclier et je suis à pied\n";
		if (soldier.toString().compareTo(result) != 0){
			Assert.fail("wrong statement");
		}
	}	

	@Test
	public void testB_RemoveShield() {
		Unit soldier = new SoldierProxy(new InfantryManImpl());
		Weapon sword = new Sword();
		Weapon shield = new Shield();
		try {
			soldier.addWeapon(shield);
			soldier.addWeapon(sword);
		} catch (TooManyItemsException e) {
			Assert.fail("thinks it has too many weapon but it hasn't");
		}
		soldier.remove(shield);

		String result = "je porte une épée et je suis à pied\n";
		if (soldier.toString().compareTo(result) != 0)
			Assert.fail("wrong statement");

	}
		
	
	@Test
	public void testC_removeSword() {
		Unit soldier = new SoldierProxy(new InfantryManImpl());
		Weapon sword = new Sword();
		Weapon shield = new Shield();
		try {
			soldier.addWeapon(shield);
			soldier.addWeapon(sword);
		} catch (TooManyItemsException e) {
			Assert.fail("thinks it has too many weapon but it hasn't");
		}
		soldier.remove(sword);

		String result = "je porte un bouclier et je suis à pied\n";
		if (soldier.toString().compareTo(result) != 0)
			Assert.fail("wrong statement");

	}	

	@Test
	public void testD_TooManyItems() {
		Unit soldier = new SoldierProxy(new InfantryManImpl());
		Weapon leftSword = new Sword();	
		Weapon rightSword = new Sword();	
		Weapon extraSword = new Sword();
		try {
			soldier.addWeapon(leftSword);
			soldier.addWeapon(rightSword);
			soldier.addWeapon(extraSword);
			Assert.fail("can have 3 weapons");
		} catch (TooManyItemsException e) {
			String result = "je porte une épée et je porte une épée et je suis à pied\n";
			if (soldier.toString().compareTo(result) != 0)
				Assert.fail("wrong statement");
		}



	}	
	
	@Test
	public void testE_RemoveUnequippedItem() {
		Unit soldier = new SoldierProxy(new InfantryManImpl());
		Weapon notEquipedSword = new Sword();
		soldier.remove(notEquipedSword);
		String result = "je suis à pied\n";
		if (soldier.toString().compareTo(result) != 0)
			Assert.fail("wrong statement");

	}	

}
