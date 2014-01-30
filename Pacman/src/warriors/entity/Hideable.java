package warriors.entity;

import java.awt.Point;

import gameframework.base.Drawable;
import gameframework.game.GameEntity;

public interface Hideable extends GameEntity, Drawable {
	void show();
	void hide();
	Point getPosition();
}
