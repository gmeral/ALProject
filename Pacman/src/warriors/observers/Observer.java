package warriors.observers;

import warriors.army.Unit;
import warriors.entity.GameSoldier;

public interface Observer {
	void strikeDone(Unit u, int dmg);
	void parryDone(Unit u, int dmg);
	void deadUnit(Unit u);
	void moved(GameSoldier soldier);
}
