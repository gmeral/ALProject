package triforce.entity.displaybar;

import gameframework.base.Drawable;
import gameframework.game.GameEntity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import triforce.customframework.TriforceGameImpl;
import triforce.entity.Player;

public class DisplayBarEntity implements GameEntity, Drawable{

	private List<PlayerBarEntity> playerBars;
	private Canvas canvas;
	private int x,y;

	public DisplayBarEntity(Canvas defaultCanvas){
		x = 0;
		y = (TriforceGameImpl.FRAME_NB_ROWS - 1) * TriforceGameImpl.GLOBAL_SPRITE_SIZE;
		playerBars = new ArrayList<PlayerBarEntity>();
		canvas = defaultCanvas;
	}

	public void addPlayer(Player player, String playerImagePath) {
		PlayerBarEntity p;
		if(playerBars.isEmpty())
			p = new PlayerBarEntity(canvas, playerImagePath, player, x, y);
		else {
			PlayerBarEntity previous = playerBars.get(playerBars.size()-1);
			int xPosition = previous.getPosition().x + previous.getPhysicalSize() + TriforceGameImpl.GLOBAL_SPRITE_SIZE;
			p = new PlayerBarEntity(canvas, playerImagePath, player,xPosition , y);
		}
		playerBars.add(p);
	}
	
	@Override
	public void draw(Graphics g) {
		Point pos = new Point(x, y);
	
		for(PlayerBarEntity pbe : playerBars) {
			pbe.setPosition(pos);
			pbe.draw(g);
			pos.translate(pbe.getPhysicalSize() + TriforceGameImpl.GLOBAL_SPRITE_SIZE, 0);
		}
	}

	public Point getPosition() {
		return new Point(x,y);
	}

}
