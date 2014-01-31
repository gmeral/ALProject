package triforce.army;

import java.util.ArrayList;
import java.util.List;

import triforce.entity.Player;
import triforce.observers.Observer;

public abstract class AbstractObservableUnit implements Unit {


	private List <Observer> observers;

	public AbstractObservableUnit(){
		observers = new ArrayList<Observer>();
	}


	protected void notifyStrike(int strength) {
		List <Observer> copy = new ArrayList<Observer>(observers);
		for (Observer o : copy){
			o.strikeDone(this,strength);
		}
	}
	
	public void notifyBrokenShield(Player soldier) {
		List <Observer> copy = new ArrayList<Observer>(observers);
		for (Observer o : copy){
			o.brokenShield(soldier);
		}
	}
	
	public void notifyStrike(Player soldier, int strength) {
		List <Observer> copy = new ArrayList<Observer>(observers);
		for (Observer o : copy){
			o.strikeDone(soldier,strength);
		}
	}


	protected void notifyParry(int damageReduced) {
		List <Observer> copy = new ArrayList<Observer>(observers);
		for (Observer o : copy){
			o.parryDone(this,damageReduced);
		}
	}

	public void notifyMove(Player gs){
		List <Observer> copy = new ArrayList<Observer>(observers);
		for (Observer o : copy){
			o.moved(gs);
		}
	}

	protected void notifyDeath() {
		List <Observer> copy = new ArrayList<Observer>(observers);
		for (Observer o : copy){
			o.deadUnit(this);
		}
	}

	public void attache(Observer o){
		observers.add(o);
	}

	public void detache(Observer o){
		observers.remove(o);
	}
}
