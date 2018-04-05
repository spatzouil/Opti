package autre;
import java.util.ArrayList;


public class Etat {

	ArrayList<Proccesseur> proccesseur;
	
	public Etat(int nbProc){
		this.proccesseur = new ArrayList<Proccesseur>();
	}
	
	
	public int max(int a, int b){
		return (a > b)?a:b;
	}

	
	public static void main(String[] args) {

	}

}
