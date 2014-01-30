package warriors.rule;

import gameframework.base.ObservableValue;
import gameframework.base.Overlap;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;

import java.awt.Point;
import java.util.Vector;

import warriors.entity.AbstractBonus;
import warriors.entity.Floor;
import warriors.entity.GameSoldier;
import warriors.entity.Ghost;
import warriors.entity.HolyGrailBonus;
import warriors.entity.ShieldBonus;
import warriors.entity.SwordBonus;

public class WarriorsOverlapRules extends OverlapRulesApplierDefaultImpl {
	protected GameUniverse universe;

	// Time duration during which pacman is invulnerable and during which ghosts
	// can be eaten (in number of cycles)
	static final int INVULNERABLE_DURATION = 60;
	protected Point player1StartPos;
	protected Point player2StartPos;
	protected boolean managePacmanDeath;
	private final ObservableValue<Integer> score;
	private final ObservableValue<Integer> life;
	private final ObservableValue<Boolean> endOfGame;
	private GameSoldier winner;

	public WarriorsOverlapRules(Point player1Pos, Point player2Pos,
			ObservableValue<Integer> life, ObservableValue<Integer> score,
			ObservableValue<Boolean> end) {
		player1StartPos = (Point) player1Pos.clone();
		player2StartPos = (Point) player2Pos.clone();
		this.life = life;
		this.score = score;
		this.endOfGame = end;
	}

	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}

	@Override
	public void applyOverlapRules(Vector<Overlap> overlappables) {
		managePacmanDeath = true;
		super.applyOverlapRules(overlappables);
	}
	
	public void overlapRule(GameSoldier player,ShieldBonus bonus){
		if(bonus.applyBonus(player)) {
			player.setSpriteState("shield+");
			universe.removeGameEntity(bonus);
		}
	}
	
	public void overlapRule(GameSoldier player,Ghost ghost){
		player.parry(ghost.getDamages());
		player.setInvincible(5);
	}
	
	public void overlapRule(GameSoldier player,SwordBonus bonus){
		if(bonus.applyBonus(player)) {
			universe.removeGameEntity(bonus);
		}
	}
	
	public void overlapRule(GameSoldier player,HolyGrailBonus grail) {
		winner = player;
		player.setSpriteState("win");
		universe.removeGameEntity(grail);
		endOfGame.setValue(true);
	}
	
	public void overlapRule(GameSoldier player,GameSoldier player2) {
	}
	
	public void overlapRule(GameSoldier player,Floor floor) {
	}
	
	public void overlapRule(AbstractBonus bonus, Floor floor) {
	}

	public GameSoldier getWinner() {
		return winner;
	}

	
}