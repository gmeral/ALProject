package warriors.entity.displaybar;

import gameframework.base.Drawable;
import gameframework.game.GameEntity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import warriors.customframework.WarriorsGameImpl;
import warriors.entity.GameSoldier;

public class DisplayBarEntity implements GameEntity, Drawable{

	private List<PlayerBarEntity> playerBars;
	private Canvas canvas;
	private int x,y;

	public DisplayBarEntity(Canvas defaultCanvas){
		x = 0;
		y = (WarriorsGameImpl.FRAME_NB_ROWS - 1) * WarriorsGameImpl.GLOBAL_SPRITE_SIZE;
		playerBars = new ArrayList<PlayerBarEntity>();
		canvas = defaultCanvas;
	}

	public void addPlayer(GameSoldier player, String playerImagePath) {
		PlayerBarEntity p;
		if(playerBars.isEmpty())
			p = new PlayerBarEntity(canvas, playerImagePath, player, x, y);
		else {
			PlayerBarEntity previous = playerBars.get(playerBars.size()-1);
			int xPosition = previous.getPosition().x + previous.getPhysicalSize() + WarriorsGameImpl.GLOBAL_SPRITE_SIZE;
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
			pos.translate(pbe.getPhysicalSize() + WarriorsGameImpl.GLOBAL_SPRITE_SIZE, 0);
		}
	}

	public Point getPosition() {
		return new Point(x,y);
	}

}
