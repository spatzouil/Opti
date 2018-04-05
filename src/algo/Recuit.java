package algo;

import java.util.ArrayList;

import autre.Etat;
import autre.Tache;

public class Recuit {

	private int temps; //energie
	private float temperature = 10000; //temperature T
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
	
	
	public static void main(String[] args) {
		ArrayList<Tache> taches = new ArrayList<>();
//		for(int i=0; i<)
		Recuit r = new Recuit(taches, 10);
	}

}
