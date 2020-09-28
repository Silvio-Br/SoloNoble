import java.io.IOException;

public class SoloNoble {
	
	public static void main(String[] args) {
		try {
			Grille g = new Grille("/Users/silviobrancati/Documents/IUT/2020_2021/Algo/TP_Solo_Noble/SoloNoble/tablier.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
//test