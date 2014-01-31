package triforce.entity;

import gameframework.base.Overlappable;
import gameframework.game.GameEntity;

public interface Bonus extends GameEntity, Overlappable{
	boolean applyBonus(Player gs);
}
