package triforce.entity.displaybar;

import java.awt.Canvas;

public class SwordContainer extends AbstractGameContainer{

	protected SwordContainer(Canvas defaultCanvas, int xx, int yy, int size) {
		super(defaultCanvas, xx, yy, size, "images/sword.gif", "images/swordEmpty.png");
	}
}
