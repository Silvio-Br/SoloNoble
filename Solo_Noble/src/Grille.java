import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Grille {

	/** tableau deux dimensions de booleen, true quand il y a une piece **/
	private String[][] tablier;
	
	/**
	 * constructeur d'une grille par defaut
	 */
	public Grille() {
		tablier = new String[][]{{" "," ","o","o","o"," "," "},
								 {" "," ","o","o","o"," "," "},
								 {"o","o","o","o","o","o","o"},
								 {"o","o","o",".","o","o","o"},
								 {"o","o","o","o","o","o","o"},
								 {" "," ","o","o","o"," "," "},
								 {" "," ","o","o","o"," "," "}};
	}
	
	/**
	 * constructeur d'une grille a partir d'un modele donne
	 * @param f
	 * 			chemin du fichier txt
	 * @throws IOException 
	 */
	public Grille(String fichier) throws IOException {
		convertirFichier(fichier);
	}
	
	public void convertirFichier(String fichier) throws IOException {
		int caractere;
		BufferedReader reader = new BufferedReader(new FileReader(fichier));
		
		/** get nombre de lignes **/
		int lignes = 0;
		while (reader.readLine() != null) lignes++;
		reader.close();
		
		/** get nombre de colonnes **/
		reader = new BufferedReader(new FileReader(fichier));
		int colonnes = reader.readLine().length();
		reader.close();
		
		/** attribution des valeurs au tablier **/
		reader = new BufferedReader(new FileReader(fichier));
		tablier = new String[lignes][colonnes];
		
		/** iterateurs **/
		int i = 0;
		int j = 0;
		int nbCarac = 0;
	
		while((caractere = reader.read()) != -1) {
			
			/** recupere un caractere de la ligne caractere **/
			String val = "";
		    val += (char) caractere;
		    nbCarac++;
		    
		    if (nbCarac <= colonnes) {
		    	 System.out.println("t[" + i + "][" + j  + "] = " + val);
		    	 if (nbCarac == colonnes) {
				    	i++;
				    	j = 0;
				    }
				    
				    if (i < colonnes) {
				    	j++;
				    }
				    
		    } else {
		    	nbCarac = 0;
		    	j = 0;
		    }
		    
		}
		reader.close();
	}
	
}
