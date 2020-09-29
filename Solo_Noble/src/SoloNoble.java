import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SoloNoble {
	
	private Grille g;
	private List<Deplacement> solution;
	
	public static void main(String[] args) throws IOException {
		SoloNoble solo;
		if (args.length > 0) {
			solo = new SoloNoble(args[0]);
		} else {
			solo = new SoloNoble(null);
		}
		solo.resoudreSoloNoble(solo.g.calculerNbPiece());
		solo.afficherSolution();
	}

	public SoloNoble(String f) throws IOException {
		if (f != null) {
			g = new Grille(f);
		} else {
			g = new Grille();
		}
		solution = new ArrayList<Deplacement>();
	}
	
	public boolean resoudreSoloNoble(int billes) {
		
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
	
	public void afficherSolution() {
		for(Deplacement i:solution) {
			System.out.println(i);
		}
		System.out.println("*** LONGUEUR SOLUTION : "+solution.size()+" ***");
	}

}








