package warriors.entity;

import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;

import java.awt.Canvas;

public abstract class AbstractBonus extends AbstractHideable implements GameEntity, Overlappable {

	public AbstractBonus(Canvas defaultCanvas, int xx, int yy, DrawableImage img) {
		super(defaultCanvas, xx, yy,img);
	}

	public abstract boolean applyBonus(GameSoldier gs);
}
