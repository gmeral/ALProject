package Observers;

import army.Unit;

public interface Observer {
	void strikeDone(Unit u, int dmg);
	void parryDone(Unit u, int dmg);
	void deadUnit(Unit u);
}
