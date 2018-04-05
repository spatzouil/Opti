package algo;

import java.util.ArrayList;

import Etat.Etat;
import Etat.Proccesseur;
import Etat.Tache;

public class Tabou {
	
	private Etat meilleurEtat;
	private int nbIteration = 0;	
	
	public Tabou (ArrayList<Tache> lTache, int nbProc, int maxTabusize){
		int nbIte = 0;
		Etat bestEtat = new Etat().initialisationTabou(lTache, nbProc);
		Etat bestCandidat = bestEtat;
		ArrayList<Etat> lTabu = new ArrayList<Etat>();
		lTabu.add(bestCandidat);
		
		while(!ConditionDArret(nbIte)){
			ArrayList<Etat> LEtatVoisin = bestEtat.GenerationVoisinTabou();
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
			this.nbIteration++;
		}
		meilleurEtat = bestEtat;
	}

	private boolean ConditionDArret(int nbIte) {
		if(nbIte>10){
			return true;
		}
		return false;
	}

	
	

	public Etat getMeilleurEtat() {
		return meilleurEtat;
	}
	
	public String toString(){
		return meilleurEtat.toString();
	}
	
	public static void main(String[] args) {
		ArrayList<Tache> taches = new ArrayList<>();
		for(int i=0; i<100; i++){
			taches.add(new Tache(0,(int)(Math.random() * 100)+1));
		}
		Tabou r = new Tabou(taches, 10, 100);
		System.out.println(r);
	}
}
