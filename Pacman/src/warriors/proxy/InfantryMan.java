package warriors.proxy;

import warriors.soldier.InfantryManImpl;
import warriors.visitor.WarriorsVisitor;

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
