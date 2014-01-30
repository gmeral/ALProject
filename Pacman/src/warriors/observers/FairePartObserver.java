package warriors.observers;

import warriors.army.Unit;
import warriors.entity.GameSoldier;

public class FairePartObserver implements Observer {

	@Override
	public void strikeDone(Unit u, int dmg) {}

	@Override
	public void parryDone(Unit u, int dmg) {}

	@Override
	public void deadUnit(Unit u) {
		String name= u.getName();
		System.out.println(name + " vient de mourir");
	}

	@Override
	public void moved(GameSoldier soldier) {}

	@Override
	public void brokenShield(Unit u) {
		// TODO Auto-generated method stub
		
	}



}
