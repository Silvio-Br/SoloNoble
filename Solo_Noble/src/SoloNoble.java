import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//"./Solo_Noble/tablier.txt"
public class SoloNoble {
	
	private Grille g;
	private List<Deplacement> solution;
	private int nbAppelRecursif;
	private Grille grilleDepart;
	
	public static void main(String[] args) throws IOException {
		/** creation chronometre jusqu'a la fin du programme **/
		long chrono = java.lang.System.currentTimeMillis();
		
		SoloNoble solo;
		/** si un argument est present considere comme fichier txt contenant une grille **/
		if (args.length > 0) {
			solo = new SoloNoble(args[0]);
		} else {
			/** sinon on creer une grille par defaut **/
			solo = new SoloNoble(null);
		}
		
		/** lancement de la resolution du solo noble **/
		solo.resoudreSoloNoble(solo.g.calculerNbPiece());
		solo.afficherSolution();
		
		/** fin du chronometre et affichage **/
		long chrono2 = java.lang.System.currentTimeMillis() ;
		long temps = chrono2 - chrono ;
		System.out.println("*** Temps ecoule = " + temps + " ms ***") ;
	}

	/**
	 * constructeur de SoloNoble
	 * @param f
	 * 			chemin du fichier si null tablier par defaut utilise 
	 * @throws IOException
	 */
	public SoloNoble(String f) throws IOException {
		if (f != null) {
			g = new Grille(f);
			/** copie de la grille pour l'affichage de la solution **/
			grilleDepart = new Grille(f);
		} else {
			g = new Grille();
			/** copie de la grille pour l'affichage de la solution **/
			grilleDepart = new Grille();
		}
		nbAppelRecursif = 0;
		solution = new ArrayList<Deplacement>();
	}
	
	/**
	 * methode resoud le jeu
	 * @param billes
	 * 				nb de pieces dans la grille
	 * @return
	 */
	public boolean resoudreSoloNoble(int billes) {
		nbAppelRecursif++;
		boolean bloque;
		if(billes == 1) {
			bloque = false;
		}
		else {
			bloque = true;
			ArrayList<Deplacement> listeDepPossible = g.calculerMouvementPossibles();
			int i = 0;
			while(i < listeDepPossible.size() && bloque) {
				//deplacement de la bille
				g.effectuerDeplacement(listeDepPossible.get(i));
				boolean acceptable = !(g.calculerMouvementPossibles().size() == 0
						&& billes != 2);
				if(acceptable) {
					//enregistrement deplacement
					solution.add(listeDepPossible.get(i));
					bloque = resoudreSoloNoble(billes-1);
					if(bloque) {
						//on annule l'enregistrement
						solution.remove(solution.size()-1);
						g.annulerDeplacement(listeDepPossible.get(i));
					}
				}
				else {
					g.annulerDeplacement(listeDepPossible.get(i));
				}
				i++;
			}
		}
		return bloque;
	}
	
	/**
	 * methode affichant la solution dans la console
	 */
	public void afficherLogSolution() {
		for(Deplacement i:solution) {
			System.out.println(i);
		}
		System.out.println("*** Nombre appel recursif: "+nbAppelRecursif+" ***");
	}
	
	public void afficherSolution() {
		int i=1;
		System.out.println("Grille de depart : " + "\n" + grilleDepart);
		System.out.println("[SOLUTION]");
		for(Deplacement d:solution) {
			grilleDepart.effectuerDeplacement(d);
			System.out.println("Etape i :"+i+"   "+ d);
			System.out.println("\n"+grilleDepart);
			i++;
		}
		System.out.println("*** Nombre d'appels recursifs : "+nbAppelRecursif+" ***");
		if(i==1) {
			System.out.println("Aucune solution trouvé");
		}
	}

}








