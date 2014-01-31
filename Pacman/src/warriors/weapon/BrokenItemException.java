package warriors.weapon;

import warriors.soldier.Soldier;


public class BrokenItemException extends Exception {

	private Soldier nextItem;
	private int strength;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5989017705175838795L;
	
	public BrokenItemException(String arg0, Soldier soldier, int damages) {
		super(arg0);
		this.nextItem = soldier;
		strength = damages;
	}
	
	
	public Soldier nextItem() {
		return nextItem;
	}


	public int getStrength() {
		return strength;
	}
}
