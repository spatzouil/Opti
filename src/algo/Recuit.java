package algo;

import java.util.ArrayList;

import autre.Etat;
import autre.Tache;

public class Recuit {

	private int temps; //energie
	private int temperature; //temperature T
	private Etat etat;
	
	
	public Recuit(ArrayList<Tache> tache, int nbProc){
		this.etat = new Etat(nbProc);
	}
	
	
	public static void main(String[] args) {

	}

}
