package algo;

import java.util.ArrayList;

import autre.Etat;
import autre.Proccesseur;
import autre.Tache;

public class Tabou {

	
	
	public Tabou (ArrayList<Tache> LTache, int nbProc){
		Etat etat = initialisation(LTache, nbProc);
		
	}
	
	public Etat initialisation (ArrayList<Tache> LTache, int nbProc){
		Etat etatInitial = new Etat(nbProc);
		for(int i = 0; i<nbProc; i++){
			Proccesseur newProc = new Proccesseur();
			for(int j=0; j<(LTache.size()/nbProc); j++){
				newProc.addTache(LTache.get(i*LTache.size()/nbProc+j));
			}
			etatInitial.addProccesseur(newProc);
		}
		
		return etatInitial;
	}
}
