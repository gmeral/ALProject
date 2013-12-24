import proxy.InfantryMan;
import soldier.DeadSoldierException;
import weapon.Shield;
import weapon.Sword;
import weapon.TooManyItemsException;
import weapon.Weapon;
import Observers.FightObserver;
import army.Squad;
import army.Unit;


public class Main {

	public static void main(String args[]){
		Unit gaston;
		Unit roger;
		Unit robert;
		Unit leon;
		Unit jeanlouis;
		Squad alpha;

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
		
		FightObserver fightObserver = new FightObserver();	
		gaston.attache(fightObserver);
		roger.attache(fightObserver);
		leon.attache(fightObserver);
		robert.attache(fightObserver);
		jeanlouis.attache(fightObserver);

		while(true) {
			try {
				alpha.parry(jeanlouis.strike()+200);
			} catch (DeadSoldierException e) {
				break;
			}
		}
		
		System.out.println(fightObserver.getLogs());
	}
}
