package triforce;

import gameframework.game.CanvasDefaultImpl;
import gameframework.game.Game;
import gameframework.game.GameLevelDefaultImpl;
import gameframework.game.GameMovable;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverseDefaultImpl;
import gameframework.game.GameUniverseViewPortDefaultImpl;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;
import gameframework.game.MoveBlockerRulesApplierDefaultImpl;
import gameframework.game.OverlapProcessor;
import gameframework.game.OverlapProcessorDefaultImpl;

import java.awt.Canvas;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import triforce.customframework.GhostMovableDriver;
import triforce.customframework.MoveStrategyRandom;
import triforce.customframework.PlayerMoveStrategy;
import triforce.customframework.TriforceGameImpl;
import triforce.entity.CandleBonus;
import triforce.entity.Floor;
import triforce.entity.Ghost;
import triforce.entity.HolyGrailBonus;
import triforce.entity.Player;
import triforce.entity.SafeZone;
import triforce.entity.ShieldBonus;
import triforce.entity.SwordBonus;
import triforce.entity.Wall;
import triforce.entity.displaybar.DisplayBarEntity;
import triforce.observers.PlayerObserver;
import triforce.rule.TriforceOverlapRules;

public abstract class AbstractTriforceLevel extends GameLevelDefaultImpl  {

	private static final int MINIMUM_DELAY_BETWEEN_GAME_CYCLES = 40;
	public static final int SPRITE_SIZE = TriforceGameImpl.GLOBAL_SPRITE_SIZE;
	boolean stopGameLoop;

	/* framework */
	protected Canvas canvas;
	OverlapProcessor overlapProcessor;
	MoveBlockerChecker moveBlockerChecker;
	TriforceOverlapRules overlapRules;

	/*triforce game special*/
	PlayerObserver obs ;
	List<GameMovable> targets = new ArrayList();
	Player player1;
	Player player2;

	public AbstractTriforceLevel(Game g) {
		super(g);
	}

	protected void initVariables(){
		overlapProcessor = new OverlapProcessorDefaultImpl();

		moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		moveBlockerChecker.setMoveBlockerRules(new MoveBlockerRulesApplierDefaultImpl());

		overlapRules = new TriforceOverlapRules(new Point(14 * SPRITE_SIZE, 17 * SPRITE_SIZE),
				new Point(14 * SPRITE_SIZE, 15 * SPRITE_SIZE), life[0], score[0], endOfGame);
		overlapProcessor.setOverlapRules(overlapRules);

		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		overlapRules.setUniverse(universe);

		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);
	}

	protected void initPlayers(int xP1, int yP1, int xP2, int yP2) {

		obs = new PlayerObserver(universe);

		player1 = new Player(canvas, "images/link.gif", obs, xP1 * SPRITE_SIZE, yP1 * SPRITE_SIZE, KeyEvent.VK_M  );
		GameMovableDriverDefaultImpl driver1 = new GameMovableDriverDefaultImpl();
		PlayerMoveStrategy keyStr = new PlayerMoveStrategy(KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
		driver1.setStrategy(keyStr);
		driver1.setmoveBlockerChecker(moveBlockerChecker);
		canvas.addKeyListener(keyStr);
		canvas.addKeyListener(player1);
		player1.setDriver(driver1);
		targets.add(player1);

		player2 = new Player(canvas, "images/link2.gif",obs, xP2 * SPRITE_SIZE, yP2 * SPRITE_SIZE, KeyEvent.VK_SPACE);
		GameMovableDriverDefaultImpl driver2 = new GameMovableDriverDefaultImpl();
		PlayerMoveStrategy keyStrPlayer2 = new PlayerMoveStrategy(KeyEvent.VK_D, KeyEvent.VK_Q, KeyEvent.VK_Z, KeyEvent.VK_S);
		driver2.setStrategy(keyStrPlayer2);
		driver2.setmoveBlockerChecker(moveBlockerChecker);
		canvas.addKeyListener(keyStrPlayer2);
		canvas.addKeyListener(player2);
		player2.setDriver(driver2);
		targets.add(player2);

		obs.setTargets(targets);
		universe.addGameEntity(player1);
		universe.addGameEntity(player2);

	}

	protected void initDisplayBar(){
		DisplayBarEntity displayBar = new DisplayBarEntity(canvas);
		displayBar.addPlayer(player1, "images/linkAvatar.gif");
		displayBar.addPlayer(player2, "images/link2Avatar.gif");
		universe.addGameEntity(displayBar);	
	}

	public void readMap(int[][] tab){
		List<Ghost> ghosts= new ArrayList();
		for (int i = 0; i < TriforceGameImpl.FRAME_NB_ROWS-1; ++i) {
			for (int j = 0; j < TriforceGameImpl.FRAME_NB_COLUMNS; ++j) {
				if (tab[i][j] == 0) {
					universe.addGameEntity(new Floor(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
				}
				if (tab[i][j] == 1) {
					universe.addGameEntity(new Wall(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
				}
				if (tab[i][j] == 2) {
					universe.addGameEntity(new Floor(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
					universe.addGameEntity(new SwordBonus(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
				}
				if (tab[i][j] == 3) {
					universe.addGameEntity(new Floor(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
					universe.addGameEntity(new ShieldBonus(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
				}
				if (tab[i][j] == 4) {
					universe.addGameEntity(new Floor(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
					universe.addGameEntity(new HolyGrailBonus(canvas,j * SPRITE_SIZE, i * SPRITE_SIZE));
				}
				if (tab[i][j] == 5) {
					universe.addGameEntity(new Floor(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
					Ghost myGhost;
					GameMovableDriverDefaultImpl ghostDriv = new GhostMovableDriver();
					MoveStrategyRandom ranStr = new MoveStrategyRandom();
					ghostDriv.setStrategy(ranStr);
					ghostDriv.setmoveBlockerChecker(moveBlockerChecker);
					myGhost = new Ghost(canvas,j * SPRITE_SIZE, i * SPRITE_SIZE);
					myGhost.setDriver(ghostDriv);
					ghosts.add(myGhost);
					targets.add(myGhost);
				}
				if (tab[i][j] == 6) {
					universe.addGameEntity(new SafeZone(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
				}		
				if (tab[i][j] == 7) {
					universe.addGameEntity(new Floor(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
					universe.addGameEntity(new CandleBonus(canvas,j * SPRITE_SIZE, i * SPRITE_SIZE));
				}
			}
		}

		for (Ghost g : ghosts){
			universe.addGameEntity(g);
		}
	}

	@Override
	public void run() {
		stopGameLoop = false;
		// main game loop :
		long start;
		while (!stopGameLoop && !this.isInterrupted()) {
			start = new Date().getTime();
			gameBoard.paint();
			universe.allOneStepMoves();
			universe.processAllOverlaps();
			try {
				long sleepTime = MINIMUM_DELAY_BETWEEN_GAME_CYCLES
						- (new Date().getTime() - start);
				if (sleepTime > 0) {
					Thread.sleep(sleepTime);
				}
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void end() {
		universe.allOneStepMoves();
		gameBoard.paint();
		stopGameLoop = true;
		super.end();
	}
}
