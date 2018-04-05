package modele.algo;

import java.util.ArrayList;

import modele.etat.Etat;
import modele.etat.Tache;

public class Recuit {

	private int temps; //energie
	private float temperature = 10000; //temperature T
	private float bornTInf = 1; // born inferieur de temperature 
	private float lambda = 0.99f;
	
	private Etat etat;
	
	
	public Recuit(ArrayList<Tache> taches, int nbProc){
		this.etat = new Etat(nbProc);
		for(Tache t: taches){
			this.etat.addTacheProc(this.alea(0,nbProc), t);
		}
	}
	
	
	public void itererTemperature(){
		this.temperature *= this.lambda;
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
	
	public boolean voisinAccepteAlea(float probaSucces){
		return (Math.random() >= probaSucces)?true:false;
	}
	
	public void recuitSimule(){
		Etat etatCourant = this.etat;
		Etat meilleurEtat = this.etat;
		int energieEtatCourant = etatCourant.fontionObjectif();
		int energieMeilleurEtat = meilleurEtat.fontionObjectif();
		
		do{
			Etat etatTemp = etatCourant.genererVoisinRecuit();
			int energieEtatTemp = etatTemp.fontionObjectif();
			
			if(energieEtatTemp > energieEtatCourant || this.voisinAccepteAlea(this.regleMetropolis(etatTemp, etatTemp))){
				etatCourant = etatTemp;
				energieEtatCourant = energieEtatTemp;
				if(energieEtatCourant > energieMeilleurEtat){
					meilleurEtat = etatCourant;
					energieMeilleurEtat = energieEtatCourant;
				}
			}
			
			this.itererTemperature();
			
		}while(this.temperature > 1);
	}
	
	public float regleMetropolis(Etat e1, Etat e2){
		float deltatE = this.deltatEnergie(e1, e2);
		float res = (float) Math.exp((-deltatE) / this.temperature);
		return res;
	}
	
	/**
	 * Retourne le deltatEnergie entre deux etat, negatif si le nouvelle etat est meilleur
	 * @param e
	 * @return
	 */
	public float deltatEnergie(Etat e1, Etat e2){
		return Math.abs(e1.fontionObjectif() - e2.fontionObjectif());
	}
	
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("T = "+this.temperature + "\n");
		sb.append(this.etat.toString());
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		ArrayList<Tache> taches = new ArrayList<>();
		for(int i=0; i<10; i++){
			taches.add(new Tache(0,(int)(Math.random() * 10)));
		}
		Recuit r = new Recuit(taches, 3);
//		System.out.println(r);
		
	}
}
