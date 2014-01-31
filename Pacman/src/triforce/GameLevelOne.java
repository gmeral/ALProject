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
import triforce.entity.Floor;
import triforce.entity.Ghost;
import triforce.entity.HolyGrailBonus;
import triforce.entity.Player;
import triforce.entity.ShieldBonus;
import triforce.entity.SwordBonus;
import triforce.entity.Wall;
import triforce.entity.displaybar.DisplayBarEntity;
import triforce.observers.PlayerObserver;
import triforce.rule.TriforceOverlapRules;

public class GameLevelOne extends GameLevelDefaultImpl {
	Canvas canvas;
	private static final int MINIMUM_DELAY_BETWEEN_GAME_CYCLES = 40;
	boolean stopGameLoop;
	List<GameMovable> targets = new ArrayList();
	// 0 : Pacgums; 1 : Walls; 2 : SuperPacgums; 3 : Doors; 4 : Jail; 5 : empty
	// Note: teleportation points are not indicated since they are defined by
	// directed pairs of positions.
	static int[][] tab = { 
		{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
		{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
		{ 1, 2, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 2, 1 },
		{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
		{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
		{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
		{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
		{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
		{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 3, 3, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 5, 0, 0, 5, 0, 5, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 1, 0, 0, 0, 0, 0, 5, 0, 0, 0, 1, 5, 0, 0, 4, 0, 5, 1, 0, 0, 0, 5, 0, 0, 0, 0, 0, 1 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 5, 0, 0, 5, 0, 5, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
		{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1 },
		{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
		{ 1, 0, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
		{ 1, 2, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 0, 0, 2, 1 },
		{ 1, 1, 1, 0, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1 },
		{ 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1 },
		{ 1, 0, 0, 0, 0, 0, 2, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 2, 0, 0, 0, 0, 0, 1 },
		{ 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1 },
		{ 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1 },
		{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
		{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	public static final int SPRITE_SIZE = TriforceGameImpl.GLOBAL_SPRITE_SIZE;

	@Override
	protected void init() {
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();

		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		moveBlockerChecker.setMoveBlockerRules(new MoveBlockerRulesApplierDefaultImpl());

		TriforceOverlapRules overlapRules = new TriforceOverlapRules(new Point(14 * SPRITE_SIZE, 17 * SPRITE_SIZE),
				new Point(14 * SPRITE_SIZE, 15 * SPRITE_SIZE), life[0], score[0], endOfGame);
		overlapProcessor.setOverlapRules(overlapRules);

		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		overlapRules.setUniverse(universe);

		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);

		// Filling up the universe with basic non movable entities and inclusion in the universe
		List<Ghost> ghosts= new ArrayList();
		for (int i = 0; i < TriforceGameImpl.FRAME_NB_ROWS-1; ++i) {
			for (int j = 0; j < TriforceGameImpl.FRAME_NB_COLUMNS-1; ++j) {
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
			}
		}
		for (Ghost g : ghosts){
			universe.addGameEntity(g);
		}
		
		PlayerObserver obs = new PlayerObserver(universe);

		
		Player player1 = new Player(canvas, "images/link.gif", obs, 26 * SPRITE_SIZE, 1 * SPRITE_SIZE, KeyEvent.VK_NUMPAD0  );
		GameMovableDriverDefaultImpl driver1 = new GameMovableDriverDefaultImpl();
		PlayerMoveStrategy keyStr = new PlayerMoveStrategy(KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
		driver1.setStrategy(keyStr);
		driver1.setmoveBlockerChecker(moveBlockerChecker);
		canvas.addKeyListener(keyStr);
		canvas.addKeyListener(player1);
		player1.setDriver(driver1);
		universe.addGameEntity(player1);
		targets.add(player1);
		
		Player player2 = new Player(canvas, "images/link2.gif",obs, 1 * SPRITE_SIZE, 1 * SPRITE_SIZE, KeyEvent.VK_SPACE);
		GameMovableDriverDefaultImpl driver2 = new GameMovableDriverDefaultImpl();
		PlayerMoveStrategy keyStrPlayer2 = new PlayerMoveStrategy(KeyEvent.VK_D, KeyEvent.VK_Q, KeyEvent.VK_Z, KeyEvent.VK_S);
		driver2.setStrategy(keyStrPlayer2);
		driver2.setmoveBlockerChecker(moveBlockerChecker);
		canvas.addKeyListener(keyStrPlayer2);
		canvas.addKeyListener(player2);
		player2.setDriver(driver2);
		universe.addGameEntity(player2);
		targets.add(player2);
		
		DisplayBarEntity displayBar = new DisplayBarEntity(canvas);
		displayBar.addPlayer(player1, "images/linkAvatar.gif");
		displayBar.addPlayer(player2, "images/link2Avatar.gif");
		universe.addGameEntity(displayBar);		
		
		obs.setTargets(targets);
	}
	public GameLevelOne(Game g) {
		super(g);
		canvas = g.getCanvas();
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
