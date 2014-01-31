package triforce.observers;

import triforce.army.Unit;
import triforce.entity.Player;

public interface Observer {
	void strikeDone(Unit u, int dmg);
	void parryDone(Unit u, int dmg);
	void deadUnit(Unit u);
	void moved(Player soldier);
	void brokenShield(Unit u);
}
