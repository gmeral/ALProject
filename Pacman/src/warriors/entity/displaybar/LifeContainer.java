package warriors.entity.displaybar;

import java.awt.Canvas;

public class LifeContainer  extends AbstractGameContainer{

	protected LifeContainer(Canvas defaultCanvas, int xx, int yy, int size) {
		super(defaultCanvas, xx, yy, size, "images/Life.gif", "images/LifeEmpty.png");
	}
	//TODO Ajouter le bon observer
	//TODO Image de coeur plein
	//TODO Image de coeur vide
}
