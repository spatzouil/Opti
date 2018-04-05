package autre;
import java.util.ArrayList;


public class Etat {

	ArrayList<Proccesseur> proccesseurs;
	
	public Etat(int nbProc){
		this.proccesseurs = new ArrayList<Proccesseur>();
	}
	
	public void addProccesseur(Proccesseur p){
		proccesseurs.add(p);
	}	
	
	public int fontionObjectif(){
		int tempsMax=0;
		int tempsProc;
		for(Proccesseur p: proccesseurs){
			tempsProc = 0; 
			for(Tache tache: p.getTache()){
				tempsProc += tache.getP();
			}
			tempsMax = max (tempsMax, tempsProc);
		}
		return tempsMax;
	}
	
	public int max(int a, int b){
		return (a > b)?a:b;
	}

	public ArrayList<Proccesseur> getProccesseur() {
		return proccesseurs;
	}
	
	public static void main(String[] args) {

	}
}
