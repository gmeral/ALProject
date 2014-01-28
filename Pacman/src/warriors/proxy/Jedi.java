package warriors.proxy;

import warriors.soldier.JediImpl;
import warriors.visitor.WarriorsVisitor;

public class Jedi extends SoldierProxy{

	public Jedi() {
		super(new JediImpl());
	}
	
	@Override
	public void accept(WarriorsVisitor visitor) {
		super.accept(visitor);
		visitor.visit(this);
	}
}
