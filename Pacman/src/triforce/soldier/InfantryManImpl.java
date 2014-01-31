package triforce.soldier;


public class InfantryManImpl extends AbstractSoldier{

	public static final int INITIAL_INFANTRYMAN_HP = 3;
	public static final int INITIAL_INFANTRYMAN_STRENGTH = 0;
	
	
	public InfantryManImpl(){
		this("Peon" + (++peonID));
	}
	
	public InfantryManImpl(String name){
		super(name,INITIAL_INFANTRYMAN_STRENGTH,INITIAL_INFANTRYMAN_HP);
	}
	
	public String toString(){
		return "je suis à pied\n";
	}
}
