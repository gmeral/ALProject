package triforce;

import gameframework.game.GameLevel;

import java.util.ArrayList;

import triforce.customframework.TriforceGameImpl;

public class Main {
	public static void main(String[] args) {
		
		TriforceGameImpl g = new TriforceGameImpl(); //redefine game size
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();
		SoundPlayer soundPlayer = SoundPlayer.getInstance();

		levels.add(new GameLevelOne(g));
		levels.add(new GameLevelTwo(g));
		
		g.setLevels(levels);
		soundPlayer.gameTheme();
		g.start();
	}
}
