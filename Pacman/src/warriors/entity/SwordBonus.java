package warriors.entity;

import gameframework.base.DrawableImage;

import java.awt.Canvas;

import warriors.weapon.Sword;
import warriors.weapon.TooManyItemsException;

public class SwordBonus extends AbstractBonus {
	public SwordBonus(Canvas canvas, int x, int y) {
		super(canvas, x, y, new DrawableImage("images/superpacgum.gif", canvas)); //TODO image d'épée
	}
	

	@Override
	public boolean applyBonus(GameSoldier gs) {
		try {
			gs.addWeapon(new Sword());
		} catch (TooManyItemsException e) {
			return false;
		}
		return true;
	}
}
