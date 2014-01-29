package warriors.entity;

import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;

import java.awt.Canvas;

public class Floor extends AbstractHideable implements Overlappable {
	
	public Floor(Canvas defaultCanvas, int xx, int yy) {
		super(defaultCanvas, xx, yy, new DrawableImage("images/floor.gif", defaultCanvas)); //TODO mettre image sol
	}
}
