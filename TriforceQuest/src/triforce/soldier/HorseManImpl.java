package triforce.soldier;


 
public class HorseManImpl extends AbstractSoldier {

	public static final int INITIAL_HORSEMAN_HP = 4;
	public static final int INITIAL_HORSEMAN_STRENGTH = 0;

	
	public HorseManImpl (){
		this("Peon" + (++peonID));
	}

	public HorseManImpl(String name) {
		super(name, INITIAL_HORSEMAN_STRENGTH, INITIAL_HORSEMAN_HP);
	}

	public String toString(){
		return "je suis à cheval\n";
	}

}
