package modele.algo;

import java.util.ArrayList;

import modele.etat.Etat;
import modele.etat.Tache;

public class Recuit {

	private float temperature = 100000; //temperature T
	private float borneInfTemperature = 0.1f; // born inferieur de temperature 
	private float lambda = 0.99f;
	private int nbIteration = 0;
	
	private Etat etat;
	
	
	public Recuit(ArrayList<Tache> taches, int nbProc, float temperature, float bornInf){
		this.temperature = temperature;
		this.borneInfTemperature = bornInf;
		this.etat = new Etat(nbProc);
		for(Tache t: taches){
			this.etat.addTacheProc(this.alea(0,nbProc), t);
		}
		this.recuitSimule();
	}
	
	public Recuit(ArrayList<Tache> taches, int nbProc){
		this.etat = new Etat(nbProc);
		for(Tache t: taches){
			this.etat.addTacheProc(this.alea(0,nbProc), t);
		}
		this.recuitSimule();
	}
	
	
	/**
	 * Reduit la temperature avec lambda
	 */
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
	
	/**
	 * Retourn vrai si le random 
	 * @param probaSucces
	 * @return
	 */
	public boolean voisinAccepteAlea(float probaSucces){
//		System.out.println("Proba de succes: " + probaSucces);
		return (Math.random() < probaSucces)?true:false;
	}
	
	/**
	 * Optimise un etat
	 * @return
	 */
	public Etat recuitSimule(){
		float eMax = this.etat.optimumParfait();
		System.out.println("Optimum parfait: " + eMax);
		System.out.println();
		System.out.println(this.etat);
		System.out.println();
		
		Etat etatCourant = this.etat;
		Etat meilleurEtat = this.etat;
		float energieEtatCourant = etatCourant.fontionObjectifRecuit();
		float energieMeilleurEtat = meilleurEtat.fontionObjectifRecuit();
		
		do{
			Etat etatTemp = etatCourant.genererVoisinRecuit();
			float energieEtatTemp = etatTemp.fontionObjectifRecuit();
			System.out.println("energieEtatCourant: " + energieEtatCourant + " energieEtatTemp: " + energieEtatTemp + "energieMeilleurEtat: " + energieMeilleurEtat + " iteration: " + this.nbIteration);

			if(energieEtatTemp >= energieEtatCourant || this.voisinAccepteAlea(this.regleMetropolis(etatTemp, etatCourant))){
				etatCourant = etatTemp;
				energieEtatCourant = energieEtatTemp;
				if(energieEtatCourant > energieMeilleurEtat){
					meilleurEtat = etatCourant; 
					energieMeilleurEtat = energieEtatCourant;
				}
			}
			
			this.itererTemperature(); //Reduit la temperature
			this.nbIteration++;
		}while(this.temperature > this.borneInfTemperature && energieEtatCourant < eMax);
		
		System.out.println(meilleurEtat);

//		this.etat = meilleurEtat;
		return meilleurEtat;
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
		return Math.abs(e1.fontionObjectifRecuit() - e2.fontionObjectifRecuit());
	}
	
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("T = "+this.temperature + "\n");
		sb.append(this.etat.toString());
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		ArrayList<Tache> taches = new ArrayList<>();
		int nbTaches = 10;
		for(int i=0; i<nbTaches; i++){
			int valMax = 10;
			taches.add(new Tache(0,(int)(Math.random() * valMax)));
		}
		Recuit r = new Recuit(taches, 5);
	}
}
