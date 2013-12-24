package weapon;

import soldier.Soldier;


public class BrokenItemException extends Exception {

	private Soldier nextItem;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5989017705175838795L;
	
	public BrokenItemException(String arg0, Soldier soldier) {
		super(arg0);
		this.nextItem = soldier;
	}
	
	
	public Soldier nextItem() {
		return nextItem;
	}
}
