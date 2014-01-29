package warriors.observers;

import warriors.army.Unit;
import warriors.entity.GameSoldier;

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
	public void moved(GameSoldier soldier) {}
	

}
