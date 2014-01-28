package warriors.tests;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import warriors.army.Squad;
import warriors.army.Unit;
import warriors.observers.FightObserver;
import warriors.proxy.InfantryMan;
import warriors.soldier.DeadSoldierException;
import warriors.weapon.Shield;
import warriors.weapon.Sword;
import warriors.weapon.TooManyItemsException;
import warriors.weapon.Weapon;

public class TestObserver {

	private Unit gaston;
	private Unit roger;
	private Unit robert;
	private Unit leon;
	private Unit jeanlouis;
	private Squad alpha;
	
	
	@BeforeClass
	public void init(){
		gaston = new InfantryMan();
		roger = new InfantryMan();
		robert = new InfantryMan();
		leon = new InfantryMan();
		jeanlouis = new InfantryMan();
		Weapon sword = new Sword();
		Weapon shield = new Shield();
		alpha = new Squad();
		alpha.addUnit(gaston);
		alpha.addUnit(roger);
		alpha.addUnit(robert);
		alpha.addUnit(leon);
		alpha.addUnit(jeanlouis);
		try {
			alpha.addWeapon(sword);
			alpha.addWeapon(shield);
		} catch (TooManyItemsException e1) {
			e1.printStackTrace();
		}
	}

	@Test
	public void TestA_FightObserver() {
		FightObserver fightObserver = new FightObserver();	
		gaston.attache(fightObserver);
		roger.attache(fightObserver);
		leon.attache(fightObserver);
		robert.attache(fightObserver);
		jeanlouis.attache(fightObserver);

		while(true) {
			try {
				alpha.parry(jeanlouis.strike()+ 50);
			} catch (DeadSoldierException e) {
				Assert.assertEquals(5, fightObserver);
				break;
			}
		}
	}

	
}
