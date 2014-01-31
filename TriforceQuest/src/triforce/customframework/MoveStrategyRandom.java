package triforce.customframework;

import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

import java.awt.Point;
import java.util.Random;

/**
 * MoveStrategy which randomly selects one of the four directions (top, bottom,
 * left, right)
 */
public class MoveStrategyRandom implements MoveStrategy {
	SpeedVector currentMove = new SpeedVectorDefaultImpl(new Point(0, 0),2);
	static Random random = new Random(System.currentTimeMillis());
	private int movesStraight = 3;
	

	public SpeedVector getSpeedVector() {
		int i =0;
//		if (movesStraight == 0){
			i = random.nextInt(5);
//			movesStraight = 3;
//		}
		switch (i) {
		case 0:
			currentMove.setDirection(new Point(1, 0));
			break;
		case 1:
			currentMove.setDirection(new Point(-1, 0));
			break;
		case 2:
			currentMove.setDirection(new Point(0, -1));
			break;
		case 3:
			currentMove.setDirection(new Point(0, 1));
			break;
		}
//		movesStraight--;
		return currentMove;
	}
}
