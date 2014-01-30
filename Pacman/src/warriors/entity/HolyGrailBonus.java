package warriors.entity;

import gameframework.base.DrawableImage;
import gameframework.game.SpriteManager;
import gameframework.game.SpriteManagerDefaultImpl;

import java.awt.Canvas;
import java.awt.Graphics;

public class HolyGrailBonus extends AbstractBonus {

	protected final SpriteManager spriteManager;
	
	
	public HolyGrailBonus(Canvas canvas, int x, int y) {
		super(canvas, x, y, new DrawableImage("images/triforce.gif", canvas));
		spriteManager = new SpriteManagerDefaultImpl("images/triforce.gif", canvas, RENDERING_SIZE, 2);
		spriteManager.setTypes("unique");
		spriteManager.setType("unique");
	}
	
	@Override
	public void draw(Graphics g) {
		if(visible){
			spriteManager.increment();
			spriteManager.draw(g, getPosition());
		}
		else{
			spriteManager.increment();
			g.drawImage(hidden.getImage(), x, y, RENDERING_SIZE+1, RENDERING_SIZE+1,null);
		}
		hide();
	}
	

	@Override
	public boolean applyBonus(GameSoldier gs) {
		return true;
	}
}
