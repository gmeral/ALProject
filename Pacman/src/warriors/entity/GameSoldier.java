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
import warriors.customframework.WarriorsGameImpl;
import warriors.customframework.WarriorsSpriteManagerImpl;
import warriors.observers.GameSoldierObserver;
import warriors.observers.Observer;
import warriors.proxy.InfantryMan;
import warriors.proxy.SoldierProxy;
import warriors.soldier.DeadSoldierException;
import warriors.visitor.WarriorsVisitor;
import warriors.weapon.BrokenItemException;
import warriors.weapon.TooManyItemsException;
import warriors.weapon.Weapon;

public class GameSoldier extends GameMovable implements GameEntity, Drawable, Overlappable, Unit, KeyListener{
	private SoldierProxy soldier;
	public static final int RENDERING_SIZE = WarriorsGameImpl.GLOBAL_SPRITE_SIZE;
	public static final int SPRITE_SIZE = 16;
	public static final int[] SPRITE_ROWS ={4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,  4, 4, 4, 4, 4, 4, 4, 4,4};
	protected final SpriteManager spriteManager;
	protected int invincibleTimer = 0;
	protected int maxInvincibleTimer = 0;
	protected boolean movable = true;
	private String spriteState = "";
	private int hurtFrames = 0;
	private int strikeKey = KeyEvent.VK_M;
	Point initPosition;
	GameSoldierObserver obs;


	public GameSoldier(Canvas defaultCanvas, String spritePath, GameSoldierObserver observer, int x, int y, int strikeKey) {
		spriteManager = new WarriorsSpriteManagerImpl(spritePath,
				defaultCanvas, RENDERING_SIZE, SPRITE_SIZE, SPRITE_ROWS, getBoundingBox());
		spriteManager.setTypes("down", "right", "up", "left",
				"shield+down", "shield+right", "shield+up", "shield+left",
				"hit+down", "hit+right", "hit+up", "hit+left",
				"hurt+down", "hurt+right", "hurt+up", "hurt+left",
				"hurt+shield+down", "hurt+shield+right", "hurt+shield+up", "hurt+shield+left",
				"hurt+hit+down", "hurt+hit+right", "hurt+hit+up", "hurt+hit+left",
				"static+down", "static+right", "static+up", "static+left",
				"shield+static+down", "shield+static+right", "shield+static+up", "shield+static+left",
				"win");

		soldier = new InfantryMan();
		soldier.attache(observer);
		initPosition = new Point(x,y);
		setPosition(initPosition);
		obs = observer;
		this.strikeKey = strikeKey;
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
		if(!isInvincible()){
			hurtFrames = 4;
			int dmg = 0;
			try {
				dmg = soldier.parry(damages);
			} catch (DeadSoldierException e) {
				soldier = new InfantryMan();
				soldier.attache(obs);
				spriteState = "";
				setPosition(initPosition);
				return dmg;
			} catch (BrokenItemException e) {
				soldier.notifyBrokenShield(this);
			}
			return dmg;
		}
		return 0;
	}

	@Override
	public int strike() {
		int strength = soldier.strike();
		if (strength > 0)
			soldier.notifyStrike(this, strength);
		return strength;
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
		if (isInvincible()) {
			invincibleTimer--;
		}
		String prefix = "";
		String suffix = "";
		if(!spriteState.equals("win")){
			Point tmp = getSpeedVector().getDirection();
			int speed = getSpeedVector().getSpeed();
			movable = true;

			if (speed == 0 && !spriteState.equals("hit+") && hurtFrames == 0) {
				suffix += "static+";
			}
			if(hurtFrames > 0){
				prefix += "hurt+";
				hurtFrames--;
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

	public void setSpriteState(String spriteWeapon) {
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

	public boolean isInvincible() {
		return invincibleTimer > 0;
	}

	public void setInvincible(int timer) {
		maxInvincibleTimer = invincibleTimer = timer;
	}

	public int getHealth() {
		return soldier.getHealth();
	}

	public int getMaxHealth() {
		return soldier.getMaxHealth();
	}

	public int getNbRemainingStrike() {
		return soldier.getNbRemainingStrike();
		
	}
}
