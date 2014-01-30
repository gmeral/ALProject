package warriors.observers;

import warriors.army.Unit;
import warriors.entity.GameSoldier;

public class FightObserver implements Observer {

	private StringBuffer fightLog = new StringBuffer();
	
	public FightObserver(){}
	
	@Override
	public void strikeDone(Unit u, int dmg) {		
		fightLog.append(u.getName() + " a donné un coup de force " + dmg + "\n");
	}

	@Override
	public void parryDone(Unit u, int dmg) {
		fightLog.append(u.getName() + " a paré " + dmg + " dommages\n");
	}

	@Override
	public void deadUnit(Unit u) {
		fightLog.append(u.getName() + " est mort\n");
	}

	public String getLogs(){
		return fightLog.toString();
	}

	@Override
	public void moved(GameSoldier soldier) {}
	
}
