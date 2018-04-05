package autre;
import java.util.ArrayList;


public class Etat {

	ArrayList<Proccesseur> proccesseurs;
	
	public Etat(int nbProc){
		this.proccesseurs = new ArrayList<Proccesseur>();
		for(int i=0; i < nbProc; i++)
			this.addProccesseur(new Proccesseur());
	}
	
	public void addProccesseur(Proccesseur p){
		proccesseurs.add(p);
	}	
	
	public int fontionObjectif(){
		int tempsMax=0;
		int tempsProc;
		for(Proccesseur p: proccesseurs){
			tempsProc = 0; 
			for(Tache tache: p.getTaches()){
				tempsProc += tache.getP();
			}
			tempsMax = max (tempsMax, tempsProc);
		}
		return tempsMax;
	}
	
	public int max(int a, int b){
		return (a > b)?a:b;
	}
	
	public void addTacheProc(int index, Tache tache){
		if(index < this.proccesseurs.size())
			this.proccesseurs.get(index).addTache(tache);
		else
			System.out.println("ERREUR INDEX ADDTACHEPROC");
	}
	
	
	public void transfertTache(int indexProc1, int indexProc2, int indexTache){
		Tache t = this.proccesseurs.get(indexProc1).getTache(indexTache);
		this.proccesseurs.get(indexProc1).removeTache(indexTache);
		this.proccesseurs.get(indexProc2).addTache(t);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<this.proccesseurs.size(); i++){
			sb.append("Proccesseur n°"+(i+1)+":\n");
			sb.append(this.proccesseurs.get(i).toString());
		}
		sb.append("\n");
		sb.append("Temps max : " + this.fontionObjectif() + "\n");
		return sb.toString();
	}

	public ArrayList<Proccesseur> getProccesseur() {
		return proccesseurs;
	}
	
	public static void main(String[] args) {

	}
}
