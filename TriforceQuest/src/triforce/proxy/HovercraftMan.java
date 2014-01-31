package triforce.proxy;

import triforce.soldier.HovercreftManImpl;
import triforce.visitor.WarriorsVisitor;

public class HovercraftMan extends SoldierProxy{

	public HovercraftMan() {
		super(new HovercreftManImpl());
	}

	@Override
	public void accept(WarriorsVisitor visitor) {
		super.accept(visitor);
		visitor.visit(this);
	}
}
