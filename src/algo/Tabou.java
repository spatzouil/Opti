package algo;

import java.util.ArrayList;

import autre.Proccesseur;
import autre.Tache;

public class Tabou {

	
	
	public Tabou (ArrayList<Tache> LTache, int nbProc){
		
		
	}
	
	public ArrayList<Proccesseur> initialisation (ArrayList<Tache> LTache, int nbProc){
		ArrayList<Proccesseur> LProc = new ArrayList<Proccesseur>();
		
		for(int i = 0; i<nbProc; i++){
			Proccesseur newProc = new Proccesseur();
			for(int j=0; j<(LTache.size()/nbProc); j++){
				newProc.addTache(LTache.get(i*LTache.size()/nbProc+j));
			}
			LProc.add(newProc);
		}
		
		return null;
	}
}
