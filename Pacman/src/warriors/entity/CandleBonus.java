package warriors.entity;

import gameframework.base.DrawableImage;

import java.awt.Canvas;

import warriors.weapon.Candle;
import warriors.weapon.TooManyItemsException;

public class CandleBonus extends AbstractBonus {

	public CandleBonus(Canvas canvas, int x, int y) {
		super(canvas, x, y, new DrawableImage("images/candle.gif", canvas));
	}

	@Override
	public boolean applyBonus(GameSoldier gs) {
		try {
			gs.addWeapon(new Candle());
		} catch (TooManyItemsException e) {
			return false;
		}
		return false;
	}

}
