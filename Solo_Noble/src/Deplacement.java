/**
 * Représente un déplacement potentiel ou avere d une piece
 *
 */
public class Deplacement {

	/**
	 * Ligne de la piece avant son deplacement
	 */
	private int ligneD;
	
	/**
	 * Ligne de la piece apres son deplacement
	 */
	private int ligneA;
	
	/**
	 * Colonne de la piece avant son deplacement
	 */
	private int colA;
	
	/**
	 * Colonne de la piece apres son deplacement
	 */
	private int colD;

	public Deplacement(int ligneD, int colD, int ligneA, int colA) {
		super();
		this.ligneD = ligneD;
		this.ligneA = ligneA;
		this.colA = colA;
		this.colD = colD;
	}

	/**
	 * @return the ligneD
	 */
	public int getLigneD() {
		return ligneD;
	}

	/**
	 * @param ligneD the ligneD to set
	 */
	public void setLigneD(int ligneD) {
		this.ligneD = ligneD;
	}

	/**
	 * @return the ligneA
	 */
	public int getLigneA() {
		return ligneA;
	}

	/**
	 * @param ligneA the ligneA to set
	 */
	public void setLigneA(int ligneA) {
		this.ligneA = ligneA;
	}

	/**
	 * @return the colA
	 */
	public int getColA() {
		return colA;
	}

	/**
	 * @param colA the colA to set
	 */
	public void setColA(int colA) {
		this.colA = colA;
	}

	/**
	 * @return the colD
	 */
	public int getColD() {
		return colD;
	}

	/**
	 * @param colD the colD to set
	 */
	public void setColD(int colD) {
		this.colD = colD;
	}
	
	public String toString() {
		return "Ligne Depart: "+ligneD+" | colonne depart:"+colD+" | ligne arrivé: "+ligneA+" | colonne arrive: "+colA;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
