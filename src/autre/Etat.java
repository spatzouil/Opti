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
		int tempsMax;
		for(Proccesseur p: proccesseurs){
			for(Tache tache: p.getTache()){
				
			}
		}
		
		return tempsMax;
	}
	
	public static void main(String[] args) {

	}

}
