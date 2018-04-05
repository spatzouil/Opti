package algo;

import java.util.ArrayList;

import autre.Etat;
import autre.Tache;

public class Recuit {

	private int temps; //energie
	private float temperature = 10000; //temperature T
	private float bornTInf = 1; // born inferieur de temperature 
	private float lambda = 0.99f;
	
	private Etat etat;
	
	
	public Recuit(ArrayList<Tache> taches, int nbProc){
		this.etat = new Etat(nbProc);
		for(Tache t: taches){
			this.etat.addTacheProc(this.alea(nbProc), t);
		}
	}
	
	
	public void itererTemperature(){
		this.temperature *= this.lambda;
	}
	
	public int alea(int max){
		return (int)(Math.random() * max);
	}
	
	public void recuitSimule(){
		Etat etatCourant = this.etat;
		Etat meilleurEtat = this.etat;
		
	}
	
	public float regleMetropolis(Etat e){
		float deltatE = this.deltatEnergie(e);
		float res = (float) Math.exp((-deltatE) / this.temperature);
		return res;
	}
	
	/**
	 * Retourne le deltatEnergie entre deux etat, negatif si le nouvelle etat est meilleur
	 * @param e
	 * @return
	 */
	public float deltatEnergie(Etat e){
		return this.etat.fontionObjectif() - e.fontionObjectif();
	}
	
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("T = "+this.temperature + "\n");
		sb.append(this.etat.toString());
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		ArrayList<Tache> taches = new ArrayList<>();
		for(int i=0; i<20; i++){
			taches.add(new Tache(0,(int)(Math.random() * 10)));
		}
		Recuit r = new Recuit(taches, 10);
//		System.out.println(r);
		
	}
}
