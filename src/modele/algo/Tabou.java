package modele.algo;

import java.util.ArrayList;

import modele.etat.Etat;
import modele.etat.Proccesseur;
import modele.etat.Tache;

public class Tabou {
	
	private Etat meilleurEtat;
	private int nbIteration = 0;	
	private ArrayList<Integer> lValeur = new ArrayList<Integer>();
	
	public Tabou (ArrayList<Tache> lTache, int nbProc, int maxTabusize, int nbIteration){
		int nbIte = 0;
		Etat bestEtat = new Etat().initialisationTabou(lTache, nbProc);
		Etat bestCandidat = bestEtat;
		ArrayList<Etat> lTabu = new ArrayList<Etat>();
		lTabu.add(bestCandidat);
		lValeur.add((int) bestEtat.fontionObjectif());
		while(!ConditionDArret(nbIte,(int)bestEtat.fontionObjectif(),nbProc, nbIteration)){
			ArrayList<Etat> LEtatVoisin = bestEtat.GenerationVoisinTabou();
			bestCandidat = LEtatVoisin.get(0);
			for(Etat e: LEtatVoisin){
				if(!lTabu.contains(e) && (e.fontionObjectif() > bestCandidat.fontionObjectif())){
					bestCandidat = e;
				}
			}
			if(bestCandidat.fontionObjectif()>bestEtat.fontionObjectif()){
				bestEtat = bestCandidat;
				lValeur.add((int) bestEtat.fontionObjectif());
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

	private boolean ConditionDArret(int nbIte, int nb, int nbProc, int nbIteration) {
		if((nb > -nbProc) || nbIte>nbIteration){
			return true;
		}
		return false;
	}
	
	public Etat getMeilleurEtat() {
		return meilleurEtat;
	}
	
	public int getNbIteration() {
		return nbIteration;
	}

	public ArrayList<Integer> getlValeur() {
		return lValeur;
	}
	
	public String toString(){
		return meilleurEtat.toString();
	}
	
	public static void main(String[] args) {
		ArrayList<Tache> taches = new ArrayList<>();
		for(int i=0; i<100; i++){
			taches.add(new Tache(0,(int)(Math.random() * 1000)+1));
		}
		Tabou r = new Tabou(taches, 10, 100, 10);
		System.out.println(r);
	}
}
