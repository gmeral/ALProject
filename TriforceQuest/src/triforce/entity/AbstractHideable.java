package triforce.entity;

import gameframework.base.DrawableImage;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import triforce.customframework.TriforceGameImpl;

public abstract class AbstractHideable implements Hideable{
	
	boolean visible=false;
	private DrawableImage image = null;
	protected DrawableImage hidden = null;
	
	protected int x, y;
	public static final int RENDERING_SIZE = TriforceGameImpl.GLOBAL_SPRITE_SIZE;
	
	
	public AbstractHideable(Canvas defaultCanvas, int xx, int yy, DrawableImage drawableImage) {
		x = xx;
		y = yy;
		image = drawableImage;
		hidden = new DrawableImage("images/black.gif", defaultCanvas);
	}


	@Override
	public void draw(Graphics g) {
		if(visible){
			g.drawImage(image.getImage(), x, y, RENDERING_SIZE, RENDERING_SIZE,null);
		}
		else{
			g.drawImage(hidden.getImage(), x, y, RENDERING_SIZE+1, RENDERING_SIZE+1,null);
		}
		hide();
	}
	
	public Rectangle getBoundingBox() {
		return (new Rectangle(x, y, RENDERING_SIZE, RENDERING_SIZE));
	}

	public void show(){
		visible = true;
	}
	
	public void hide(){
		visible = false;
	}
	
	public Point getPosition(){
		return new Point(x,y);
	}
}
