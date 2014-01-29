package warriors.entity;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManager;
import gameframework.game.SpriteManagerDefaultImpl;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import warriors.army.Unit;
import warriors.observers.Observer;
import warriors.proxy.InfantryMan;
import warriors.proxy.SoldierProxy;
import warriors.soldier.DeadSoldierException;
import warriors.visitor.WarriorsVisitor;
import warriors.weapon.TooManyItemsException;
import warriors.weapon.Weapon;

public class GameSoldier extends GameMovable implements GameEntity, Drawable, Overlappable, Unit{
	private SoldierProxy soldier;
	public static final int RENDERING_SIZE = 16;
	protected final SpriteManager spriteManager;
	protected boolean movable = true;

	public GameSoldier(Canvas defaultCanvas, boolean isGood) {
		if(isGood)
			spriteManager = new SpriteManagerDefaultImpl("images/link.gif",  //TODO Sprite good guy
					defaultCanvas, RENDERING_SIZE, 13);
		else
			spriteManager = new SpriteManagerDefaultImpl("images/link.gif",  //TODO Sprite bad guy (changer une couleur du good guy)
					defaultCanvas, RENDERING_SIZE, 13);
		spriteManager.setTypes("down", "right", "up", "left");
		soldier = new InfantryMan();
	}

	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE));
	}

	@Override
	public int parry(int damages) throws DeadSoldierException {
		return soldier.parry(damages);
	}

	@Override
	public int strike() {
		return soldier.strike(); //TODO gestion des sprites de strike.
	}

	@Override
	public String getName() {
		return soldier.getName();
	}

	@Override
	public void addWeapon(Weapon w) throws TooManyItemsException {
		soldier.addWeapon(w);
	}

	@Override
	public void remove(Weapon item) {
		soldier.remove(item);
	}

	@Override
	public void accept(WarriorsVisitor visitor) {
		soldier.accept(visitor);
	}

	@Override
	public void attache(Observer o) {
		soldier.attache(o);
	}

	@Override
	public void detache(Observer o) {
		soldier.detache(o);
	}

	@Override
	public void draw(Graphics g) {
		String spriteType = "";
		Point tmp = getSpeedVector().getDirection();
		movable = true;

		if (tmp.getX() == 1) {
			spriteType += "right";
		} else if (tmp.getX() == -1) {
			spriteType += "left";
		} else if (tmp.getY() == 1) {
			spriteType += "down";
		} else if (tmp.getY() == -1) {
			spriteType += "up";
		} else {
			spriteType = "down";
			spriteManager.reset();
			movable = false;
		}
		spriteManager.setType(spriteType);
		spriteManager.draw(g, getPosition());
		soldier.notifyMove(this);
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		if (movable) 
			spriteManager.increment();
	}

	@Override
	public int getSightRange() {
		return soldier.getSightRange();
	}
	
	
}
