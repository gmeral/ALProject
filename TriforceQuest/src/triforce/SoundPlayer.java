package triforce;

public class SoundPlayer {

	public static void HitSound() {
		Sound s = new Sound("sounds/LOZ_Sword.wav");
		s.play();
	}

	public static void ShieldBlockSound() {
		Sound s = new Sound("sounds/LOZ_Shield.wav");
		s.play();
	}

	public static void DeathSound() {
		Sound s = new Sound("sounds/LOZ_Die.wav");
		s.play();
	}

	public static void TriforceNearSound(boolean on) {
		//TODO son de "scintillement"
	}

	public static void WinSound() {
		Sound s = new Sound("sounds/win.wav");
		s.play();
	}

	public static void BonusSound() {
		Sound s = new Sound("sounds/LOZ_Get_Item.wav");
		s.play();
	}

	public static void HurtSound() {
		Sound s = new Sound("sounds/LOZ_Hurt.wav");
		s.play();
	}

	public static void BrokenSwordSound() {
		// TODO Auto-generated method stub
		
	}

	public static void BrokenShieldSound() {
		// TODO Auto-generated method stub
		
	}
	
	public static void KillSound(){
		Sound s = new Sound("sounds/LOZ_Kill.wav");
		s.play();
	}

}
