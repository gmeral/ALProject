package warriors.customframework;

import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WarriorMoveStrategy extends KeyAdapter implements MoveStrategy {
	protected SpeedVector speedVector = new SpeedVectorDefaultImpl(new Point(0,1),0);
	private static final int DEFAULT_SPEED = 8;
	private final int  right, left,up, down;


	public SpeedVector getSpeedVector() {
		return speedVector;
	}

	public WarriorMoveStrategy(int rightKey, int leftKey, int upKey, int downKey) {
		right = rightKey;
		left = leftKey;
		up = upKey;
		down = downKey;
	}

	@Override
	public void keyPressed(KeyEvent event) {

		int keycode = event.getKeyCode();
		if (keycode == right) {
			speedVector.setDirection(new Point(1, 0));
			speedVector.setSpeed(DEFAULT_SPEED);
		}
		if (keycode == left) {
			speedVector.setDirection(new Point(-1, 0));
			speedVector.setSpeed(DEFAULT_SPEED);
		}
		if (keycode == up) {
			speedVector.setDirection(new Point(0, -1));
			speedVector.setSpeed(DEFAULT_SPEED);
		}
		if (keycode == down) {
			speedVector.setDirection(new Point(0, 1));
			speedVector.setSpeed(DEFAULT_SPEED);
		}
	}


	@Override
	public void keyReleased(KeyEvent event) {
		int keycode = event.getKeyCode();
		if (keycode == right || keycode == left || keycode == up || keycode == down) {
			speedVector.setSpeed(0);
		}
	}
}
