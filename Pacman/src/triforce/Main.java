package triforce;

import gameframework.game.GameLevel;

import java.util.ArrayList;

import triforce.customframework.TriforceGameImpl;

public class Main {
	public static void main(String[] args) {
		TriforceGameImpl g = new TriforceGameImpl(); //redefine game size
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();

		levels.add(new GameLevelTwo(g));
		levels.add(new GameLevelOne(g));
		
		g.setLevels(levels);
		g.start();
	}
}
