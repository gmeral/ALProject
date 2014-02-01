package triforce.army;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import triforce.soldier.DeadSoldierException;
import triforce.visitor.WarriorsVisitor;
import triforce.weapon.BrokenItemException;
import triforce.weapon.TooManyItemsException;
import triforce.weapon.Weapon;

public class Squad extends AbstractObservableUnit {

	private List<Unit> Units;
	private String name;
	static private int lambdaID = 0;
	
	public Squad(){
		this("lambda" + (++lambdaID));
	}
	
	public Squad(String s){
		Units = new ArrayList<>();
		name = s;
	}
	
	
	public int parry(int damages) throws DeadSoldierException, BrokenItemException {
		int damageReduced =0;
		if (Units.size() == 0){
			notifyParry(damageReduced);
			throw new DeadSoldierException();
		}
		int strength = damages / Units.size();
		for (int i=0 ; i<Units.size() ; i++){
			try {
				damageReduced += Units.get(i).parry(strength);
			} catch (DeadSoldierException e) {
				Units.remove(i);
			}
		}
		if (Units.size() == 0){
			notifyParry(damageReduced);
			notifyDeath();
			throw new DeadSoldierException();
		}
		notifyParry(damageReduced);
		return damageReduced;
	}


	public int strike() throws BrokenItemException {
		int strength = 0;
		for (int i=0 ; i<Units.size() ; i++){
			strength += Units.get(i).strike();
		}
		notifyStrike(strength);
		return strength;
	}

	public void addWeapon(Weapon w) throws TooManyItemsException {
		for (int i=0 ; i<Units.size() ; i++)
			Units.get(i).addWeapon(w);
	}


	public void remove(Weapon item) {
		for (int i=0 ; i<Units.size() ; i++){
			Units.get(i).remove(item);
		}
	}


	public void addUnit(Unit s){
		Units.add(s);
	}


	public void removeUnit(Unit s) {
		Units.remove(s);
	}

	public String toString(){
		return toString("");
	}
	
	public String toString(String tab){
		StringBuffer result = new StringBuffer("je suis une squad et voici qui me compose :\n");
		tab += "    ";
		for (int i=0 ; i<Units.size() ; i++){
			result.append(tab);
			if (Units.get(i)instanceof Squad)
				result.append(((Squad)Units.get(i)).toString(tab));
			else
				result.append(Units.get(i).toString());
		}
		return result.toString();
	}
	

	@Override
	public void accept(WarriorsVisitor visitor) {
		visitor.visit(this);
	}
	
	
	public Iterator<Unit> GetChildren(){
		return Units.iterator();
	}


	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getSightRange() {
		int range = 0;
		for (int i=0 ; i<Units.size() ; i++){
			if(Units.get(i).getSightRange() > range)
				range = Units.get(i).getSightRange();
		}
		return range;
	}

}
