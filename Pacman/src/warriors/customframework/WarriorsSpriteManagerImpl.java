package warriors.customframework;

import gameframework.base.DrawableImage;
import gameframework.game.SpriteManager;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

/**
 * This implementation of {@link SpriteManager} assumes that sprite types are in
 * rows whereas increments of a type are in columns
 * 
 */
public class WarriorsSpriteManagerImpl implements SpriteManager {

	private final DrawableImage image;
	private Map<String, Integer> types;
	private int spriteNumber = 0;
	private final int[] spriteRows;
	private int currentRow;
	private final int renderingSize;
	private final int spriteSize;
	private final Rectangle boundingBox;

	public WarriorsSpriteManagerImpl(String filename, Canvas canvas, int renderingSize,
			int spriteSize, int[] spriteRows, Rectangle boundingBox) {
		this.renderingSize = renderingSize;
		this.spriteSize = spriteSize;
		this.spriteRows = spriteRows;
		this.boundingBox = boundingBox;
		image = new DrawableImage(filename, canvas);
	}

	@Override
	public void setTypes(String... types) {
		int i = 0;
		this.types = new HashMap<String, Integer>(types.length);
		for (String type : types) {
			this.types.put(type, i);
			i++;
		}
	}

	@Override
	public void draw(Graphics g, Point position) {
		// Destination image coordinates
		int dx1 = (int) position.getX() - (int) boundingBox.getX();
		int dy1 = (int) position.getY() - (int) boundingBox.getY();
		int dx2 = dx1 + renderingSize*3;
		int dy2 = dy1 + renderingSize*3;

		// Source image coordinates
		int sx1 = spriteNumber * spriteSize*3;
		int sy1 = currentRow * spriteSize*3;
		int sx2 = sx1 + spriteSize*3;
		int sy2 = sy1 + spriteSize*3;
		g.drawImage(image.getImage(), dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
	}

	@Override
	public void setType(String type) {
		if (!types.containsKey(type)) {
			throw new IllegalArgumentException(type
					+ " is not a valid type for this sprite manager.");
		}
		this.currentRow = types.get(type);
	}

	@Override
	public void increment() {
		spriteNumber = (spriteNumber + 1) % spriteRows[currentRow];
	}

	@Override
	public void reset() {
		spriteNumber = 0;
	}

	@Override
	public void setIncrement(int increment) {
		this.spriteNumber = increment;
	}

	public int getCurrentRow() {
		return currentRow;
	}
}
