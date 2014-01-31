package triforce.entity;

import gameframework.base.DrawableImage;

import java.awt.Canvas;

import triforce.weapon.Shield;
import triforce.weapon.TooManyItemsException;

public class ShieldBonus extends AbstractHideable implements Bonus  {
	public ShieldBonus(Canvas canvas, int x, int y) {
		super(canvas, x, y, new DrawableImage("images/shield.gif", canvas));
	}

	@Override
	public boolean applyBonus(Player gs) {
		try {
			gs.addWeapon(new Shield());
		} catch (TooManyItemsException e) {
			return false;
		}
		return true;
	}
	
	
}
