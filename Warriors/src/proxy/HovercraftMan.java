package proxy;

import soldier.HovercreftManImpl;
import visitor.WarriorsVisitor;

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
