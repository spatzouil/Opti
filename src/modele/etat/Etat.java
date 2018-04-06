package modele.etat;
import java.util.ArrayList;


public class Etat {

	ArrayList<Proccesseur> proccesseurs;
	
	public Etat(){
		this.proccesseurs = new ArrayList<Proccesseur>();
	}
	
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
	
	public float fontionObjectif(){
		float tempsProc;
		float tempstt=0;
		float score=0;
		float tempsMoy;
		for(Proccesseur p: proccesseurs){
			for(Tache tache: p.getTaches()){
				tempstt += tache.getP();
			}
		}
		tempsMoy = tempstt/proccesseurs.size();
		
		for(Proccesseur p: proccesseurs){
			tempsProc = 0; 
			for(Tache tache: p.getTaches()){
				tempsProc += tache.getP();
			}

			score += Math.abs(tempsMoy-tempsProc);
		}
		return -score;
	}
	
	public float fontionObjectifRecuit(){
		float tempsMax=0;
		float tempsProc;
		for(Proccesseur p: proccesseurs){
			tempsProc = 0; 
			for(Tache tache: p.getTaches()){
				tempsProc += tache.getP();
			}
			tempsMax = max (tempsMax, tempsProc);
		}
		return -tempsMax;
	}
	
	public float optimumParfait(){
		float optimumParfait = 0;
		for(Proccesseur p : proccesseurs){
			for(Tache t: p.getTaches()){
				optimumParfait += t.getP();
			}
		}
		
		return -(optimumParfait / this.proccesseurs.size());	
	}
	
	public float max(float a, float b){
		return (a > b)?a:b;
	}
	
	public int min(int a, int b){
		return (a < b)?a:b;
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
		//Si le proccesseur n'a pas de tache en prendre un autre
		while(this.getNbTacheProc(indexProc1) == 0){
			indexProc1 = this.alea(0, this.getNbProc());
		}
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
	
	
	/*
	 * !estUnEntier(s) || Integer.parseInt(s) != 2 || Integer.parseInt(s) != 1
	 */
	
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
			sb.append("Proccesseur n°"+(i+1)+": ");
			sb.append(this.proccesseurs.get(i).toString()+"\n\n");
		}		
		return sb.toString();
	}

	
	public ArrayList<Etat> GenerationVoisinTabou() {
		ArrayList<Etat> lRetour = new ArrayList<Etat>();
		for(int h = 0 ; h<this.getProccesseur().size();h++){
			for(int i = 0; i<this.getProccesseur().get(h).getTaches().size();i++){
				for(int j = 0; j<this.getProccesseur().size(); j++){
					if(h != j){
						Etat newEtat = new Etat(this);
						newEtat.transfertTache(h, j, i);
						lRetour.add(newEtat);
					}
				}
			}
			
		}
		return lRetour;
	}
	
	//Creation d'un Etat0 
	public Etat initialisationTabou (ArrayList<Tache> LTache, int nbProc){
		Etat etatInitial = new Etat(nbProc);
		for(int j=0; j<LTache.size(); j++){
			etatInitial.getProccesseur().get(0).addTache(LTache.get(j));
		}	
		return etatInitial;
	}
		
	public static void main(String[] args) {

	}
}
