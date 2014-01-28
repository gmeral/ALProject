package warriors.soldier;

public class JediImpl extends AbstractSoldier {


	public static final int INITIAL_GROUNDSOLDIER_HP = 100;
	public static final int INITIAL_GROUNDSOLDIER_STRENGTH = 20;
	
	public JediImpl() {
		this("Peon" + (++peonID));
	}
	
	public JediImpl(String name) {
		super(name, INITIAL_GROUNDSOLDIER_STRENGTH, INITIAL_GROUNDSOLDIER_HP);
	}

	public String toString(){
		return "je suis un jedi\n";
	}
}
