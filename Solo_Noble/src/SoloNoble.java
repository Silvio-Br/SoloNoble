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
		SoloNoble solo;
		if (args.length > 0) {
			solo = new SoloNoble(args[0]);
		} else {
			solo = new SoloNoble(null);
		}
		System.out.println(solo.g.toString());
		//solo.grilleDepart = solo.g.clone();	
		System.out.println(solo.grilleDepart);
		solo.nbAppelRecursif = 0;
		solo.resoudreSoloNoble(solo.g.calculerNbPiece());
		//solo.afficherLogSolution();
		solo.afficherSolution();
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
			grilleDepart = new Grille(f);
		} else {
			g = new Grille();
			grilleDepart = new Grille();
		}
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
					}
				}
				else {
					//annulation du deplacement si resultat non acceptable
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
		System.out.println(grilleDepart);
		System.out.println("[SOLUTION]");
		for(Deplacement d:solution) {
			grilleDepart.effectuerDeplacement(d);
			System.out.println("Etape i :"+i+"   "+ d);
			System.out.println("\n"+grilleDepart);
			i++;
		}
	}

}








