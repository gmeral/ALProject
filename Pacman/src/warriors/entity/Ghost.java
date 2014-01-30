package warriors.entity;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManagerDefaultImpl;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Ghost extends GameMovable implements Drawable, GameEntity, Overlappable, Hideable {

	protected static DrawableImage image = null;
	protected boolean movable = true;
	protected int afraidTimer = 0;
	protected int maxAfraidTimer = 0;
	protected boolean active = true;
	private final SpriteManagerDefaultImpl spriteManager;
	private int damages = 1;
	private boolean visible = false;
	public static final int RENDERING_SIZE = 16;

	public Ghost(Canvas defaultCanvas,int x, int y) {
		spriteManager = new SpriteManagerDefaultImpl("images/wizzrobe.gif",
				defaultCanvas, RENDERING_SIZE, 8);
		spriteManager.setTypes(
				"left",
				"right",
				"up",
				"down");
		setPosition(new Point(x, y));
	}

	public boolean isAfraid() {
		return afraidTimer > 0;
	}

	public void setAfraid(int timer) {
		maxAfraidTimer = afraidTimer = timer;
	}

	public boolean isActive() {
		return active;
	}

	public void setAlive(boolean aliveState) {
		active = aliveState;
	}

	public void draw(Graphics g) {
		if(visible){
			String spriteType = "";
			Point tmp = getSpeedVector().getDirection();
			movable = true;
			if (tmp.getX() == -1) {
				spriteType += "left";
			} else if (tmp.getY() == 1) {
				spriteType += "down";
			} else if (tmp.getY() == -1) {
				spriteType += "up";
			} else {
				spriteType += "right";
			}
			spriteManager.setType(spriteType);
			spriteManager.draw(g, getPosition());
			hide();
		}
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		if (movable) {
			spriteManager.increment();
			if (isAfraid()) {
				afraidTimer--;
			}
		}
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE));
	}

	public int getDamages() {
		return damages ;
	}

	public void show(){
		visible  = true;
	}

	public void hide(){
		visible = false;
	}
}
