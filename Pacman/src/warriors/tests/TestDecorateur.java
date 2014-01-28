package warriors.tests;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import warriors.soldier.HorseManImpl;
import warriors.soldier.Soldier;
import warriors.weapon.Sword;
import warriors.weapon.TooManyItemsException;
import warriors.weapon.Weapon;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDecorateur{

	
	@Test
	public void testA_UpgradeSoldier() {
		Soldier horse = new HorseManImpl();		
		Weapon s = new Sword();
		try {
			horse = s.getDeco(horse);
		} catch (TooManyItemsException e) {
			Assert.fail("can't add weapon, thinks it has too many but hasn't one");
		}
		
		String result = "je porte une épée et je suis à cheval\n";
		if (horse.toString().compareTo(result) != 0){
			Assert.fail("wrong statement");
		}
			
	}	
	
	@Test
	public void testB_UpgradeTooMany() {
		Soldier horse = new HorseManImpl();		
		Weapon leftSword = new Sword();	
		Weapon rightSword = new Sword();	
		Weapon extraSword = new Sword();
		try {
			horse = leftSword.getDeco(horse);
			horse = rightSword.getDeco(horse);
			horse = extraSword.getDeco(horse);
			Assert.fail("can add 3 weapons");
		} catch (TooManyItemsException e) {
			System.out.println(e.getMessage());
		}
		
		String result = "je porte une épée et je porte une épée et je suis à cheval\n";
		if (horse.toString().compareTo(result) != 0){
			Assert.fail("wrong statement");
		}
	}

}
