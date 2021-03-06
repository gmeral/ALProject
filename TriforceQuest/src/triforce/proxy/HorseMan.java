package triforce.proxy;

import triforce.soldier.HorseManImpl;
import triforce.visitor.WarriorsVisitor;

public class HorseMan extends SoldierProxy {

	public HorseMan() {
		super(new HorseManImpl());
	}
	
	public HorseMan(String name) {
		super(new HorseManImpl(name));
	}
	@Override
	public void accept(WarriorsVisitor visitor) {
		super.accept(visitor);
		visitor.visit(this);
	}
}
