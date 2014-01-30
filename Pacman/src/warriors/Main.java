package warriors;

import gameframework.game.GameLevel;

import java.util.ArrayList;

import warriors.customframework.WarriorsGameImpl;

public class Main {
	public static void main(String[] args) {
		WarriorsGameImpl g = new WarriorsGameImpl(); //redefine game size
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();

		levels.add(new GameLevelTwo(g));
		levels.add(new GameLevelOne(g));
		
		g.setLevels(levels);
		g.start();
	}
}
