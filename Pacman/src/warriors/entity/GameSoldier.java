package warriors.entity;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManager;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import warriors.army.Unit;
import warriors.customframework.WarriorsSpriteManagerImpl;
import warriors.observers.GameSoldierObserver;
import warriors.observers.Observer;
import warriors.proxy.InfantryMan;
import warriors.proxy.SoldierProxy;
import warriors.soldier.DeadSoldierException;
import warriors.visitor.WarriorsVisitor;
import warriors.weapon.TooManyItemsException;
import warriors.weapon.Weapon;

public class GameSoldier extends GameMovable implements GameEntity, Drawable, Overlappable, Unit, KeyListener{
	private SoldierProxy soldier;
	public static final int RENDERING_SIZE = 16;
	public static final int SPRITE_SIZE = 16;
	public static final int[] SPRITE_ROWS ={2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1};
	protected final SpriteManager spriteManager;
	protected boolean movable = true;
	private String spriteState = "";
	private boolean hurt = false;
	private int strikeKey = KeyEvent.VK_M;
	Point initPosition;
	GameSoldierObserver obs;


	public GameSoldier(Canvas defaultCanvas, String spritePath, GameSoldierObserver observer, int x, int y) {
		spriteManager = new WarriorsSpriteManagerImpl(spritePath,  //TODO Sprite joueur 2
				defaultCanvas, RENDERING_SIZE, SPRITE_SIZE, SPRITE_ROWS, getBoundingBox());
		spriteManager.setTypes("down", "right", "up", "left",
				"shield+down", "shield+right", "shield+up", "shield+left",
				"hit+down", "hit+right", "hit+up", "hit+left",
				"hurt+down", "hurt+right", "hurt+up", "hurt+left",
				"hurt+shield+down", "hurt+shield+right", "hurt+shield+up", "hurt+shield+left",
				"hurt+hit+down", "hurt+hit+right", "hurt+hit+up", "hurt+hit+left",
				"static+down", "static+right", "static+up", "static+left",
				"shield+static+down", "shield+static+right", "shield+static+up", "shield+static+left");
		soldier = new InfantryMan();
		soldier.attache(observer);
		initPosition = new Point(x,y);
		setPosition(initPosition);
		obs = observer;
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(
				RENDERING_SIZE,    
				RENDERING_SIZE,   
				RENDERING_SIZE,    
				RENDERING_SIZE); 
	}

	@Override
	public int parry(int damages){
		hurt = true;
		int dmg = 0;
		try {
			dmg = soldier.parry(damages);
		} catch (DeadSoldierException e) {
			soldier = new InfantryMan();
			soldier.attache(obs);
			spriteState = "";
			setPosition(initPosition);
			return dmg;
		}
		return dmg;
	}

	@Override
	public int strike() {
		int strength = soldier.strike();
		if (strength > 0)
			soldier.notifyStrike(this, strength);
		return strength; //TODO gestion des sprites de strike.
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
		Point tmp = getSpeedVector().getDirection();
		int speed = getSpeedVector().getSpeed();
		movable = true;
		String prefix = "";
		String suffix = "";
		if (speed == 0 && !spriteState.equals("hit+") && !hurt) {
			suffix += "static+";
		}
		if(hurt){
			prefix += "hurt+";
			hurt = false;
		}
		if (tmp.getX() == 1) {
			suffix += "right";
		} else if (tmp.getX() == -1) {
			suffix += "left";
		} else if (tmp.getY() == 1) {
			suffix += "down";
		} else if (tmp.getY() == -1) {
			suffix += "up";
		} else {
			suffix = "down";
			spriteManager.reset();
			movable = false;
		}
		spriteManager.setType(prefix+spriteState+suffix);
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

	public void setSpriteWeapon(String spriteWeapon) {
		this.spriteState = spriteWeapon;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	String currentWeapon;	
	@Override
	public void keyPressed(KeyEvent e) {
		currentWeapon = spriteState;
		int keycode = e.getKeyCode();
		if (keycode == strikeKey){
			if (strike() >0){
				spriteState = "hit+";
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keycode = e.getKeyCode();
		if (keycode == strikeKey){
			spriteState = currentWeapon;
		}
	}
}
