package Etat;
import java.util.ArrayList;


public class Proccesseur {

	private ArrayList<Tache> taches;
	
	public Proccesseur(){
		this.taches = new ArrayList<>();
	}

	public Proccesseur(Proccesseur p) {
		this.taches = new ArrayList<>();
		for(Tache t: p.getTaches()){
			taches.add(new Tache(t));
		}
	}

	public void addTache(Tache tache) {
		taches.add(tache);
	}

	public ArrayList<Tache> getTaches() {
		return taches;
	}
	
	public Tache getTache(int index){
		if(index >= this.taches.size())
			System.out.println("ERREUR INDEX GETTACHE");
		return this.taches.get(index);
	}
	
	public int getNbTache(){
		return this.taches.size();
	}
	
	public void removeTache(int index){
		this.taches.remove(index);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		int dureeTotal = 0;
		for(Tache t: taches){
			dureeTotal += t.getP();
		}
		sb.append("duree total: " + dureeTotal + "\n");
		for(Tache t: taches){
//			sb.append(t.toString() + ", ");
		}
		return sb.toString();
	}

}
