package algo;

import java.util.ArrayList;

import autre.Etat;
import autre.Proccesseur;
import autre.Tache;

public class Tabou {
	
	private Etat meilleurEtat;
	
	public Tabou (ArrayList<Tache> lTache, int nbProc, int maxTabusize){
		int nbIte = 0;
		Etat bestEtat = initialisation(lTache, nbProc);
		Etat bestCandidat = bestEtat;
		ArrayList<Etat> lTabu = new ArrayList<Etat>();
		lTabu.add(bestCandidat);
		while(!ConditionDArret(nbIte)){
			ArrayList<Etat> LEtatVoisin = GenerationVoisin(bestEtat);
			bestCandidat = LEtatVoisin.get(0);
			for(Etat e: LEtatVoisin){
				if(!lTabu.contains(e) && (e.fontionObjectif()> bestCandidat.fontionObjectif())){
					bestCandidat = e;
				}
			}
			if(bestCandidat.fontionObjectif()>bestEtat.fontionObjectif()){
				bestEtat = bestCandidat;
				nbIte = 0;
			}
			else{
				nbIte++;
			}
			lTabu.add(bestCandidat);
			if(lTabu.size()> maxTabusize){
				lTabu.remove(0);
			}
		}
		meilleurEtat = bestEtat;
	}
	
	
	private ArrayList<Etat> GenerationVoisin(Etat etat) {
		ArrayList<Etat> lRetour = new ArrayList<Etat>();
		for(Proccesseur p: etat.getProccesseur()){
			for(Tache t: p.getTaches()){
				for(Proccesseur p2: etat.getProccesseur()){
					if(p != p2){
						Etat newEtat = etat;
						
						lRetour.add(newEtat);
					}
				}				
			}
		}
		return lRetour;
	}


	private boolean ConditionDArret(int nbIte) {
		
		return false;
	}

	//Creation d'un Etat0 
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
	

	public Etat getMeilleurEtat() {
		return meilleurEtat;
	}
}
