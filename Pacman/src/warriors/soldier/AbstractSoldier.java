package warriors.soldier;

import warriors.visitor.WarriorsVisitor;

abstract class AbstractSoldier implements Soldier {

	private int strength;
	private int healthPoints;
	private String name;
	static protected int peonID = 0;
	
	protected AbstractSoldier(int str, int hp) {
		strength = str;
		healthPoints = hp;
	}
		
	protected AbstractSoldier(String s,int str, int hp) {
		name = s;
		strength = str;
		healthPoints = hp;
	}
	
	public int parry(int damages) throws DeadSoldierException {
		healthPoints = (damages > healthPoints ? 0 : healthPoints - damages);
		if (healthPoints == 0)
			throw new DeadSoldierException();
		return 0;
	}

	

	public int strike() {
		return strength;
	}

	public int getStrength() {
		return strength;
	}

	public int getHealthPoints() {
		return healthPoints;
	}


	public void accept(WarriorsVisitor visitor) {
		//not implemented
	}
	
	public String getName() {
		return name;
	}

}
