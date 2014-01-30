package warriors.soldier;

import warriors.visitor.WarriorsVisitor;

abstract class AbstractSoldier implements Soldier {

	private int strength;
	private int healthPoints;
	private int sightRange;
	private String name;
	static protected int peonID = 0;
	public static final int INITIAL_SIGHTRANGE = 10;
	
	protected AbstractSoldier(int str, int hp) {
		strength = str;
		healthPoints = hp;
		sightRange = INITIAL_SIGHTRANGE;
	}
		
	protected AbstractSoldier(String s,int str, int hp) {
		name = s;
		strength = str;
		healthPoints = hp;
		sightRange = INITIAL_SIGHTRANGE;
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
	
	public int getSightRange() {
		return sightRange;
	}
}
