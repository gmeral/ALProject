package triforce.entity.displaybar;

import java.awt.Canvas;

public class LifeContainer  extends AbstractGameContainer{

	protected LifeContainer(Canvas defaultCanvas, int xx, int yy, int size) {
		super(defaultCanvas, xx, yy, size, "images/Life.gif", "images/LifeEmpty.png");
	}
}
