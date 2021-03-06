package triforce.soldier;

import triforce.visitor.WarriorsVisitor;

abstract class AbstractSoldier implements Soldier {

	private int strength;
	private int healthPoints;
	private int maxHealthPoints;
	private int sightRange;
	private String name;
	static protected int peonID = 0;
	public static final int INITIAL_SIGHTRANGE = 3;
	
	protected AbstractSoldier(int str, int hp) {
		strength = str;
		healthPoints = hp;
		maxHealthPoints = hp;
		sightRange = INITIAL_SIGHTRANGE;
	}
		
	protected AbstractSoldier(String s,int str, int hp) {
		name = s;
		strength = str;
		healthPoints = hp;
		maxHealthPoints = hp;
		sightRange = INITIAL_SIGHTRANGE;
	}
	
	public int parry(int damages) throws DeadSoldierException {
		int oldHP = healthPoints;
		healthPoints = (damages > healthPoints ? 0 : healthPoints - damages);
		if (healthPoints == 0)
			throw new DeadSoldierException();
		return oldHP - healthPoints;
	}

	public int getNbRemainingStrike() {
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
	
	public int getMaxHealth(){
		return maxHealthPoints;
	}
	public int getCurrentHealth(){
		return healthPoints;
	}
}
