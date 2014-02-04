package triforce.customframework;

import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerMoveStrategy extends KeyAdapter implements MoveStrategy {
	protected SpeedVector speedVector = new SpeedVectorDefaultImpl(new Point(0,1),0);
	private static final int DEFAULT_SPEED = TriforceGameImpl.GLOBAL_SPRITE_SIZE/2;
	private final int  right, left,up, down;
	private int nbKeyPressed;


	public SpeedVector getSpeedVector() {
		return speedVector;
	}

	public PlayerMoveStrategy(int rightKey, int leftKey, int upKey, int downKey) {
		right = rightKey;
		left = leftKey;
		up = upKey;
		down = downKey;
		nbKeyPressed = 0;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int keycode = event.getKeyCode();
		if (keycode == right) {
			speedVector.setDirection(new Point(1, 0));
			speedVector.setSpeed(DEFAULT_SPEED);
			nbKeyPressed++;
		}
		if (keycode == left) {
			speedVector.setDirection(new Point(-1, 0));
			speedVector.setSpeed(DEFAULT_SPEED);
			nbKeyPressed++;
		}
		if (keycode == up) {
			speedVector.setDirection(new Point(0, -1));
			speedVector.setSpeed(DEFAULT_SPEED);
			nbKeyPressed++;
		}
		if (keycode == down) {
			speedVector.setDirection(new Point(0, 1));
			speedVector.setSpeed(DEFAULT_SPEED);
			nbKeyPressed++;
		}
		
	}


	@Override
	public void keyReleased(KeyEvent event) {
		int keycode = event.getKeyCode();
		if (keycode == right || keycode == left || keycode == up || keycode == down) {
			nbKeyPressed--;
			if(nbKeyPressed == 0)
				speedVector.setSpeed(0);
		}
	}
}
