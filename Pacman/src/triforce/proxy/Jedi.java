package triforce.proxy;

import triforce.soldier.JediImpl;
import triforce.visitor.WarriorsVisitor;

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
