package triforce.rule;

import gameframework.base.ObservableValue;
import gameframework.base.Overlap;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;

import java.awt.Point;
import java.util.Vector;

import triforce.SoundPlayer;
import triforce.entity.Bonus;
import triforce.entity.CandleBonus;
import triforce.entity.Floor;
import triforce.entity.Ghost;
import triforce.entity.HolyGrailBonus;
import triforce.entity.Player;
import triforce.entity.SafeZone;
import triforce.entity.ShieldBonus;
import triforce.entity.SwordBonus;

public class TriforceOverlapRules extends OverlapRulesApplierDefaultImpl {
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
	private Player winner;

	public TriforceOverlapRules(Point player1Pos, Point player2Pos,
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

	public void overlapRule(Player player,ShieldBonus bonus){
		if(bonus.applyBonus(player)) {
			SoundPlayer.BonusSound();
			player.setSpriteState("shield+");
			universe.removeGameEntity(bonus);
		}
	}
	
	public void overlapRule(Player player,CandleBonus bonus){
		if(bonus.applyBonus(player)) {
			SoundPlayer.BonusSound();
			universe.removeGameEntity(bonus);
		}
	}
	
	public void overlapRule(Player player,Ghost ghost){
		player.parry(ghost.getDamages());
		player.setInvincible(5);
	}
	
	public void overlapRule(Player player,SwordBonus bonus){
		if(bonus.applyBonus(player)) {
			SoundPlayer.BonusSound();
			universe.removeGameEntity(bonus);
		}
	}
	
	public void overlapRule(Player player,HolyGrailBonus grail) {
		winner = player;
		player.setSpriteState("win");
		universe.removeGameEntity(grail);
		SoundPlayer.WinSound();
		endOfGame.setValue(true);
	}
	
	public void overlapRule(Player player,Player player2) {
	}
	
	public void overlapRule(Player player, SafeZone zone) {
		player.setInvincible(5);
	}	
	
	public void overlapRule(Player player,Floor floor) {
	}
	
	public void overlapRule(Bonus bonus, Floor floor) {
	}

	public Player getWinner() {
		return winner;
	}

	
}