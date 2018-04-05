package autre;

public class Tache {

	private int numTache; //sert a identifier la tache --> utile ????
	private int p; //durée operatoire
	
	
	public Tache(int num, int duree){
		this.numTache = num;
		this.p = duree;
	}


	public Tache(Tache t) {
		this.numTache = t.getNumTache();
		this.p = getP();
	}


	/**
	 * @return the numTache
	 */
	public int getNumTache() {
		return numTache;
	}


	/**
	 * @return the p
	 */
	public int getP() {
		return p;
	}
	
	public String toString(){
		return p+"";		
	}

}
