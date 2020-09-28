import java.io.IOException;

public class SoloNoble {
	
	public static void main(String[] args) {
		try {
			Grille g = new Grille("./tablier.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
//test