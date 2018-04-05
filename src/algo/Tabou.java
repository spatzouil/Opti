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
				if(!lTabu.contains(e) && (e.fontionObjectif() > bestCandidat.fontionObjectif())){
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
		for(int h=0; h<etat.getProccesseur().size();h++){
			for(int i=0; i<etat.getProccesseur().get(h).getTaches().size();i++){
				for(int j =0; j<etat.getProccesseur().size(); j++){
					if(h != j){
						Etat newEtat = new Etat(etat);
						newEtat.transfertTache(h, j, i);
						lRetour.add(newEtat);
					}
				}
			}
			
		}
		return lRetour;
	}


	private boolean ConditionDArret(int nbIte) {
		if(nbIte>100){
			return true;
		}
		return false;
	}

	//Creation d'un Etat0 
	public Etat initialisation (ArrayList<Tache> LTache, int nbProc){
		
		Etat etatInitial = new Etat(nbProc);
		for(int i = 0; i<nbProc; i++){
			Proccesseur newProc = new Proccesseur();
			etatInitial.addProccesseur(newProc);
		}
		for(int j=0; j<LTache.size(); j++){
			etatInitial.getProccesseur().get(0).addTache(LTache.get(j));
		}
		return etatInitial;
	}
	

	public Etat getMeilleurEtat() {
		return meilleurEtat;
	}
	
	public String toString(){
		return meilleurEtat.toString();
	}
	
	public static void main(String[] args) {
		ArrayList<Tache> taches = new ArrayList<>();
		for(int i=0; i<10; i++){
			taches.add(new Tache(0,(int)(Math.random() * 10)+1));
		}
		Tabou r = new Tabou(taches, 2, 100);
		System.out.println(r);
	}
}
