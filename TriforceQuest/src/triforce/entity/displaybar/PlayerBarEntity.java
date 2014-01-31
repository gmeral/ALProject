package triforce.entity.displaybar;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

import triforce.customframework.TriforceGameImpl;
import triforce.entity.Player;
import triforce.weapon.Sword;

public class PlayerBarEntity implements  Drawable{

	private DrawableImage playerImage;
	private Player playerEntity;
	private LifeContainer lifeCount;
	private SwordContainer swordCount;
	private int x,y;
	private static final int RENDERING_SIZE = TriforceGameImpl.GLOBAL_SPRITE_SIZE;
	
	public PlayerBarEntity(Canvas defaultCanvas, String playerImagePath, Player player, int xx, int yy){
		x = xx;
		y = yy;
		playerImage = new DrawableImage(playerImagePath, defaultCanvas);
		playerEntity = player;
		lifeCount = new LifeContainer(defaultCanvas, x + RENDERING_SIZE, y, playerEntity.getMaxHealth());
		int swordCountXPosition  = lifeCount.getPosition().x + lifeCount.size * RENDERING_SIZE;
		swordCount = new SwordContainer(defaultCanvas, swordCountXPosition, y, Sword.swordHealth);
	}
	@Override
	public void draw(Graphics g) {
		g.drawImage(playerImage.getImage(), x, y, RENDERING_SIZE, RENDERING_SIZE,null);
		lifeCount.draw(g, playerEntity.getHealth());
		swordCount.draw(g, playerEntity.getNbRemainingStrike());
	}

	public Point getPosition() {
		return new Point(x, y);
	}

	public int getPhysicalSize() {
		return (lifeCount.size + swordCount.size + 1) * TriforceGameImpl.GLOBAL_SPRITE_SIZE;
	}

	public void setPosition(Point pos) {
		x = pos.x;
		y = pos.y;
	}

}
