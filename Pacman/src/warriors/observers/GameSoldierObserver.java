package warriors.observers;

import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.GameUniverse;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Iterator;

import warriors.GameLevelOne;
import warriors.army.Unit;
import warriors.entity.AbstractHideable;
import warriors.entity.GameSoldier;
import warriors.soldier.DeadSoldierException;


public class GameSoldierObserver implements Observer{

	public static final int SpriteSize = 16;
	private GameUniverse universe;

	public GameSoldierObserver(GameUniverse univ){
		universe = univ;
	}

	@Override
	public void strikeDone(Unit u, int dmg) {
		if(u instanceof GameSoldier)
			strikeDone((GameSoldier)u, dmg);
	}

	@Override
	public void parryDone(Unit u, int dmg) {}

	@Override
	public void deadUnit(Unit u) {}


	public void strikeDone(GameSoldier soldier, int dmg) {	
		Point hitPlace = new Point();
		hitPlace.setLocation(soldier.getPosition().getX() + soldier.getSpeedVector().getDirection().getX()*SpriteSize,
				soldier.getPosition().getY() + soldier.getSpeedVector().getDirection().getY()*SpriteSize);
		Iterator<GameEntity> entity = universe.gameEntities();
		while(entity.hasNext()){
			GameEntity current = entity.next();
			if (current instanceof GameSoldier && ((GameSoldier) current).getPosition().equals(hitPlace)){
				((GameSoldier) current).parry(dmg);
			}
		}
	}

	@Override
	public void moved(GameSoldier soldier) {
		Iterator<GameEntity> it = universe.gameEntities();
		while(it.hasNext()){
			GameEntity current = it.next();
			if(current instanceof AbstractHideable){
				AbstractHideable currentHideable = (AbstractHideable) current;
				if (this.near(currentHideable, soldier)){
					currentHideable.show();
				}
			}
		}
	}

	private boolean near(AbstractHideable current, GameSoldier soldier) {
		Point2D p1 = current.getPosition();
		Point2D p2 = soldier.getPosition();
		return p1.distance(p2) <= soldier.getSightRange()*GameLevelOne.SPRITE_SIZE;
	}

}
