package triforce.entity.displaybar;

import gameframework.base.DrawableImage;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

import triforce.customframework.TriforceGameImpl;

public class AbstractGameContainer{

	protected DrawableImage displayUnit;
	protected DrawableImage emptyDisplayUnit;
	protected int x,y;
	protected int size;
	public static final int RENDERING_SIZE = TriforceGameImpl.GLOBAL_SPRITE_SIZE;

	protected AbstractGameContainer(Canvas defaultCanvas, int xx, int yy, int size, String unitImagePath, String unitEmptyPath) {
		this.size = size;
		displayUnit = new DrawableImage(unitImagePath, defaultCanvas);
		emptyDisplayUnit = new DrawableImage(unitEmptyPath, defaultCanvas);
		x = xx;
		y = yy;
	}

	public void draw(Graphics g, int count) {
		for(int i = 0 ; i < count ; i++) 
				g.drawImage(displayUnit.getImage(), x+i*RENDERING_SIZE, y, RENDERING_SIZE, RENDERING_SIZE,null);
		for(int i = count ; i < size ; i++)
				g.drawImage(emptyDisplayUnit.getImage(), x+i*RENDERING_SIZE, y, RENDERING_SIZE, RENDERING_SIZE,null);
	}

	public Point getPosition(){
		return new Point(x,y);
	}
	public int getSize(){
		return size;
	}
}
