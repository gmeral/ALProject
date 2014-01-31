package triforce.soldier;

public class HovercreftManImpl extends AbstractSoldier {

	public static final int INITIAL_ROBOTMAN_HP = 100;
	public static final int INITIAL_ROBOTMAN_STRENGTH = 20;

	
	public  HovercreftManImpl() {
		this("Peon" + (++peonID));
	}
	
	public  HovercreftManImpl(String name) {
		super(name,INITIAL_ROBOTMAN_STRENGTH, INITIAL_ROBOTMAN_HP);
	}



	public String toString(){
		return "je suis sur un hovercraft\n";
	}
}
