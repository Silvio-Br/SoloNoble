import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
	
	/**
	 * methode convertissant le fichier txt en tablier
	 * @param fichier
	 * 					chemin du fichier txt
	 * @throws IOException
	 */
	public void convertirFichier(String fichier) throws IOException {
		
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
		
		/** variable contenant le code du caractere **/
		int caractere;
	
		/** limite iteration en fonction de l'os **/
		int limiteColonne;
		
		/** si os est windows la boucle for de l ira jusque colonnes+1 sinon limiteColonne = colonnes**/
		if(System.getProperty("os.name").equals("Windows 10")) {
			limiteColonne = colonnes +1;
		}
		else {
			limiteColonne = colonnes;
		}
		
		/** parcours caracteres par caracteres **/
		for (int k = 0; k < lignes; k++) {
			for (int l = 0; l < limiteColonne; l++) {
				/** recupere la valeur du caractere **/
				caractere = reader.read();
				String val = "";
				val += (char) caractere;
				
				/** insere dans tablier si dans les bornes **/
				if(l != colonnes) {				
					tablier[k][l] = val;					
				}
			}
			/** passe le caractere de retour à la ligne **/
			caractere = reader.read();
		}
		reader.close();
	}
	
	/**
	 * methode calculant les mouvements possibles dans les quatres directions 
	 * @return
	 */
	public ArrayList<Deplacement> calculerMouvementPossibles(){
		ArrayList<Deplacement> listeMouv = new ArrayList<Deplacement>();
		
		for(int i=0; i< tablier.length; i++) {
			for(int j=0; j< tablier[i].length; j++) {
				//deplacement possible vers la droite
				if(j+2 < tablier[i].length 
						&& tablier[i][j].equals("o")
						&& tablier[i][j+1].equals("o")
						&& tablier[i][j+2].equals(".")) {
					listeMouv.add(new Deplacement(i, j, i, j+2));
				}
				
				//deplacement possible vers la gauche
				if(j-2 >= 0
						&& tablier[i][j].equals("o")
						&& tablier[i][j-1].equals("o")
						&& tablier[i][j-2].equals(".")) {
					listeMouv.add(new Deplacement(i, j, i, j-2));
				}
				
				//deplacement possible vers le haut
				if(i-2 >= 0
						&& tablier[i][j].equals("o")
						&& tablier[i-1][j].equals("o")
						&& tablier[i-2][j].equals(".")) {
					listeMouv.add(new Deplacement(i, j, i-2, j));
				}
				
				//deplacement possible vers le bas
				if(i+2 < tablier.length 
						&& tablier[i][j].equals("o")
						&& tablier[i+1][j].equals("o")
						&& tablier[i+2][j].equals(".")) {
					listeMouv.add(new Deplacement(i, j, i+2, j));
				}		
			}
		}
		return listeMouv;
	}
	
	/**
	 * methode effectuant le deplacement 
	 * @param dep
	 * 				deplacement à effectuer 
	 */
	public void effectuerDeplacement(Deplacement dep) {
		deplacer(dep, false);
	}
	
	/**
	 * methode annulant le deplacement 
	 * @param dep
	 * 				deplacement à effectuer
	 */
	public void annulerDeplacement(Deplacement dep) {
		deplacer(dep, true);
	}
	
	/**
	 * methode deplacant la piece 
	 * @param dep
	 * 				deplacement à faire
	 * @param annuler
	 * 				true quand annulation de deplacement
	 */
	private void deplacer(Deplacement dep, boolean annuler) {
		String s1 = ".";
		String s2 = "o";
		if(annuler) {
			s1 = "o";
			s2 = ".";
		}	
		tablier[dep.getLigneA()][dep.getColD()] = s1;
		tablier[dep.getLigneA()][dep.getColA()] = s2;
		
		if(dep.getLigneD() == dep.getLigneA()) {
			tablier[dep.getLigneD()][Math.min(dep.getColD(), dep.getColA())+1] = s1;
		}
		else {
			tablier[Math.min(dep.getLigneD(), dep.getLigneA())+1][dep.getColA()] = s1;	
		}
	}
	
	/**
	 * calcule le nb de piece dans le tablier
	 * @return nb nombre de piece
	 */
	public int calculerNbPiece() {
		int nb = 0;
		for(int i=0; i<tablier.length; i++) {
			for(int j=0; j<tablier[i].length; j++) {
				if(tablier[i][j].equals("o")) {
					nb++;
				}
			}
		}
		return nb;
	}
	
	/**
	 * methode clone une grille
	 * @return grille clonee 
	 */
	public Grille clone() {
		Grille res = new Grille();
		res.tablier = this.tablier.clone();
		return res;
	}

	/**
	 * getteur de tablier
	 * @return le tablier
	 */
	public String[][] getTablier() {
		return tablier;
	}
	
	@Override
	public String toString() {
		String res = "";
		for(int i=0; i<tablier.length; i++) {
			for(String s:tablier[i]) {
				res += s;
			}
			res += "\n";
		}
		
		
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
}
