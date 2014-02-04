package triforce;

import java.util.HashMap;
import java.util.Map;

public class SoundPlayer {
//TODO Eviter que le jeu plante quand on arrive pas à jouer un son.
	//TODO Cette classe doit etre un singleton qui instancie tout les sons au lancement du jeu
	private static SoundPlayer instance;
	private Map<String, Sound> sounds;
	
	private SoundPlayer() {
		sounds = new HashMap<String,Sound>();
		sounds.put("hit", new Sound("sounds/LOZ_Sword.wav"));
		sounds.put("shieldBlock",new Sound("sounds/LOZ_Shield.wav"));
		sounds.put("death", new Sound("sounds/LOZ_Die.wav"));
		sounds.put("win", new Sound("sounds/LA_Fanfare_Item_Extended.wav"));
		sounds.put("bonus",	new Sound("sounds/LOZ_Get_Item.wav"));
		sounds.put("hurt", new Sound("sounds/LOZ_Hurt.wav"));
		sounds.put("kill", new Sound("sounds/LOZ_Kill.wav"));
		sounds.put("gameTheme", new Sound("sounds/background.wav"));
	}
	
	public static SoundPlayer getInstance() {
		if(instance == null)
			instance = new SoundPlayer();
		return instance;
	}
	
	
	public void HitSound() {
		sounds.get("hit").play();
	}

	public  void ShieldBlockSound() {
		sounds.get("shieldBlock").play();
	}

	public  void DeathSound() {
		sounds.get("death").play();
	}

	public  void TriforceNearSound(boolean on) {
		//TODO son de "scintillement"
	}

	public  void WinSound() {
		sounds.get("win").play();
	}

	public  void BonusSound() {
		sounds.get("bonus").play();
	}

	public  void HurtSound() {
		System.out.println("hurt");
		sounds.get("hurt").play();
	}

	public  void BrokenSwordSound() {
		// TODO Auto-generated method stub
		
	}

	public  void BrokenShieldSound() {
		// TODO Auto-generated method stub
		
	}
	
	public  void KillSound(){
		sounds.get("kill").play();
	}

	public void gameTheme(){
		sounds.get("gameTheme").loop();
	}
}
