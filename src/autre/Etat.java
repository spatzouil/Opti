package autre;
import java.util.ArrayList;


public class Etat {

	ArrayList<Proccesseur> proccesseurs;
	
	public Etat(int nbProc){
		this.proccesseurs = new ArrayList<Proccesseur>();
		for(int i=0; i < nbProc; i++)
			this.addProccesseur(new Proccesseur());
	}
	
	public Etat(Etat e){
		this.proccesseurs = new ArrayList<>();
		for(Proccesseur p: e.getProccesseur()){
			proccesseurs.add(new Proccesseur(p));
		}
	}
	
	public void addProccesseur(Proccesseur p){
		proccesseurs.add(p);
	}	
	
	public int fontionObjectif(){
		int tempsMax=0;
		int tempsProc;
		for(Proccesseur p: proccesseurs){
			tempsProc = 0; 
			for(Tache tache: p.getTaches()){
				tempsProc += tache.getP();
			}
			tempsMax = max (tempsMax, tempsProc);
		}
		return -tempsMax;
	}
	
	public int max(int a, int b){
		return (a > b)?a:b;
	}
	
	public void addTacheProc(int index, Tache tache){
		if(index < this.proccesseurs.size())
			this.proccesseurs.get(index).addTache(tache);
		else
			System.out.println("ERREUR INDEX ADDTACHEPROC");
	}
	
	
	public void transfertTache(int indexProc1, int indexProc2, int indexTache){
		Tache t = this.proccesseurs.get(indexProc1).getTache(indexTache);
		this.proccesseurs.get(indexProc1).removeTache(indexTache);
		this.proccesseurs.get(indexProc2).addTache(t);
	}
	
	public void transfertTache(Proccesseur Proc1, Proccesseur Proc2, int indexTache){
		Tache t = Proc1.getTache(indexTache);
		Proc1.removeTache(indexTache);
		Proc2.addTache(t);
	}
	
	public Etat genererVoisinRecuit(){
		Etat etatRes = new Etat(this);
		int indexProc1 = this.alea(0, this.getNbProc());
		int indexProc2 = this.alea(0, this.getNbProc());
		
		//Permet d'eviter que indexProc1 = indexProc2
		while(indexProc1 == indexProc2){
			indexProc2 = this.alea(0, this.getNbProc());
		}
		
		int indexTache = this.alea(0,this.getNbTacheProc(indexProc1));
		etatRes.transfertTache(indexProc1, indexProc2, indexTache);

		return etatRes;
	}
	
																															/*** GETTERS ***/
	
	
	public ArrayList<Proccesseur> getProccesseur() {
		return proccesseurs;
	}
	
	public int getNbProc(){
		return this.proccesseurs.size();
	}
	
	/**
	 * Retourne le nombre de tache du processeur au rang index
	 * @param index
	 * @return
	 */
	public int getNbTacheProc(int index){
		return this.proccesseurs.get(index).getNbTache();
	}
	
	/**
	 * retourne un entier aleatoire entre min et max (max non compris)
	 * @param min
	 * @param max
	 * @return
	 */
	public int alea(int min, int max){
		return min + (int)(Math.random() * max);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<this.proccesseurs.size(); i++){
			sb.append("Proccesseur n°"+(i+1)+":\n");
			sb.append(this.proccesseurs.get(i).toString());
		}
		sb.append("\n");
		sb.append("Temps max : " + this.fontionObjectif() + "\n");
		return sb.toString();
	}

	
	public static void main(String[] args) {

	}
}
