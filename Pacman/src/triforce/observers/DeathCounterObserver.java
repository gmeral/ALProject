package triforce.observers;

import triforce.army.Unit;
import triforce.entity.Player;

public class DeathCounterObserver implements Observer {

	private int NbDeaths;
	
	@Override
	public void strikeDone(Unit u, int dmg) {}

	@Override
	public void parryDone(Unit u, int dmg) {}

	@Override
	public void deadUnit(Unit u) {
		NbDeaths++;
	}

	public int getNbDeaths() {
		return NbDeaths;
	}

	@Override
	public void moved(Player soldier) {}

	@Override
	public void brokenShield(Unit u) {
		// TODO Auto-generated method stub
		
	}
	

}
