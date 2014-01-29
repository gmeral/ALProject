package warriors.entity;

import gameframework.base.DrawableImage;
import gameframework.game.MoveBlocker;

import java.awt.Canvas;

public class Wall extends AbstractHideable implements MoveBlocker  {

	public Wall(Canvas defaultCanvas, int xx, int yy) {
		super(defaultCanvas, xx, yy,new DrawableImage("images/wall.gif", defaultCanvas));
	}
}
