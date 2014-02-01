package triforce;

import gameframework.game.GameLevel;

import java.util.ArrayList;

import triforce.customframework.TriforceGameImpl;

public class Main {
	public static void main(String[] args) {
		Sound gameTheme = new Sound("sounds/background.wav");
		TriforceGameImpl g = new TriforceGameImpl(); //redefine game size
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();

		levels.add(new GameLevelOne(g));
		levels.add(new GameLevelTwo(g));
		
		g.setLevels(levels);
		gameTheme.loop();
		g.start();
	}
}
