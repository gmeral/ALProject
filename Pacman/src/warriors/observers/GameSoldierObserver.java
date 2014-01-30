package warriors.observers;

import gameframework.game.GameEntity;
import gameframework.game.GameUniverse;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.List;

import warriors.GameLevelOne;
import warriors.army.Unit;
import warriors.entity.GameSoldier;
import warriors.entity.Ghost;
import warriors.entity.Hideable;


public class GameSoldierObserver implements Observer{

	public static final int SpriteSize = 16;
	private GameUniverse universe;
	List<GameEntity> targets;
	public GameSoldierObserver(GameUniverse univ){
		universe = univ;
	}


	public void setTargets(List<GameEntity> list){
		targets = list;
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
		for(GameEntity current : targets){
			if (current instanceof GameSoldier && ((GameSoldier) current).getPosition().equals(hitPlace)){
				((GameSoldier) current).parry(dmg);
			}		
			if (current instanceof Ghost && ((Ghost) current).getPosition().equals(hitPlace)){
				universe.removeGameEntity(current);
			}
		}
	}

	@Override
	public void moved(GameSoldier soldier) {
		Iterator<GameEntity> it = universe.gameEntities();
		while(it.hasNext()){
			GameEntity current = it.next();
			if(current instanceof Hideable){
				Hideable currentHideable = (Hideable) current;
				if (this.near(currentHideable, soldier)){
					currentHideable.show();
				}
			}
		}
	}

	private boolean near(Hideable current, GameSoldier soldier) {
		Point2D p1 = current.getPosition();
		Point2D p2 = soldier.getPosition();
		return p1.distance(p2) <= soldier.getSightRange()*GameLevelOne.SPRITE_SIZE;
	}

	@Override
	public void brokenShield(Unit u) {
		if (u instanceof GameSoldier)
			brokenShield((GameSoldier)u);
	}
	
	public void brokenShield(GameSoldier soldier) {
		soldier.setSpriteState("");
	}
}
