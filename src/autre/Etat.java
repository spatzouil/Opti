package autre;
import java.util.ArrayList;


public class Etat {
	ArrayList<Proccesseur> proccesseurs;
	
	public Etat(int nbProc){
		this.proccesseurs = new ArrayList<>(nbProc);
	}
	
	
	public int max(int a, int b){
		return (a > b)?a:b;
	}
	
	
	public static void main(String[] args) {

	}

}
