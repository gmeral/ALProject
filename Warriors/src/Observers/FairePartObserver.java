package Observers;

import army.Unit;

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



}
