package proxy;

import soldier.InfantryManImpl;
import visitor.WarriorsVisitor;

public class InfantryMan extends SoldierProxy {
	

	public InfantryMan() {
		super(new InfantryManImpl());
	}
		
	public InfantryMan(String name) {	
		super(new InfantryManImpl(name));
	}

	
	@Override
	public void accept(WarriorsVisitor visitor) {
		super.accept(visitor);
		visitor.visit(this);
	}
}
