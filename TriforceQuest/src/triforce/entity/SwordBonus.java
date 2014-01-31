package triforce.entity;

import gameframework.base.DrawableImage;

import java.awt.Canvas;

import triforce.weapon.Sword;
import triforce.weapon.TooManyItemsException;

public class SwordBonus extends AbstractHideable implements Bonus {
	public SwordBonus(Canvas canvas, int x, int y) {
		super(canvas, x, y, new DrawableImage("images/sword.gif", canvas));
	}
	

	@Override
	public boolean applyBonus(Player gs) {
		try {
			gs.addWeapon(new Sword());
		} catch (TooManyItemsException e) {
			return false;
		}
		return true;
	}
}
