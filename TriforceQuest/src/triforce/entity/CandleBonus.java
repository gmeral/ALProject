package triforce.entity;

import gameframework.base.DrawableImage;

import java.awt.Canvas;

import triforce.weapon.Candle;
import triforce.weapon.TooManyItemsException;

public class CandleBonus extends AbstractHideable implements Bonus {

	public CandleBonus(Canvas canvas, int x, int y) {
		super(canvas, x, y, new DrawableImage("images/candle.gif", canvas));
	}

	@Override
	public boolean applyBonus(Player gs) {
		try {
			gs.addWeapon(new Candle());
		} catch (TooManyItemsException e) {
			return false;
		}
		return true;
	}

}
