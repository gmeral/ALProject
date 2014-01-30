package warriors.entity;

import gameframework.base.DrawableImage;

import java.awt.Canvas;

import warriors.weapon.Shield;
import warriors.weapon.TooManyItemsException;

public class ShieldBonus extends AbstractBonus {
	public ShieldBonus(Canvas canvas, int x, int y) {
		super(canvas, x, y, new DrawableImage("images/shield.gif", canvas)); //TODO image de bouclier
	}

	@Override
	public boolean applyBonus(GameSoldier gs) {
		try {
			gs.addWeapon(new Shield());
		} catch (TooManyItemsException e) {
			return false;
		}
		return true;
	}
	
	
}
